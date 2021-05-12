package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AdminController {
    @FXML
    private TextField username;
    @FXML
    private TextField suma;
    @FXML
    private Button confirm;
    @FXML
    private Button close;

    App m = new App();
    @FXML
    public void initialize(){
        m.resizeStage(700, 500);
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
