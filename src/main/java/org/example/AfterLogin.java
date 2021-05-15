package org.example;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class AfterLogin {
    public int entryAmount1;
    public int entryAmount2;
    private Image img;
    @FXML
    private Button logout;
    @FXML
    private ImageView bimg;
    @FXML
    private ImageView playop;
    @FXML
    private ImageView spectateop;
    @FXML
    private ImageView donateop;

    @FXML
    private ChoiceBox<Integer> entryPlay = new ChoiceBox<>();
    @FXML
    private ChoiceBox<Integer> entrySpectate = new ChoiceBox<>();
    public void initialize(){
        App.resizeStage(700, 480);
        img = new Image(getClass().getResourceAsStream("/img/Loginimage.png"));
        bimg.setImage(img);

        img = new Image(getClass().getResourceAsStream("/img/chips-cropped.png"));
        playop.setImage(img);

        img = new Image(getClass().getResourceAsStream("/img/spectate.png"));
        spectateop.setImage(img);

        //img = new Image(getClass().getResourceAsStream("/img/s.png"));
        //donateop.setImage(img);

        entrySpectate.setValue(500);
        entryPlay.setValue(500);
        addValues(entryPlay);
        addValues(entrySpectate);
    }
    public void addValues(ChoiceBox<Integer> entry) {
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
    public void userLogOut(ActionEvent event) throws IOException{
        App m=new App();
        m.changeScene("LogIn.fxml");
    }
    public void getChoice(ChoiceBox<Integer> entry){
        entryAmount1 = entry.getValue();
        entryAmount2 = entry.getValue();
    }
    public void Playbutton() throws IOException
    {
        App m = new App();
        getChoice(entryPlay);
        m.changeScene("match.fxml");
    }
    public int getEntryPlay(){ return entryAmount1; }
    public void Spectatebutton() throws IOException
    {
        getChoice(entrySpectate);

//        App.changeScene("match.fxml");
        //m.changeScene("match.fxml");
    }
    public int getEntrySpectate(){ return entryAmount2; }
}
