module com.example.javafxlearning {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javafxlearning to javafx.fxml;
    exports com.example.javafxlearning;
}