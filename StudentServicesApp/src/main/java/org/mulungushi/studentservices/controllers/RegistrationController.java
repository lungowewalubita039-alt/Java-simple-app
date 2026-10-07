package org.mulungushi.studentservices.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.mulungushi.studentservices.services.StudentService;

public class RegistrationController {
    @FXML private TextField idField;
    @FXML private TextField nameField;
    @FXML private Label statusLabel;

    private StudentService studentService = new StudentService();

    @FXML
    protected void onRegisterButtonClick() {
        try {
            studentService.registerNewStudent(idField.getText(), nameField.getText(), "N/A");
            statusLabel.setText("Student successfully registered!");
            statusLabel.setStyle("-fx-text-fill: green;");
        } catch (Exception e) {
            statusLabel.setText("Error: Invalid Input");
            statusLabel.setStyle("-fx-text-fill: red;");
        }
    }
}