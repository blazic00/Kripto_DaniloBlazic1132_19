package com.example.kripto_daniloblazic1132_19;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private Button loginButton;

    @FXML
    private Button signUpButton;

    @FXML
    protected void onHelloButtonClick() {

        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void onloginButtonClick(ActionEvent actionEvent) throws IOException {
        Stage currentStage = (Stage) loginButton.getScene().getWindow();
        MyStage.createStage("sertifikat-view.fxml");

    }

    public void onSignUpButtonClick(ActionEvent actionEvent) throws IOException {
        Stage currentStage = (Stage) loginButton.getScene().getWindow();
        MyStage.createStage("signUp-view.fxml");
    }
}