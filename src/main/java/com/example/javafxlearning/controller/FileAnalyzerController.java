package com.example.javafxlearning.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class FileAnalyzerController {

    @FXML
    Button fileButton;

    @FXML
    void chooseFile(ActionEvent e) {
        System.out.println("It's working");
    }
}
