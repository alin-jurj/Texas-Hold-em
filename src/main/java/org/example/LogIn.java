package org.example;

import exceptions.CompleteLoginDataException;
import exceptions.ConfirmPasswordException;
import exceptions.UsernameAlreadyExistsException;
import exceptions.WrongPasswordException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import services.UserService;

import java.io.IOException;

public class LogIn {
    private static String username;
    private Image img;
    @FXML
    private Button button_log;
    @FXML
    private Button button_reg;
    @FXML
    private Label wrongLogIn;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ImageView bimg;

    public void initialize()
    {
        img= new Image(getClass().getResourceAsStream("/img/Loginimage.png"));
        bimg.setImage(img);
        App.resizeStage(650, 500);
    }
    @FXML
    public void handleLogIn() {
        App m = new App();
        try {
            if (UserService.checkCredentials(usernameField.getText(), passwordField.getText()).equals("Player")){
                wrongLogIn.setText("You have logged in successfully!");
                username = usernameField.getText();
                if(UserService.getStatus(username).equals("VIP")){
                    UserService.giveUserMoney(username, 10000);
                }else if(UserService.getStatus(username).equals(username)){
                    UserService.giveUserMoney(username, 1000);
                }
                m.changeScene("afterLogin.fxml");

                //AfterLogin controller=App.getPath().getController();
                //controller.setHelloMessage("Welcome "+ usernameFieldLogin.getText());

            }
            else if (UserService.checkCredentials(usernameField.getText(), passwordField.getText()).equals("Administrator")) {
                wrongLogIn.setTextFill(Color.web("#008000", 0.8));
                wrongLogIn.setText("You have logged in successfully!");

                m.changeScene("Admin.fxml");

                //StudentController controller=Main.getPath().getController();
                //controller.setHelloMessage("Welcome "+usernameFieldLogin.getText());
            }

            else {
                wrongLogIn.setTextFill(Color.web("#ef0c0c", 0.8));
                wrongLogIn.setText("Wrong username or password. Please try again!");
            }

        }
        catch (CompleteLoginDataException e) {
            wrongLogIn.setTextFill(Color.web("#ef0c0c", 0.8));
            wrongLogIn.setText("Please complete all log in fields!");
        }
        catch (Exception e) {
            System.out.println("Hm");
            System.out.println(e.getMessage());
        }


    }
    public static String getUsername(){
        return username;
    }
    @FXML
    public void userRegister() throws IOException{
        App m= new App();
        m.changeScene("Register.fxml");
    }

}