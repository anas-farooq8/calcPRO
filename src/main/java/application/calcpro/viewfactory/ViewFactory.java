package application.calcpro.viewfactory;

import application.calcpro.App;
import application.calcpro.controllers.DisplayJobDataController;
import application.calcpro.controllers.HistoryGridBoxController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;
import application.calcpro.models.JobData;

public class ViewFactory {
    // For Exception Handling
    private static final Logger logger = Logger.getLogger(ViewFactory.class.getName());

    // Singleton instance
    private static ViewFactory viewFactory;

    private ViewFactory() {
        // private constructor to prevent instantiation
    }

    // Get instance method
    public static synchronized ViewFactory getInstance() {
        if (viewFactory == null) {
            viewFactory = new ViewFactory();
        }
        return viewFactory;
    }

    // Creates the Stage
    private void createStage(FXMLLoader loader, boolean isScrollable) {
        Scene scene = null;

        try {
            if(isScrollable)
                scene = new Scene(loader.load(), 720, 820);
            else
                scene = new Scene(loader.load());
        } catch (Exception e) {
            // Log the exception instead of printing the stack trace
            logger.log(Level.SEVERE, "An error occurred while creating the stage", e);
        }

        Stage stage = new Stage();
        if (scene != null) {
            stage.setScene(scene);
        } else {
            // Log a warning if the scene is null
            logger.warning("Scene is null. Stage creation failed.");
            return;
        }

        //stage.initStyle(StageStyle.TRANSPARENT);

        stage.setTitle("CalcPRO");
        stage.setResizable(false);
        stage.show();
    }

    public void closeStage(Stage stage) {
        stage.close();
    }


    // Methods to show different windows
    public void showMainWindow() {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("main-view.fxml"));
        createStage(loader, false);
    }

    public void showCalculationWindow() {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("calc-view.fxml"));
        createStage(loader, true);
    }

    public void showHistoryWindow() {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("history-view.fxml"));
        createStage(loader, false);
    }

    public StackPane showHistoryGridView(int id, JobData jobData) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("history-gridBox-view.fxml"));
            HistoryGridBoxController historyGridBoxController = new HistoryGridBoxController();
            loader.setController(historyGridBoxController);

            historyGridBoxController.setId(id);
            historyGridBoxController.setJobData(jobData);

            return loader.load();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "An error occurred while displaying the history grid view", e);
        }
        return null;
    }

    public void showDetailsWindow(JobData jobData) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("display-jobData-view.fxml"));
            DisplayJobDataController displayJobDataController = new DisplayJobDataController();
            loader.setController(displayJobDataController);

            displayJobDataController.setJobData(jobData);
            createStage(loader, true);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "An error occurred while displaying the history grid view", e);
        }
    }
}
