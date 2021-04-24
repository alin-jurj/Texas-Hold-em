package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LogIn {

    @FXML
    private Button button_log;
    @FXML
    private Button button_reg;
    @FXML
    private Label wrongLogIn;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;


    public void userLogIn() throws IOException {
        checkLogin();
    }

    public void checkLogin() throws IOException{
        App m= new App();
        if(username.getText().toString().equals("admin") && password.getText().toString().equals("123")){
            wrongLogIn.setText("Succes!");
            m.changeScene("afterLogin.fxml");
        }
        else if(username.getText().isEmpty() && password.getText().isEmpty()){
                wrongLogIn.setText("Please enter your data.");
        }
        else {
            wrongLogIn.setText("Wrong username or password.");
        }
    }
    public void userRegister() throws IOException{
        App m= new App();
        m.changeScene("Register.fxml");
    }

}
