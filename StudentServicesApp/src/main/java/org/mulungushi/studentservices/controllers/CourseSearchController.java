package org.mulungushi.studentservices.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.mulungushi.studentservices.services.BackgroundTaskRunner;

public class CourseSearchController {
    @FXML private TextField searchField;
    @FXML private Label resultLabel;

    @FXML
    protected void onSearchClick() {
        resultLabel.setText("Searching network...");
        
        // Simulating a slow network search safely on a background thread
        BackgroundTaskRunner.runSlowTask(
            () -> {
                try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
            },
            () -> resultLabel.setText("Results found for: " + searchField.getText())
        );
    }
}