package com.example.kripto_daniloblazic1132_19;

import crypto.Crypto;
import crypto.Korisnik;
import crypto.MetaData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class SertifikatView {

    @FXML
    private ListView listView;


    @FXML
    public void initialize() {
        ObservableList<String> items = FXCollections.observableArrayList();
        File folder = new File(MetaData.getPathToCerts());
        File[] files = folder.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                items.add(file.getName());
            }
        }
        listView.setItems(items);
    }

    public void onPotvrdiButtonClick(ActionEvent actionEvent) throws IOException {
        Stage currentStage = (Stage) listView.getScene().getWindow();
        ObservableList<String> selectedItems = listView.getSelectionModel().getSelectedItems();
        String userCert = selectedItems.get(0);
        if(Crypto.verifyUserCert(userCert) == 0){
                MyStage.createStage("login-view.fxml");
                String currentUsername = userCert.replace(".crt","");
                Korisnik.setCurrentUser(currentUsername);
        }
        else{
            //Sertifikat nije validan!
        }
        currentStage.close();
    }
}
