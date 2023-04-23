package com.example.kripto_daniloblazic1132_19;

import crypto.Crypto;
import crypto.Korisnik;
import crypto.MetaData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;

public class UploadDownloadView {

    @FXML
    private ListView listView;
    public void onDownloadButtonClick(ActionEvent actionEvent) {
        ObservableList<String> items = FXCollections.observableArrayList();
        File folder = new File(MetaData.getPathToSignedFiles());
        File[] files = folder.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                if(Crypto.checkIfFileSignedByUser(file) == 0) {
                    items.add(file.getName());
                }
            }
        }
        listView.setItems(items);
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
