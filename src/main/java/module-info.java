module com.example.javafxlearning {
    requires javafx.controls;
    requires javafx.fxml;
    requires LogAnalysis;


    opens com.example.javafxlearning to javafx.fxml;
    exports com.example.javafxlearning;
    exports com.example.javafxlearning.controller;
    opens com.example.javafxlearning.controller to javafx.fxml;
}