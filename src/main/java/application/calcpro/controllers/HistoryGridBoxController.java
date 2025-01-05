package application.calcpro.controllers;

import application.calcpro.models.JobData;
import application.calcpro.viewfactory.ViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class HistoryGridBoxController implements Initializable {

    private int id;
    private JobData jobData;

    @FXML
    private TextField dateAdded;

    @FXML
    private Label jobName;

    @FXML
    private Label serialId;

    @FXML
    private Label totalJobPrice;

    @FXML
    private Button viewDetails;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.dateAdded.setText(jobData.getDateAdded().toString());
        this.jobName.setText(jobData.getJobName());
        this.serialId.setText(String.valueOf(id));
        this.totalJobPrice.setText(String.valueOf(jobData.getTotalJobPrice()));

        viewDetails.setOnAction(e -> {
            // Open the details window
            ViewFactory.getInstance().showDetailsWindow(jobData);
        });
    }


    public void setJobData(JobData jobData) {
        this.jobData = jobData;
    }

    public void setId(int id) {
        this.id = id;
    }
}
