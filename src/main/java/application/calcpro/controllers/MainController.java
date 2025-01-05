package application.calcpro.controllers;

import application.calcpro.viewfactory.ViewFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainController {
    @FXML
    private void handleCalculate(ActionEvent event) {
        // Close the previous stage
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        ViewFactory.getInstance().closeStage(stage);

        // Load Subscription Management Window
        ViewFactory.getInstance().showCalculationWindow();
    }

    @FXML
    private void handleHistory(ActionEvent event) {
        // Close the previous stage
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        ViewFactory.getInstance().closeStage(stage);

        // Load Reservation Management Window
        ViewFactory.getInstance().showHistoryWindow();
    }

    // Exit the application
    @FXML
    private void handleExit(ActionEvent event) {
        // Get the current stage and close it
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        ViewFactory.getInstance().closeStage(stage);
    }
}
