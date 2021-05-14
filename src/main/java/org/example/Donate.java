package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.regex.Pattern;

public class Donate {
    @FXML
    private ImageView bimg;

    @FXML
    private TextField cardNumber, cardHolderName, CVC, amount;

    @FXML
    private ChoiceBox<Integer> month, year;

    @FXML
    private Button submit;
    @FXML
    private Label wrongmessage;

    private Pattern cardNumberRegex = Pattern.compile("\\d{16}");
    private Pattern CVCRegex = Pattern.compile("\\d{3}");
    private Pattern amountRegex = Pattern.compile("([0-9]*[.])?[0-9]+");
    private App m=new App();
    public void initialize() throws IOException {
        bimg.setImage(new Image(getClass().getResourceAsStream("/img/Loginimage.png")));
        month.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        year.getItems().addAll(2021, 2022, 2023, 2024, 2025, 2026, 2027, 2028, 2029, 2030);

        submit.setOnMouseClicked(mouseEvent -> {
            int status = validate();
            if (status == -1) {
                // TODO: 5/12/2021 Donated
                wrongmessage.setTextFill(Color.web("#008000", 0.8));
                wrongmessage.setText("Donated");
                try {
                    App.setRoot("afterLogin");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                // Error Handler
                switch (status) {
                    case 0:
                        // TODO: 5/12/2021 Not a valid card number
                        wrongmessage.setTextFill(Color.web("#008000", 0.8));
                        wrongmessage.setText("Not a valid card number");
                        break;
                    case 1:
                        // TODO: 5/12/2021 Not a valid CVC
                        wrongmessage.setTextFill(Color.web("#008000", 0.8));
                        wrongmessage.setText("Not a valid CVC");
                        break;
                    case 2:
                        // TODO: 5/12/2021 Not a valid amount
                        wrongmessage.setTextFill(Color.web("#008000", 0.8));
                        wrongmessage.setText("Not a valid amount");
                        break;
                }
            }
        });
    }

    public int validate() {
        if (!cardNumberRegex.matcher(cardNumber.getText()).matches()) return 0;
        if (!CVCRegex.matcher(CVC.getText()).matches()) return 1;
        if (!amountRegex.matcher(amount.getText()).matches()) return 2;

        return -1;
    }
}
