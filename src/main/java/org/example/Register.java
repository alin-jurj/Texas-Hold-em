package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class Register {
    private Image img;
    @FXML
    private Button button_reg;
    @FXML
    private Label wrongPassword;
    @FXML
    private Label emptyFields;
    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField Confirm_password;
    @FXML
    private ImageView bimg;
    @FXML
    private ImageView clk;

    public void initialize()
    {
        img= new Image(getClass().getResourceAsStream("/img/Loginimage.png"));
        bimg.setImage(img);

        img= new Image(getClass().getResourceAsStream("/img/logo.png"));
        clk.setImage(img);
    }
    public void button_Register() throws IOException {
        App m= new App();
        if(username.getText().isEmpty()||email.getText().isEmpty()||password.getText().isEmpty()||Confirm_password.getText().isEmpty())
            emptyFields.setText("Fields cannot be empty");
        else if (password.getText().toString().equals(Confirm_password.getText().toString())==false) {
            wrongPassword.setText("Password didn't match.");
        }

        else if(password.getText().toString().equals(Confirm_password.getText().toString())) {
            m.changeScene("LogIn.fxml");
        }

       /* emptyFields.setText(" ");
        wrongPassword.setText(" ");
        */
    }

}
