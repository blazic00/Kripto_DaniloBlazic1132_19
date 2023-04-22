package com.example.kripto_daniloblazic1132_19;

import crypto.MetaData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.File;

public class SertifikatView {

    @FXML
    private ListView listView;


    public void initialize() {
        ListView<String> listView = new ListView<String>();
        ObservableList<String> items = FXCollections.observableArrayList();

        File folder = new File(MetaData.getPathToCerts());
        File[] files = folder.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                items.add(file.getName());
            }
        }
    }

    public void onPotvrdiButtonClick(ActionEvent actionEvent) {

    }
}
