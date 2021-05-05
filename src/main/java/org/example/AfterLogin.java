package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class AfterLogin {
    private Image img;
    @FXML
    private Button logout;
    @FXML
    private ImageView bimg;
    @FXML
    private ImageView playop;
    @FXML
    private ImageView spectateop;

    public void initialize(){
        img = new Image(getClass().getResourceAsStream("/img/Loginimage.png"));
        bimg.setImage(img);

        img = new Image(getClass().getResourceAsStream("/img/chips-cropped.png"));
        playop.setImage(img);

        img = new Image(getClass().getResourceAsStream("/img/chips-cropped.png"));
        spectateop.setImage(img);


    }
    public void userLogOut(ActionEvent event) throws IOException{
        App m=new App();
        m.changeScene("LogIn.fxml");
    }
}
