package org.example;

//import com.sun.javafx.charts.Legend;
import exceptions.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import services.UserService;

import java.io.IOException;

public class Register {
    private Image img;
    @FXML
    private Text Rolemessege;
    @FXML
    private Button button_reg;
    @FXML
    private Label registrationMessage;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField email;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmpasswordField;
    @FXML
    private ChoiceBox role;
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

        Rolemessege.setText("Select your role");
        role.getItems().addAll("Player", "Administrator");
    }
    @FXML
    public void handleRegisterAction() throws IOException {
        try {

            UserService.addUser(usernameField.getText(), passwordField.getText(),confirmpasswordField.getText(), (String) role.getValue(),email.getText());
            //System.out.println("Account created successfully");
            registrationMessage.setTextFill(Color.web("#008000", 0.8));
            registrationMessage.setText("Account created successfully!");
            App.setRoot("LogIn");
        }
        catch (CompleteAllFieldsException e) {
            //System.out.println("Complete all fields");
            registrationMessage.setTextFill(Color.web("#ef0808", 0.8));
            registrationMessage.setText("Please complete all fields");
        }
        catch (UsernameAlreadyExistsException e) {
            //System.out.println("Username already exists!");
            registrationMessage.setTextFill(Color.web("#ef0808", 0.8));
            registrationMessage.setText("Username already exists!");
            registrationMessage.setTextAlignment(TextAlignment.LEFT);
            registrationMessage.setWrapText(true);
        }
        catch (ConfirmPasswordException e) {
            //System.out.println("Password error!" + passwordField.getText() + " and " + confirmpasswordField.getText());
            registrationMessage.setTextFill(Color.web("#ef0808", 0.8));
            registrationMessage.setText("Please enter same password twice!");
        }
        catch (UserNameNotLongEnough e) {
            //System.out.println("Password error!" + passwordField.getText() + " and " + confirmpassword.getText());
            registrationMessage.setTextFill(Color.web("#ef0808", 0.8));
            registrationMessage.setText("Username must contain at least 6 characters!");
        }
        catch (PasswordNotLongEnough e) {
            //System.out.println("Password error!" + passwordField.getText() + " and " + confirmpassword.getText());
            registrationMessage.setTextFill(Color.web("#ef0808", 0.8));
            registrationMessage.setText("Password must contain at least 6 characters!");
        }
    }
    @FXML
    void button_back() throws IOException{
        App.setRoot("LogIn");
    }

}
