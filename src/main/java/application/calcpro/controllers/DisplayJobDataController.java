package application.calcpro.controllers;

import application.calcpro.models.JobData;
import application.calcpro.viewfactory.ViewFactory;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DisplayJobDataController implements Initializable {

    private JobData jobData;

    @FXML
    private TextField jobNameField;
    @FXML private Spinner<Integer> totalJobQuantitySpinner;
    @FXML private Spinner<Double> laborRateSpinner;
    @FXML private Spinner<Double> printerRateSpinner;
    @FXML private Spinner<Double> electricityRateSpinner;
    @FXML private Spinner<Double> designTimeSpinner;
    @FXML private Spinner<Double> programmingTimeSpinner;
    @FXML private Spinner<Double> totalPrintTimeSpinner;
    @FXML private Spinner<Double> postProcessingTimeSpinner;
    @FXML private ComboBox<String> filamentTypeComboBox;
    @FXML private Spinner<Double> filamentCostSpinner;
    @FXML private Spinner<Double> filamentUsedSpinner;
    @FXML private Spinner<Double> miscExpensesSpinner;
    @FXML private Slider materialMarkupSlider;
    @FXML private Label materialMarkupLabel;

    @FXML private Label designLabel;
    @FXML private Label slicingLabel;
    @FXML private Label postProcessingLabel;
    @FXML private Label materialLabel;
    @FXML private Label electricityLabel;
    @FXML private Label printingLabel;
    @FXML private Label totalJobPriceLabel;
    @FXML private Label perPiecePriceLabel;

    @FXML private FontAwesomeIconView backBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.jobNameField.setText(jobData.getJobName());
        this.totalJobQuantitySpinner.getValueFactory().setValue(jobData.getTotalJobQuantity());
        this.laborRateSpinner.getValueFactory().setValue(jobData.getLaborRate());
        this.printerRateSpinner.getValueFactory().setValue(jobData.getPrinterRate());
        this.electricityRateSpinner.getValueFactory().setValue(jobData.getElectricityRate());
        this.designTimeSpinner.getValueFactory().setValue(jobData.getDesignTime());
        this.programmingTimeSpinner.getValueFactory().setValue(jobData.getProgrammingTime());
        this.totalPrintTimeSpinner.getValueFactory().setValue(jobData.getTotalPrintTime());
        this.postProcessingTimeSpinner.getValueFactory().setValue(jobData.getPostProcessingTime());
        this.filamentTypeComboBox.setValue(jobData.getFilamentType());
        this.filamentCostSpinner.getValueFactory().setValue(jobData.getFilamentCost());
        this.filamentUsedSpinner.getValueFactory().setValue(jobData.getFilamentUsed());
        this.miscExpensesSpinner.getValueFactory().setValue(jobData.getMiscExpenses());
        this.materialMarkupSlider.setValue(jobData.getMaterialMarkup());
        this.materialMarkupLabel.setText(String.format("Material Markup (%.0f%%)", jobData.getMaterialMarkup()));

        this.designLabel.setText(String.valueOf(jobData.getDesignPrice()));
        this.slicingLabel.setText(String.valueOf(jobData.getSlicingPrice()));
        this.postProcessingLabel.setText(String.valueOf(jobData.getPostProcessingPrice()));
        this.materialLabel.setText(String.valueOf(jobData.getMaterialPrice()));
        this.electricityLabel.setText(String.valueOf(jobData.getElectricityPrice()));
        this.printingLabel.setText(String.valueOf(jobData.getPrintingPrice()));

        this.totalJobPriceLabel.setText(String.valueOf(jobData.getTotalJobPrice()));
        this.perPiecePriceLabel.setText(String.valueOf(jobData.getPerPiecePrice()));

        backBtn.setOnMouseClicked(e -> {
            Stage stage = (Stage) backBtn.getScene().getWindow();
            ViewFactory.getInstance().closeStage(stage);
        });

    }

    public void setJobData(JobData jobData) {
        this.jobData = jobData;
    }

}
