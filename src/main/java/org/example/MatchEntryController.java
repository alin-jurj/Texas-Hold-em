package org.example;
import javafx.collections.FXCollections;
import javafx.collections.ObservableIntegerArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

import java.io.IOException;
import java.util.ArrayList;

public class MatchEntryController{

    public int entryAmount;

    @FXML
    private ChoiceBox<Integer> entry = new ChoiceBox<>();

    public void addValues() {
        entry.getItems().add(500);
        entry.getItems().add(1000);
        entry.getItems().add(2500);
        entry.getItems().add(5000);
        entry.getItems().add(10000);
        entry.getItems().add(25000);
        entry.getItems().add(50000);
        entry.getItems().add(100000);
        entry.getItems().add(500000);
        entry.getItems().add(1000000);
    }
    @FXML
    public void initialize(){
        entry.setValue(500);
        addValues();
    }

   public void getChoice(ChoiceBox<Integer> entry){entryAmount = entry.getValue();
   }


   public void Continue() throws IOException {
        App m = new App();
        getChoice(entry);
        m.changeScene("match.fxml");
   }

   public int getEntry(){ return entryAmount; }
}
