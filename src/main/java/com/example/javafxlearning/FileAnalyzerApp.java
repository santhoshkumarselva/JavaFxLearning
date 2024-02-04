package com.example.javafxlearning;

import com.example.javafxlearning.controller.FileAnalyzerController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;
import javafx.scene.control.TextArea;
import java.io.File;
import java.io.IOException;

import com.santhosh.loganalysis.RegularExpressionMatcher;

public class FileAnalyzerApp extends Application {

    File selectedFile;
    String selectedCategory;

    RegularExpressionMatcher rg;

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Create buttons and other UI elements
        Button selectFileButton = new Button("Select File");
        ComboBox<String> categoryDropdown = new ComboBox<>();
        categoryDropdown.getItems().addAll("wired carplay", "wireless carplay");
        Button selectCategoryButton = new Button("Select Category");
        Button analyzeButton = new Button("Analyse");
        TextArea fileContentsArea = new TextArea();
        fileContentsArea.setPrefSize(400, 600); // Set the preferred width to 400 and height to 300
        // File chooser
        FileChooser fileChooser = new FileChooser();
        rg = new RegularExpressionMatcher();
        // Event handlers
        selectFileButton.setOnAction(e -> {
            selectedFile = fileChooser.showOpenDialog(primaryStage);
            // Store the selected file path
            if (selectedFile != null) {
                String filePath = selectedFile.getAbsolutePath();
                System.out.println(filePath);
                System.out.println(filePath.replace("\\", "\\\\"));
                filePath = filePath.replace("\\", "\\\\");
                // You can use filePath as needed
            }
        });

        selectCategoryButton.setOnAction(e -> {
            // Store the selected category
            selectedCategory = categoryDropdown.getValue();
            switch(selectedCategory) {
                case "wired carplay":
                    selectedCategory = "wired_carplay_connection.xml";
                    break;
                case "wireless carplay":
                    selectedCategory = "wireless_carplay_connection.xml";
                    break;
            }
            // You can use selectedCategory as needed
        });

        analyzeButton.setOnAction(e -> {
            // Perform analysis based on the selected file and category
            if (selectedFile != null && selectedCategory != null) {
                // Call the analyse function using rg variable
                String analysisResult = null;
                try {
                    System.out.println(selectedFile.getAbsolutePath().replace("\\", "\\\\") + selectedCategory);
                    ClassLoader classLoader = FileAnalyzerApp.class.getClassLoader();
                    File xmlFile = new File(classLoader.getResource(selectedCategory).getFile());
                    analysisResult = rg.analyse(selectedFile.getAbsolutePath().replace("\\", "\\\\"), xmlFile);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                // Update the fileContentsArea with specific contents of the file
                fileContentsArea.setText(analysisResult);
            }
        });

        // Layout
        VBox root = new VBox(10);
        root.getChildren().addAll(selectFileButton, categoryDropdown, selectCategoryButton, analyzeButton, fileContentsArea);

        // Scene
        Scene scene = new Scene(root, 400, 300);

        // Stage


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/javafxlearning/view/FileAnalyzerView.fxml"));
        Parent root1 = loader.load();

        // Set the controller for the loaded FXML
        FileAnalyzerController controller = loader.getController();
        Scene scene1 = new Scene(root1);
        primaryStage.setScene(scene1);
        primaryStage.setTitle("Sample App");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}