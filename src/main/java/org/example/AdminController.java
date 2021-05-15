package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.User;

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

    public void checkData(){
        if(username.getText().isEmpty()){
            username.setText("Introduceti un username!");
        }
        if(suma.getText().isEmpty()){
            suma.setText("Introduceti o suma!");
        }
        if(!isInt(suma.getText())){
            suma.setText("Introduceti un numar intreg!");
        }
    }
}
