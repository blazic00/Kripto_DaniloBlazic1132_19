package com.example.kripto_daniloblazic1132_19;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginView {

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button potvrdiButton;
    public void onPotvrdiButtonClick(ActionEvent actionEvent) {
        String username = usernameTextField.getText();
        String password = passwordField.getText();



    }
}
