package org.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class BetController {
    @FXML
    private ImageView betBCKG;
    @FXML
    private TextField amount;
    @FXML
    private Button bet;
    @FXML
    private Button cancel;

    private Image img;
    private int betAmount;

    @FXML
    public void initialize(){
        App.resizeStage(514, 350);
        img = new Image(getClass().getResourceAsStream("/img/betbckg.jpg"));
        betBCKG.setImage(img);
    }

    public boolean isInt(String s){
        int i;
        try{
            i = Integer.parseInt(s);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

    public void checkBet() throws IOException {
        if(amount.getText().isEmpty()){
            amount.setText("Introduceti o suma!");
        }else if(!isInt(amount.getText())){
            amount.setText("Introduceti un numar intreg!");
        }else{
            betAmount = Integer.parseInt(amount.getText());
            System.out.println(betAmount);
            App m = new App();
            m.changeScene("Spectate.fxml");
        }
    }

    public int getBetAmount(){
        return betAmount;
    }

    public void close() throws IOException {
        App m = new App();
        m.changeScene("Spectate.fxml");
    }

}
