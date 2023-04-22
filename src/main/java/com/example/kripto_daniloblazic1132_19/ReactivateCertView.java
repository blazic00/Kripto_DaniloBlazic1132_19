package com.example.kripto_daniloblazic1132_19;

import crypto.Crypto;
import crypto.Korisnik;
import crypto.MetaData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ReactivateCertView {

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button potvrdiButton;

    @FXML
    private Button noviNalogButton;

    @FXML
    private Label label;


    public void onPotvrdiButtonClick(ActionEvent actionEvent) throws IOException {
        Stage currentStage = (Stage) potvrdiButton.getScene().getWindow();
        String username="";
        String password="";
        try {
            username = usernameTextField.getText();
            password = passwordField.getText();
        }
        catch (NullPointerException e){
            label.setText("Niste unijeli sve podatke!");
            return;
        }
        if(username.isEmpty()||password.isEmpty()){
            label.setText("Niste unijeli sve podatke!");
            return;
        }
        if(!username.equals(Korisnik.getCurrentUser())){
            label.setText("Pogresno unijeto korisnicko ime ili lozinka!");
            return;
        }
        else{
            if(Crypto.verifyPassword(password,username) == 0){
                label.setText("Pogresno unijeto korisnicko ime ili lozinka!");
                return;
            }
            else{
                MyStage.createStage("upload_download-view.fxml");
                Crypto.reactivateUserCert(Korisnik.getCurrentUser());
            }
        }
        currentStage.close();
    }

    public void onNoviNalogButtonClick(ActionEvent actionEvent) {
    }
}
