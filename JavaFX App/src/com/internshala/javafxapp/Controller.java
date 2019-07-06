package com.internshala.javafxapp;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

//import java.awt.*;
import java.net.URL;

import java.util.ResourceBundle;

public class Controller implements Initializable {
	@FXML
	public Label welcomeLabel;
	@FXML
	public ChoiceBox<String> choiceBox;
	@FXML
	public TextField userInputField;
	@FXML
	public Button convertButton;

	private static final String C_To_F_TEXT="Celsius to Fahrenheit";
	private static final String F_To_C_TEXT="Farenheit to Celsius";
	private boolean isC_To_F_TEXT=true;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		choiceBox.getItems().add(C_To_F_TEXT);
		choiceBox.getItems().add(F_To_C_TEXT);
		choiceBox.setValue(C_To_F_TEXT);
		choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue.equals(C_To_F_TEXT)){
				isC_To_F_TEXT=true;
			}
			else{
				isC_To_F_TEXT=false;
			}
		});
		convertButton.setOnAction(event -> convert());
	}

	private void convert() {
		String input=userInputField.getText();
		float temp=0.0f;
		try{
			 temp=Float.parseFloat(input);
		}
		catch(Exception exception){
			warnUser();
			return;
		}

		float newtemp=0.0f;
		if(isC_To_F_TEXT){
			newtemp=(temp*9/5)+32;
		}
		else{
			newtemp=((temp-32)*5)/9;
		}
		display(newtemp);
	}

	private void warnUser() {
		Alert alert=new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error Occured");
		alert.setHeaderText("Invalid Temperature Input");
		alert.setContentText("Please Enter Valid Temperature");
		alert.show();
	}

	private void display(float newtemp) {
		String unit= isC_To_F_TEXT?" F":" C";
		System.out.println(newtemp+unit);
		Alert alert=new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Result");
		alert.setContentText("The new Temperature is "+ newtemp+unit);
		alert.show();
	}
}
