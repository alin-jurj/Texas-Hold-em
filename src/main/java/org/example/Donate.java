package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import services.UserService;

import java.io.IOException;
import java.util.regex.Pattern;

public class Donate {
    @FXML
    private ImageView bimg;

    @FXML
    private TextField cardNumber;
    @FXML
    private TextField cardHolderName;
    @FXML
    private TextField CVC;
    @FXML
    private TextField amount;

    @FXML
    private ChoiceBox<Integer> month;
    @FXML
    private ChoiceBox<Integer>year;

    @FXML
    private Button submit;
    @FXML
    private Label wrongmessage;

    private Pattern cardNumberRegex = Pattern.compile("\\d{16}");
    private Pattern CVCRegex = Pattern.compile("\\d{3}");
    private Pattern amountRegex = Pattern.compile("([0-9]*[.])?[0-9]+");
    private App m=new App();
    public void initialize() throws IOException {
        App.resizeStage(630, 440);
        bimg.setImage(new Image(getClass().getResourceAsStream("/img/Loginimage.png")));
        month.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        year.getItems().addAll(2021, 2022, 2023, 2024, 2025, 2026, 2027, 2028, 2029, 2030);

        submit.setOnMouseClicked(mouseEvent -> {
            int status = validate();
            if (status == -1) {
               // wrongmessage.setTextFill(Color.web("#008000", 0.8));
                wrongmessage.setText("Donated");
                UserService.giveUserMoney(LogIn.getUsername(),Integer.valueOf(amount.getText()));
                UserService.updateUserStatus(LogIn.getUsername(), "VIP");
                try {
                    App.setRoot("afterLogin");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                // Error Handler
                switch (status) {
                    case 0:
                        wrongmessage.setText("Not a valid card number");
                        break;
                    case 1:
                        wrongmessage.setText("Not a valid CVC");
                        break;
                    case 2:
                        wrongmessage.setText("Not a valid amount");
                        break;
                }
            }
        });
    }
    @FXML
    public void back()throws IOException{

        App.setRoot("afterLogin");
    }
    public int validate() {
        if (!cardNumberRegex.matcher(cardNumber.getText()).matches()) return 0;
        if (!CVCRegex.matcher(CVC.getText()).matches()) return 1;
        if (!amountRegex.matcher(amount.getText()).matches()) return 2;

        return -1;
    }
}
