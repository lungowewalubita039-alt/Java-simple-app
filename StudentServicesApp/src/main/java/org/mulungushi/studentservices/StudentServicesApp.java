package org.mulungushi.studentservices;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StudentServicesApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // Change this line to load the new main_window.fxml
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/mulungushi/studentservices/views/main_window.fxml"));
        
        // Increased the width slightly to accommodate the tabs
        Scene scene = new Scene(fxmlLoader.load(), 650, 450);
        
        stage.setTitle("Student Services App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}