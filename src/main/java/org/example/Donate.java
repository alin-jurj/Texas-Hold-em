package org.example;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;

public class Donate {
    @FXML
    private ImageView bimg;

    private TextField cardNumber;
    
    
    public void initialize() {
        bimg.setImage(new Image(getClass().getResourceAsStream("/img/Loginimage.png")));
    }
    
}
