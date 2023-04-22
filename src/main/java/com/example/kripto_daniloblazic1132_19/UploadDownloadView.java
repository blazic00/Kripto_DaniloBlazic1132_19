package com.example.kripto_daniloblazic1132_19;

import crypto.Crypto;
import crypto.Korisnik;
import javafx.event.ActionEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;

public class UploadDownloadView {
    public void onDownloadButtonClick(ActionEvent actionEvent) {
    }

    private File chooseFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        return fileChooser.showOpenDialog(null);
    }

    public void onUploadButtonClick(ActionEvent actionEvent) {

        File selectedFile = chooseFile();
        if(selectedFile != null) {
            Crypto.signFile(Korisnik.getCurrentUser(), selectedFile);
            Crypto.encryptFile(selectedFile);
        }

    }
}
