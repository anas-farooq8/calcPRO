package application.calcpro.models;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class JobData implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String jobName;
    private int totalJobQuantity;
    private double laborRate;
    private double printerRate;
    private double electricityRate;
    private double designTime;
    private double programmingTime;
    private double totalPrintTime;
    private double postProcessingTime;
    private String filamentType;
    private double filamentCost;
    private double filamentUsed;
    private double miscExpenses;
    private double materialMarkup;

    // Job Price Breakdown
    private String designPrice;
    private String slicingPrice;
    private String postProcessingPrice;
    private String materialPrice;
    private String electricityPrice;
    private String printingPrice;

    // Final Pricing
    private String totalJobPrice;
    private String perPiecePrice;
    private LocalDate dateAdded;

    public JobData() {
        // Default constructor
    }

    JobData(String jobName, int totalJobQuantity, double laborRate, double printerRate, double electricityRate, double designTime, double programmingTime,
            double totalPrintTime, double postProcessingTime, String filamentType, double filamentCost, double filamentUsed, double miscExpenses,
            double materialMarkup, String designPrice, String slicingPrice, String postProcessingPrice, String materialPrice, String electricityPrice,
            String printingPrice, String totalJobPrice, String perPiecePrice) {
        this.jobName = jobName;
        this.totalJobQuantity = totalJobQuantity;
        this.laborRate = laborRate;
        this.printerRate = printerRate;
        this.electricityRate = electricityRate;
        this.designTime = designTime;
        this.programmingTime = programmingTime;
        this.totalPrintTime = totalPrintTime;
        this.postProcessingTime = postProcessingTime;
        this.filamentType = filamentType;
        this.filamentCost = filamentCost;
        this.filamentUsed = filamentUsed;
        this.miscExpenses = miscExpenses;
        this.materialMarkup = materialMarkup;

        // Job Price Breakdown
        this.designPrice = designPrice;
        this.slicingPrice = slicingPrice;
        this.postProcessingPrice = postProcessingPrice;
        this.materialPrice = materialPrice;
        this.electricityPrice = electricityPrice;
        this.printingPrice = printingPrice;

        // Final Price
        this.totalJobPrice = totalJobPrice;
        this.perPiecePrice = perPiecePrice;
        this.dateAdded = LocalDate.now();
    }

    // All Setters
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }
    public void setTotalJobQuantity(int totalJobQuantity) {
        this.totalJobQuantity = totalJobQuantity;
    }
    public void setLaborRate(double laborRate) {
        this.laborRate = laborRate;
    }
    public void setPrinterRate(double printerRate) {
        this.printerRate = printerRate;
    }
    public void setElectricityRate(double electricityRate) {
        this.electricityRate = electricityRate;
    }
    public void setDesignTime(double designTime) {
        this.designTime = designTime;
    }
    public void setProgrammingTime(double programmingTime) {
        this.programmingTime = programmingTime;
    }
    public void setTotalPrintTime(double totalPrintTime) {
        this.totalPrintTime = totalPrintTime;
    }
    public void setPostProcessingTime(double postProcessingTime) {
        this.postProcessingTime = postProcessingTime;
    }
    public void setFilamentType(String filamentType) {
        this.filamentType = filamentType;
    }
    public void setFilamentCost(double filamentCost) {
        this.filamentCost = filamentCost;
    }
    public void setFilamentUsed(double filamentUsed) {
        this.filamentUsed = filamentUsed;
    }
    public void setMiscExpenses(double miscExpenses) {
        this.miscExpenses = miscExpenses;
    }
    public void setMaterialMarkup(double materialMarkup) {
        this.materialMarkup = materialMarkup;
    }
    public void setDesignPrice(String designPrice) {
        this.designPrice = designPrice;
    }
    public void setSlicingPrice(String slicingPrice) {
        this.slicingPrice = slicingPrice;
    }
    public void setPostProcessingPrice(String postProcessingPrice) {
        this.postProcessingPrice = postProcessingPrice;
    }
    public void setMaterialPrice(String materialPrice) {
        this.materialPrice = materialPrice;
    }
    public void setElectricityPrice(String electricityPrice) {
        this.electricityPrice = electricityPrice;
    }
    public void setPrintingPrice(String printingPrice) {
        this.printingPrice = printingPrice;
    }
    public void setTotalJobPrice(String totalJobPrice) {
        this.totalJobPrice = totalJobPrice;
    }
    public void setPerPiecePrice(String perPiecePrice) {
        this.perPiecePrice = perPiecePrice;
    }
    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    // All Getters
    public String getJobName() {
        return jobName;
    }
    public int getTotalJobQuantity() {
        return totalJobQuantity;
    }
    public double getLaborRate() {
        return laborRate;
    }
    public double getPrinterRate() {
        return printerRate;
    }
    public double getElectricityRate() {
        return electricityRate;
    }
    public double getDesignTime() {
        return designTime;
    }
    public double getProgrammingTime() {
        return programmingTime;
    }
    public double getTotalPrintTime() {
        return totalPrintTime;
    }
    public double getPostProcessingTime() {
        return postProcessingTime;
    }
    public String getFilamentType() {
        return filamentType;
    }
    public double getFilamentCost() {
        return filamentCost;
    }
    public double getFilamentUsed() {
        return filamentUsed;
    }
    public double getMiscExpenses() {
        return miscExpenses;
    }
    public double getMaterialMarkup() {
        return materialMarkup;
    }
    public String getDesignPrice() {
        return designPrice;
    }
    public String getSlicingPrice() {
        return slicingPrice;
    }
    public String getPostProcessingPrice() {
        return postProcessingPrice;
    }
    public String getMaterialPrice() {
        return materialPrice;
    }
    public String getElectricityPrice() {
        return electricityPrice;
    }
    public String getPrintingPrice() {
        return printingPrice;
    }
    public String getTotalJobPrice() {
        return totalJobPrice;
    }
    public String getPerPiecePrice() {
        return perPiecePrice;
    }
    public LocalDate getDateAdded() {
        return dateAdded;
    }
}

