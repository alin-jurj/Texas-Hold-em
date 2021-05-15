package org.example;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import services.UserService;

import java.io.IOException;

public class AfterLogin {
    private static int entryAmount1;
    private static int entryAmount2;
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
    private Label money_txt;
    @FXML
    private Label money_int;
    @FXML
    private ChoiceBox<Integer> entryPlay = new ChoiceBox<>();
    @FXML
    private ChoiceBox<Integer> entrySpectate = new ChoiceBox<>();
    private UserService userr;
    private static String username;
    public void initialize(){
        App.resizeStage(700, 480);
        img = new Image(getClass().getResourceAsStream("/img/Loginimage.png"));
        bimg.setImage(img);

        img = new Image(getClass().getResourceAsStream("/img/chips-cropped.png"));
        playop.setImage(img);

        img = new Image(getClass().getResourceAsStream("/img/spectate.png"));
        spectateop.setImage(img);

        img = new Image(getClass().getResourceAsStream("/img/moneydonate.png"));
        donateop.setImage(img);

        entrySpectate.setValue(500);
        entryPlay.setValue(500);
        addValues(entryPlay);
        addValues(entrySpectate);
        username=LogIn.getUsername();
        money_txt.setText("Money");
        money_int.setText(String.valueOf(userr.getUserMoney(username)));
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
    @FXML
    public void Donatebutton() throws  IOException{
        App m= new App();
        m.changeScene("Donate.fxml");
    }
    public int getEntryPlay(){ return entryAmount1; }
    public void Spectatebutton() throws IOException
    {
        getChoice(entrySpectate);

        App m= new App();
        m.changeScene("Spectate.fxml");
    }
    public int getEntrySpectate(){ return entryAmount2; }
}