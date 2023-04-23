package com.example.kripto_daniloblazic1132_19;

import crypto.Crypto;
import crypto.Korisnik;
import crypto.MetaData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class UploadDownloadView {

    @FXML
    private ListView listView;

    @FXML
    private Label label;

    @FXML
    public void initialize() {
        updateListView();
    }

    private void updateListView(){
        ObservableList<String> items = FXCollections.observableArrayList();
        File folder = new File(MetaData.getPathToSignedFiles());
        File[] files = folder.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                if(Crypto.checkIfFileSignedByUser(file)) {
                    String pom=file.getName().split("\\.")[0]+"."+file.getName().split("\\.")[1];
                    items.add(pom);
                }
            }
        }
        listView.setItems(items);
    }
    public void onDownloadButtonClick(ActionEvent actionEvent) {

    }

    private File chooseFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        return fileChooser.showOpenDialog(null);
    }

    public void onUploadButtonClick(ActionEvent actionEvent) throws IOException {

        File selectedFile = chooseFile();
        if(selectedFile != null) {
            if(!checkIfFileWithSameNameExists(selectedFile)) {
                Crypto.signFile(Korisnik.getCurrentUser(), selectedFile);
                Crypto.encryptFile(selectedFile);
                updateListView();
            }
            else{
                label.setText("Fajl sa tim imenom vec postoji!");
            }
        }

    }
    public boolean checkIfFileWithSameNameExists(File file){
        ObservableList<String> items =  listView.getItems();
        for(String fileString : items){
            if(fileString.equals(file.getName()))
                return true;
        }
        return false;
    }
}
