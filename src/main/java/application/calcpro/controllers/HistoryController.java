package application.calcpro.controllers;

import application.calcpro.database.DatabaseOperations;
import application.calcpro.models.JobData;
import application.calcpro.viewfactory.ViewFactory;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class HistoryController implements Initializable {

    // Logger
    private static final Logger logger =  Logger.getLogger(HistoryController.class.getName());

    @FXML
    private GridPane historyGridPane;

    @FXML private FontAwesomeIconView backBtn;

    private List<JobData> allJobData;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.allJobData = DatabaseOperations.loadData();
        onHistoryGridBoxDisplay();

        backBtn.setOnMouseClicked(e -> {
            Stage stage = (Stage) backBtn.getScene().getWindow();
            ViewFactory.getInstance().closeStage(stage);

            // Open the main window
            ViewFactory.getInstance().showMainWindow();
        });
    }

    private void onHistoryGridBoxDisplay() {
        // n rows each have 1 column

        int row = 0;
        int column = 0;

        try {
            historyGridPane.getColumnConstraints().clear();
            historyGridPane.getRowConstraints().clear();
            historyGridPane.getChildren().clear();

            // Reverse the allJobData list
            Collections.reverse(allJobData);

            for (JobData jobData : allJobData) {

                if (column == 1) {
                    column = 0;
                    row++;
                }

                StackPane pane = ViewFactory.getInstance().showHistoryGridView(row + 1, jobData);

                historyGridPane.add(pane, column++, row);
                GridPane.setMargin(pane, new Insets(2));
            }
        } catch (Exception e) {
            logger.severe("An error occurred while displaying the history grid box");
        }
    }

}
