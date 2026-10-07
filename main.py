import os
import subprocess

project_name = "StudentServicesApp"
package_path = "org/mulungushi/studentservices"

# Define the file structure and their contents
files = {
    f"{project_name}/settings.gradle.kts": f"""rootProject.name = "{project_name}"
""",
    
    f"{project_name}/build.gradle.kts": """plugins {
    java
    application
    id("org.openjfx.javafxplugin") version "0.1.0"
}

repositories {
    mavenCentral()
}

javafx {
    version = "21"
    modules = listOf("javafx.controls", "javafx.fxml")
}

application {
    mainClass.set("org.mulungushi.studentservices.StudentServicesApp")
}
""",

    f"{project_name}/src/main/java/{package_path}/StudentServicesApp.java": """package org.mulungushi.studentservices;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StudentServicesApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/mulungushi/studentservices/views/register_student.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Student Services App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
""",

    f"{project_name}/src/main/java/{package_path}/controllers/RegistrationController.java": """package org.mulungushi.studentservices.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class RegistrationController {
    @FXML
    private Label statusLabel;

    @FXML
    protected void onRegisterButtonClick() {
        statusLabel.setText("Student Registration logic triggered.");
    }
}
""",

    f"{project_name}/src/main/resources/{package_path}/views/register_student.fxml": """<?xml version="1.0" encoding="UTF-8"?>
<?import javafx.geometry.Insets?>
<?import javafx.scene.control.Button?>
<?import javafx.scene.control.Label?>
<?import javafx.scene.layout.VBox?>

<VBox alignment="CENTER" spacing="20.0" xmlns:fx="http://javafx.com/fxml"
      fx:controller="org.mulungushi.studentservices.controllers.RegistrationController"
      style="-fx-font-family: 'Segoe UI'; -fx-background-color: #fafafa;">
    <padding>
        <Insets bottom="20.0" left="20.0" right="20.0" top="20.0"/>
    </padding>

    <Label text="Student Registration Layer" style="-fx-font-size: 18px; -fx-font-weight: bold;"/>
    <Button text="Register Student" onAction="#onRegisterButtonClick" style="-fx-background-color: #005fb8; -fx-text-fill: white; -fx-background-radius: 4;"/>
    <Label fx:id="statusLabel" text="Waiting for input..."/>
</VBox>
""",

    # Placeholder files to satisfy the required layered architecture
    f"{project_name}/src/main/java/{package_path}/controllers/CourseSearchController.java": "package org.mulungushi.studentservices.controllers;\n\npublic class CourseSearchController {}\n",
    f"{project_name}/src/main/java/{package_path}/services/StudentService.java": "package org.mulungushi.studentservices.services;\n\npublic class StudentService {}\n",
    f"{project_name}/src/main/java/{package_path}/services/BackgroundTaskRunner.java": "package org.mulungushi.studentservices.services;\n\npublic class BackgroundTaskRunner {}\n",
    f"{project_name}/src/main/java/{package_path}/models/Student.java": "package org.mulungushi.studentservices.models;\n\npublic class Student {}\n",
    f"{project_name}/src/main/java/{package_path}/models/Course.java": "package org.mulungushi.studentservices.models;\n\npublic class Course {}\n",
    f"{project_name}/src/main/java/{package_path}/dao/DatabaseManager.java": "package org.mulungushi.studentservices.dao;\n\npublic class DatabaseManager {}\n",
    f"{project_name}/src/main/java/{package_path}/api/ExchangeRateClient.java": "package org.mulungushi.studentservices.api;\n\npublic class ExchangeRateClient {}\n",
    f"{project_name}/src/main/resources/{package_path}/views/search_course.fxml": "<!-- Search Course FXML -->\n",
    f"{project_name}/src/main/resources/{package_path}/views/update_grade.fxml": "<!-- Update Grade FXML -->\n",
    f"{project_name}/src/test/java/{package_path}/services/StudentServiceTest.java": "package org.mulungushi.studentservices.services;\n\npublic class StudentServiceTest {}\n",
}

def create_project():
    print(f"Creating project structure for {project_name}...")
    
    for filepath, content in files.items():
        # Create directories if they don't exist
        os.makedirs(os.path.dirname(filepath), exist_ok=True)
        # Write the file
        with open(filepath, 'w', encoding='utf-8') as f:
            f.write(content)
        print(f"Created: {filepath}")

    print("\nProject files generated successfully. Running Gradle tasks...")

    # Execute Gradle commands
    try:
        # Use shell=True for Windows compatibility with 'gradle' command
        subprocess.run(["gradle", "build"], cwd=project_name, check=True, shell=True)
        subprocess.run(["gradle", "run"], cwd=project_name, check=True, shell=True)
    except subprocess.CalledProcessError as e:
        print(f"\nGradle execution failed: {e}")
        print("Please ensure Gradle is added to your system PATH.")

if __name__ == "__main__":
    create_project()