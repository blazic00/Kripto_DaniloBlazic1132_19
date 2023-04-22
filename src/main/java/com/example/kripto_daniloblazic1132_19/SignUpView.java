package com.example.kripto_daniloblazic1132_19;

import crypto.Crypto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUpView {

    @FXML
    private TextField imeTextField;
    @FXML
    private TextField mailTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField pass1TextField;
    @FXML
    private PasswordField pass2TextField;

    @FXML
    private Label label;

    @FXML
    private Button potvrdiButton;

    public void onPotvrdiButtonClick(ActionEvent actionEvent) {
        Stage currentStage = (Stage) potvrdiButton.getScene().getWindow();
        String ime="";
        String mail="";
        String username="";
        String pass1="";
        String pass2="";

    try {
        ime = imeTextField.getText();
        mail = mailTextField.getText();
        username = usernameTextField.getText();
        pass1 = pass1TextField.getText();
        pass2 = pass2TextField.getText();
    }
    catch (NullPointerException e){
        label.setText("Niste unijeli sve podatke!");
        return;
    }
        if(!pass1.equals(pass2)){
            label.setText("Sifre se ne poklapaju!");
            return;
        }
        if(Crypto.checkIfUsernameExists(username)){
            label.setText("Korisnicko ime zauzeto!");
            return;
        }
        Crypto.createUser(ime,mail,username,pass1);


    }
}
