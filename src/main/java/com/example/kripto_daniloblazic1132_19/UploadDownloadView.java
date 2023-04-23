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
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
                if(checkIfFileSignedByUser(file)) {
                    String pom=file.getName().split("\\.")[0]+"."+file.getName().split("\\.")[1];
                    items.add(pom);
                }
            }
        }
        listView.setItems(items);
    }
    public void onDownloadButtonClick(ActionEvent actionEvent) {
        try {
            Stage currentStage = (Stage) listView.getScene().getWindow();
            ObservableList<String> selectedItems = listView.getSelectionModel().getSelectedItems();
            String selectedFileName = selectedItems.get(0);
            int res = Crypto.download(selectedFileName);
            if(res == 0){
                label.setText("Dokument je spreman za preuzimanje");
            }
            else{
                label.setText("Dokument nije prosao verifikaciju");
            }
        }
        catch (IndexOutOfBoundsException e){
            label.setText("Izaberite fajl!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
    public static boolean checkIfFileSignedByUser(File file) {
        if(file.getName().split("\\.")[2].equals(Korisnik.getCurrentUser()))
            return true;
        else
            return false;
    }
}
