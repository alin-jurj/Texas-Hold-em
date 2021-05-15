package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Random;

public class Spectate {
    @FXML
    private Label message;
    @FXML
    private Label moneytxt;
    @FXML
    private Label moneytxt1;
    @FXML
    private ImageView bimg;
    private Image img;
    @FXML
    private ChoiceBox<String> g1;
    @FXML
    private ChoiceBox<String> g2;
    @FXML
    private ChoiceBox<String> g3;
    @FXML
    private ChoiceBox<String> g4;
    @FXML
    private ChoiceBox<String> g5;
    private int money,aux;
    AfterLogin entry=new AfterLogin();
    public void initialize(){
        App.resizeStage(605, 435);
        img = new Image(getClass().getResourceAsStream("/img/Loginimage.png"));
        bimg.setImage(img);
        money= entry.getEntrySpectate();
        aux=money;
        moneytxt1.setText("Money");
        moneytxt.setText(String.valueOf(money));
        g1.getItems().addAll("Daniel Negreanu","Jason Marcier");
        g1.setValue("Daniel Negreanu");
        g2.getItems().addAll("Tom Dwan","Jake Cody");
        g2.setValue("Tom Dwan");
        g3.getItems().addAll("Victoria Coren","Davidi Kitai");
        g3.setValue("Victoria Coren");
        g4.getItems().addAll("Lex Veldhuis","Andre Akkari");
        g4.setValue("Lex Veldhuis");
        g5.getItems().addAll("Dan Murariu","Jason Young");
        g5.setValue("Dan Murariu");

    }

    @FXML
    public void Bet1()
    {
        Bet(6,"Daniel Negreanu","Jason Marcier",g1,7);
    }

    @FXML
    public void Bet2()
    {
        Bet(8,"Tom Dwan","Jake Cody",g2,8);
    }

    @FXML
    public void Bet3()
    {
        Bet(5,"Victoria Coren","Davidi Kitai",g3,5);
    }
    @FXML
    public void Bet4()
    {
        Bet(3,"Lex Veldhuis","Andre Akkari",g4,3);
    }
    @FXML
    public void Bet5()
    {
        Bet(9,"Dan Murariu","Jason Young",g5,9);
    }
    private void Bet(int x,String name1,String name2,ChoiceBox<String>g,int cota) {
        Random rand = new Random();
        int rand_int1;

            if (money < 500)
                message.setText("You don't have money.");
            else {

                rand_int1 = rand.nextInt(9);
                if (rand_int1 < x && g.getValue().toString().equals(name1)) {
                    message.setText("You won");
                    money = money + cota * aux / 100;
                    moneytxt.setText(String.valueOf(money));
                } else if (rand_int1 >= x && g.getValue().toString().equals(name2)) {
                    message.setText("You won");
                    money = money + 10 - cota * aux / 100;
                    moneytxt.setText(String.valueOf(money));
                } else {
                    message.setText("You lost");
                    money = money - aux;
                    moneytxt.setText(String.valueOf(money));
                }
            }
        }
    @FXML
    public void Menu() throws IOException {
        App m=new App();
        m.changeScene("afterLogin.fxml");
    }
}
