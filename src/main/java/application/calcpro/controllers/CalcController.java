package application.calcpro.controllers;

import application.calcpro.database.DatabaseOperations;
import application.calcpro.models.JobData;
import application.calcpro.validator.Validator;
import application.calcpro.viewfactory.ViewFactory;
import javafx.fxml.FXML;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.text.DecimalFormat;
import java.time.LocalDate;

public class CalcController {

    @FXML private TextField jobNameField;
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
    @FXML private Button saveBtn;

    private final DecimalFormat currencyFormat = new DecimalFormat("0.00 USD");

    @FXML
    public void initialize() {
        setupSpinners();
        setupListeners();

        saveBtn.setOnAction(e -> saveData());
        backBtn.setOnMouseClicked(e -> {
            Stage stage = (Stage) backBtn.getScene().getWindow();
            ViewFactory.getInstance().closeStage(stage);

            // Open the main window
            ViewFactory.getInstance().showMainWindow();
        });
    }

    private void updateDoubleSpinnerValue(Spinner<Double> spinner, String newValue) {
        try {
            double value = Double.parseDouble(newValue);
            spinner.getValueFactory().setValue(value);
        } catch (NumberFormatException e) {
            spinner.getValueFactory().setValue(0.0);
        }
    }

    private void setupDoubleSpinner(Spinner<Double> spinner) {
        spinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, Double.MAX_VALUE, 0, 1.0));
        TextFormatter<Double> formatter = new TextFormatter<>(new DoubleStringConverter(), 0.0, change -> {
            String newText = change.getControlNewText();
            if (newText.matches("([0-9]*\\.?[0-9]?[0-9]?)")) {
                if(Double.parseDouble(newText) > 1_000_000.0) {
                    return null;
                }
                return change;
            }
            return null;
        });
        spinner.getEditor().setTextFormatter(formatter);
        spinner.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                updateDoubleSpinnerValue(spinner, newValue);
            }
            updateCalculations();
        });
    }

    private void setupIntegerSpinner(Spinner<Integer> spinner) {
        spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 0, 1));
        TextFormatter<Integer> formatter = new TextFormatter<>(new IntegerStringConverter(), 0, change -> {
            String newText = change.getControlNewText();
            if (newText.matches("[0-9]*")) {
                if(Integer.parseInt(newText) > 1_000_000) {
                    return null;
                }
                return change;
            }
            return null;
        });
        spinner.getEditor().setTextFormatter(formatter);
        spinner.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                updateIntegerSpinnerValue(spinner, newValue);
            }
            updateCalculations();
        });
    }

    private void updateIntegerSpinnerValue(Spinner<Integer> spinner, String newValue) {
        try {
            int value = Integer.parseInt(newValue);
            spinner.getValueFactory().setValue(value);
        } catch (NumberFormatException e) {
            spinner.getValueFactory().setValue(0);
        }
    }

    private void setupSpinners() {
        setupDoubleSpinner(laborRateSpinner);
        setupDoubleSpinner(printerRateSpinner);
        setupDoubleSpinner(electricityRateSpinner);
        setupDoubleSpinner(designTimeSpinner);
        setupDoubleSpinner(programmingTimeSpinner);
        setupDoubleSpinner(totalPrintTimeSpinner);
        setupDoubleSpinner(postProcessingTimeSpinner);
        setupDoubleSpinner(filamentCostSpinner);
        setupDoubleSpinner(filamentUsedSpinner);
        setupDoubleSpinner(miscExpensesSpinner);
        setupIntegerSpinner(totalJobQuantitySpinner);
    }

    private void setupListeners() {
        materialMarkupSlider.valueProperty().addListener((obs, oldValue, newValue) -> {
            materialMarkupLabel.setText(String.format("Material Markup (%.0f%%)", newValue));
            updateCalculations();
        });
    }

    private void updateCalculations() {
        // Implement calculation logic here
        double laborRate = laborRateSpinner.getValue();
        double designTime = designTimeSpinner.getValue();
        double programmingTime = programmingTimeSpinner.getValue();
        double postProcessingTime = postProcessingTimeSpinner.getValue();
        double filamentCost = filamentCostSpinner.getValue();
        double filamentUsed = filamentUsedSpinner.getValue();
        double miscExpenses = miscExpensesSpinner.getValue();
        double materialMarkup = materialMarkupSlider.getValue();
        double electricityRate = electricityRateSpinner.getValue();
        double totalPrintTime = totalPrintTimeSpinner.getValue();
        double printerRate = printerRateSpinner.getValue();
        int totalJobQuantity = totalJobQuantitySpinner.getValue();

        double design = laborRate * designTime;
        double slicing = laborRate * programmingTime;
        double postProcessing = laborRate * postProcessingTime;
        double filament = (filamentCost * filamentUsed) / 1000;
        double material = filament + (filament * materialMarkup / 100) + miscExpenses;
        double electricity = (electricityRate * totalPrintTime) / 10; // kW/hr
        double printing = printerRate * totalPrintTime;

        double totalJobPrice = design + slicing + postProcessing + material + electricity + printing;
        double perPiecePrice = totalJobQuantity > 0 ? totalJobPrice / totalJobQuantity : 0;

        // Update labels
        designLabel.setText(currencyFormat.format(design));
        slicingLabel.setText(currencyFormat.format(slicing));
        postProcessingLabel.setText(currencyFormat.format(postProcessing));
        materialLabel.setText(currencyFormat.format(material));
        electricityLabel.setText(currencyFormat.format(electricity));
        printingLabel.setText(currencyFormat.format(printing));
        totalJobPriceLabel.setText(currencyFormat.format(totalJobPrice));
        perPiecePriceLabel.setText(currencyFormat.format(perPiecePrice));
    }


    // Add this method in the appropriate class
    private void saveData() {
        JobData jobData = new JobData();
        jobData.setJobName(jobNameField.getText());
        jobData.setTotalJobQuantity(totalJobQuantitySpinner.getValue());
        jobData.setLaborRate(laborRateSpinner.getValue());
        jobData.setPrinterRate(printerRateSpinner.getValue());
        jobData.setElectricityRate(electricityRateSpinner.getValue());
        jobData.setDesignTime(designTimeSpinner.getValue());
        jobData.setProgrammingTime(programmingTimeSpinner.getValue());
        jobData.setTotalPrintTime(totalPrintTimeSpinner.getValue());
        jobData.setPostProcessingTime(postProcessingTimeSpinner.getValue());
        jobData.setFilamentType(filamentTypeComboBox.getValue());
        jobData.setFilamentCost(filamentCostSpinner.getValue());
        jobData.setFilamentUsed(filamentUsedSpinner.getValue());
        jobData.setMiscExpenses(miscExpensesSpinner.getValue());
        jobData.setMaterialMarkup(materialMarkupSlider.getValue());

        // Job Price Breakdown
        jobData.setDesignPrice(designLabel.getText());
        jobData.setSlicingPrice(slicingLabel.getText());
        jobData.setPostProcessingPrice(postProcessingLabel.getText());
        jobData.setMaterialPrice(materialLabel.getText());
        jobData.setElectricityPrice(electricityLabel.getText());
        jobData.setPrintingPrice(printingLabel.getText());

        // Final Pricing
        jobData.setTotalJobPrice(totalJobPriceLabel.getText());
        jobData.setPerPiecePrice(perPiecePriceLabel.getText());
        jobData.setDateAdded(LocalDate.now());

        // Method to save the data to your database
        if(!Validator.validateJobData(jobData)) {
            return;
        }
        DatabaseOperations.addJobData(jobData);
        Validator.showInformationAlert("Success", "Data saved successfully!");
        clearFields();
    }

    private void clearFields() {
        jobNameField.clear();
        totalJobQuantitySpinner.getValueFactory().setValue(0);
        laborRateSpinner.getValueFactory().setValue(0.0);
        printerRateSpinner.getValueFactory().setValue(0.0);
        electricityRateSpinner.getValueFactory().setValue(0.0);
        designTimeSpinner.getValueFactory().setValue(0.0);
        programmingTimeSpinner.getValueFactory().setValue(0.0);
        totalPrintTimeSpinner.getValueFactory().setValue(0.0);
        postProcessingTimeSpinner.getValueFactory().setValue(0.0);
        filamentTypeComboBox.setValue(null);
        filamentCostSpinner.getValueFactory().setValue(0.0);
        filamentUsedSpinner.getValueFactory().setValue(0.0);
        miscExpensesSpinner.getValueFactory().setValue(0.0);
        materialMarkupSlider.setValue(0);
    }
}