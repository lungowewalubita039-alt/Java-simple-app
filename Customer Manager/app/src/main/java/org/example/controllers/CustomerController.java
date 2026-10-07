package org.example.controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import org.example.models.Customer;

import java.util.Optional;

public class CustomerController {

    @FXML private TableView<Customer> customerTable;
    @FXML private TableColumn<Customer, String> nameCol;
    @FXML private TableColumn<Customer, String> provinceCol;

    @FXML private GridPane dialogForm;
    @FXML private TextField dialogNameField;
    @FXML private ComboBox<String> dialogProvinceComboBox;

    private ObservableList<Customer> customerList;

    @FXML
    public void initialize() {
        customerList = FXCollections.observableArrayList();

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        provinceCol.setCellValueFactory(new PropertyValueFactory<>("province"));
        
        customerTable.setItems(customerList);

        dialogProvinceComboBox.setItems(FXCollections.observableArrayList(
            "Lusaka", "Copperbelt", "Central", "Southern", "North-Western", 
            "Western", "Eastern", "Northern", "Luapula", "Muchinga"
        ));
    }

    @FXML
    private void handleAddCustomer() {
        dialogNameField.clear();
        dialogProvinceComboBox.setValue(null);

        Dialog<Customer> dialog = new Dialog<>();
        dialog.setTitle("Add New Customer");
        dialog.setHeaderText("Enter the customer's details below:");
        dialog.getDialogPane().setStyle("-fx-font-family: 'Segoe UI'; -fx-background-color: #fafafa;");

        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);
        dialog.getDialogPane().setContent(dialogForm);

        // Save button is Blue
        Node saveButton = dialog.getDialogPane().lookupButton(saveButtonType);
        saveButton.setStyle("-fx-background-color: #005fb8; -fx-text-fill: white; -fx-background-radius: 4; -fx-padding: 6 14; -fx-cursor: hand;");
        saveButton.setDisable(true);

        Node cancelButton = dialog.getDialogPane().lookupButton(ButtonType.CANCEL);
        cancelButton.setStyle("-fx-background-color: white; -fx-border-color: #d1d1d1; -fx-border-radius: 4; -fx-background-radius: 4; -fx-text-fill: #333333; -fx-padding: 5 13; -fx-cursor: hand;");

        dialogNameField.textProperty().addListener((observable, oldValue, newValue) -> {
            saveButton.setDisable(newValue.trim().isEmpty() || dialogProvinceComboBox.getValue() == null);
        });
        
        dialogProvinceComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            saveButton.setDisable(dialogNameField.getText().trim().isEmpty() || newValue == null);
        });

        Platform.runLater(dialogNameField::requestFocus);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                return new Customer(dialogNameField.getText().trim(), dialogProvinceComboBox.getValue());
            }
            return null;
        });

        Optional<Customer> result = dialog.showAndWait();
        result.ifPresent(customer -> customerList.add(customer));
    }

    @FXML
    private void handleDeleteCustomer() {
        Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        
        if (selectedCustomer == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select a customer to delete.");
            alert.getDialogPane().setStyle("-fx-font-family: 'Segoe UI';");
            alert.showAndWait();
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirm Deletion");
        confirm.setHeaderText("Delete Customer");
        confirm.setContentText("Are you sure you want to delete " + selectedCustomer.getName() + "?");
        confirm.getDialogPane().setStyle("-fx-font-family: 'Segoe UI';");

        Optional<ButtonType> result = confirm.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            customerList.remove(selectedCustomer);
        }
    }
}