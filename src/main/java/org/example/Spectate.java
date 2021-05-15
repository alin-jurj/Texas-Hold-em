package org.example;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class Spectate {
    @FXML
    private Text text1;
    @FXML
    private Text text2;
    @FXML
    private Text text3;
    @FXML
    private Text text4;
    @FXML
    private Text text5;
    @FXML
    private ImageView bimg;
    private Image img;

    public void initialize(){
        App.resizeStage(700, 480);
        img = new Image(getClass().getResourceAsStream("/img/Loginimage.png"));
        bimg.setImage(img);
    }


}
