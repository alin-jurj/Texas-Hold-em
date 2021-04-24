package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class AfterLogin {
    @FXML
    private Button logout;
    public void userLogOut(ActionEvent event) throws IOException{
        App m=new App();
        m.changeScene("LogIn.fxml");
    }
}
