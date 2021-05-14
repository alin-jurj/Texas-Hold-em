package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.User;
import services.UserService;

import java.io.IOException;

public class AdminController {
    @FXML
    private TextField username;
    @FXML
    private TextField suma;
    @FXML
    private Button confirm;
    @FXML
    private Button close;
    @FXML
    private Label transaction;

    @FXML
    public void initialize(){
        App.resizeStage(700, 500);
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

    @FXML
    public void checkData(){
        if(username.getText().isEmpty()){
            transaction.setText("Introduce a username!");
        }
        if(suma.getText().isEmpty()){
            transaction.setText("Introduce a valid value!");
        }
        if(!isInt(suma.getText())){
            transaction.setText("Introduce a valid value!");
        }if(!UserService.checkUserExist(username.getText())){
            transaction.setText("There is no user with this username!");
        }else{
            //UserService.giveUserMoney(username.getText(), Integer.parseInt(suma.getText()));
            transaction.setText("Succesful transaction!");
        }
    }

    public void Close() throws IOException {
        App.setRoot("LogIn");
    }
}
