package application.calcpro.validator;

import application.calcpro.models.JobData;
import javafx.scene.control.Alert;

public class Validator {
    private static String OS = System.getProperty("os.name").toLowerCase();

    public static boolean isWindows() {
        return (OS.contains("win"));
    }

    public static boolean isUnix() {
        return (OS.contains("nix") || OS.contains("nux"));
    }

    public static boolean validateJobData(JobData jobData) {
        // Job name can't be null or empty
        if (jobData.getJobName() == null || jobData.getJobName().isEmpty()) {
            showErrorAlert("Invalid Job Name", "Job Name can't be empty.");
            return false;
        }

        // check if the filament type is selected
        if (jobData.getFilamentType() == null) {
            showErrorAlert("Invalid Filament Type", "Please select a filament type.");
            return false;
        }

        return true;
    }

    // Information Box
    public static void showInformationAlert(String title, String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        // Show the alert
        alert.showAndWait();
    }

    // Alert Box
    public static void showErrorAlert(String title, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        // Show the alert
        alert.showAndWait();
    }

}
