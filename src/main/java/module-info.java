module application.calcpro {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires java.logging;
    requires de.jensd.fx.glyphs.fontawesome;


    opens application.calcpro to javafx.fxml;
    exports application.calcpro;
    exports application.calcpro.models;
    exports application.calcpro.controllers;
    opens application.calcpro.controllers to javafx.fxml;
    exports application.calcpro.database;
    opens application.calcpro.database to javafx.fxml;
}