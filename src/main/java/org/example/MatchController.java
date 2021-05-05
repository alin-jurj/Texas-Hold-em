package org.example;

import Game.mechanics.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MatchController {
    @FXML
    public ImageView my1;
    @FXML
    public ImageView my2;
    @FXML
    public ImageView op1;
    @FXML
    public ImageView op2;
    @FXML
    public Label bani;
    @FXML
    public ImageView table1;
    @FXML
    public ImageView table2;
    @FXML
    public ImageView table3;
    @FXML
    private ImageView table4;
    @FXML
    private ImageView table5;
    @FXML
    private ImageView toStart;
    @FXML
    private Label slabel;
    @FXML
    private Button Check;
    @FXML
    private Button Raise;
    @FXML
    private Button Fold;
    @FXML
    private Button Bet;
    @FXML
    private Button Allin;
    @FXML
    private Button Confirm;
    @FXML
    private Label wrongRaise;
    @FXML
    private TextField amountRaise;
    @FXML
    private ImageView blindme;
    @FXML
    private ImageView blindop;
    @FXML
    private ImageView bimg;

    private static Parent loadFXML(String fxml) throws IOException {  //pentru alertbox
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    @FXML
    public void initialize(){
        img = new Image(getClass().getResourceAsStream("/img/MatchBCKG.jpg"));
        bimg.setImage(img);

        img = new Image(getClass().getResourceAsStream("/img/CardBCKG.png"));
        my1.setImage(img);
        my2.setImage(img);
        table1.setImage(img);
        table2.setImage(img);
        table3.setImage(img);
        table4.setImage(img);
        table5.setImage(img);
        op1.setImage(img);
        op2.setImage(img);

        img = new Image(getClass().getResourceAsStream("/img/StartButton.png"));
        toStart.setImage(img);

    }

    public void displayAlertBox(String title) throws IOException { //tot pentru alertbox

        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);

        Scene scene = new Scene(loadFXML("Raise"));
        window.setScene(scene);
        window.showAndWait();
    }

    public Boolean isInt(TextField txt, String message){ //pentru a verifica daca in TextField s-a introdus int sau nu
        try{
            int raised = Integer.parseInt(message);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }

    public void butonVizibil(Button b){
        b.setVisible(true);
    }

    public void butonInvizibil(Button b){
        b.setVisible(false);
    }

    MatchEntryController a = new MatchEntryController(); //pentru a returna valoarea din Choicebox

    private Player player = new Player();
    private Player bot = new Player();
    private Match match = new Match(player, bot);
    private int raiseMoney; //pentru a tine minte rezultatul returnat din alertbox TextField
    private boolean check = false,bet = false, raise = false, fold = true, allin = false;

    private Image img; //pentru setarea imaginilor

   public void playersCards(){  //si playerul si botul primesc carti la player ataseaza si poze
       match.givePlayersCards();
       img = new Image(getClass().getResourceAsStream(player.getCard(0).whichCard()));
       my1.setImage(img);
       img = new Image(getClass().getResourceAsStream(player.getCard(1).whichCard()));
       my2.setImage(img);
   }

    public void threeCards(){ // pune 2 carti pe masa si le ataseaza poza
       check = false;
       match.threeCards();
        img = new Image(getClass().getResourceAsStream(match.tableCard(0).whichCard()));
        table1.setImage(img);
        img = new Image(getClass().getResourceAsStream(match.tableCard(1).whichCard()));
        table2.setImage(img);
        img = new Image(getClass().getResourceAsStream(match.tableCard(2).whichCard()));
        table3.setImage(img);
    }

    public void fourthCard(){ //pune a 4a carte pe masa si-i ataseaza poza
        match.fourthCard();
        img = new Image(getClass().getResourceAsStream(match.tableCard(3).whichCard()));
        table4.setImage(img);
    }

    public void fifthCard(){ // pune a 5a carte pe masa si-i ataseaza poza
        match.fifthCard();
        img = new Image(getClass().getResourceAsStream(match.tableCard(4).whichCard()));
        table5.setImage(img);
    }

    public void chooseWinner() { // dupa ultima runda de betting se apeleaza, se arata si cartile botului si se alege castigatorul
        match.evaluate(player);
        match.evaluate(bot);
        if(match.biggerHand(player, bot) == player){
            player.setEntryMoney(match.getPot() + player.getEntryMoney());
            System.out.println("Win");
        }else if(match.biggerHand(player, bot) == bot){
            bot.setEntryMoney(match.getPot() + bot.getEntryMoney());
            System.out.println("Lose");
        }else{
            player.setEntryMoney(match.getPot()/2 + player.getEntryMoney());
            bot.setEntryMoney(match.getPot()/2 + bot.getEntryMoney());
            System.out.println("Egal");
        }
        img = new Image(getClass().getResourceAsStream(bot.getCard(0).whichCard()));
        op1.setImage(img);
        img = new Image(getClass().getResourceAsStream(bot.getCard(1).whichCard()));
        op2.setImage(img);
        System.out.println("Botul are:" + bot.getPlayerPair());
        System.out.println("Eu am:" + player.getPlayerPair());
   }

   public void resetImages() { //inainte de urmatoarea runda se reseteaza imaginile sa nu se vada cartile din runda asta
       img = new Image(getClass().getResourceAsStream("/img/CardBCKG.png"));
       my1.setImage(img);
       my2.setImage(img);
       table1.setImage(img);
       table2.setImage(img);
       table3.setImage(img);
       table4.setImage(img);
       table5.setImage(img);
       op1.setImage(img);
       op2.setImage(img);
       player.setBlind(false);
   }

   public void resetCards(){
       match.resetTable();
       player.setHand(null, null);
       bot.setHand(null, null);
   }

   public void resetPairs(){
       player.setPlayerPair(null);
       bot.setPlayerPair(null);
   }

    public void setMoneyLabel(String s){
        bani.setText(s);
    } //ne arata cati bani mai avem


    public synchronized void Check(){
        notifyAll();
    }

    public synchronized void Bet(){
        player.betMoney(30);
        bot.betMoney(30);
        match.setPot((match.getPot() + 30*2));
        notifyAll();
    }

    public void toRaise(int amount){
        player.raiseMoney(amount);
        bot.raiseMoney(amount);
        match.setPot((match.getPot() + amount*2));
    }

    public void checkRaise() {
        if (amountRaise.getText().isEmpty()) {
            wrongRaise.setText("Please enter a number!");
        } else if (!isInt(amountRaise, amountRaise.getText())) {
            wrongRaise.setText("Please enter a number!");
        } else {
            raiseMoney = Integer.parseInt(amountRaise.getText());
        }
    }

    public synchronized void Raise(){
       toRaise(500);
       notifyAll();
    }


    public synchronized void Fold(){
        player.fold();
        bot.setEntryMoney(match.getPot());
        match.setPot(0);
        System.out.println("S-a dat fold!");
        notifyAll();
    }

    /*public void Allin(int amount){}*/

    public synchronized void Game() throws InterruptedException {
        int cnt = 0;

        while(cnt < 5) {

            playersCards();

            if (match.tableCard(0) == null) {
                wait();
            }

            threeCards();

            if (match.tableCard(3) == null) {
                wait();
            }

            fourthCard();

            if (match.tableCard(4) == null) {
                wait();
            }

            fifthCard();

            if (player.getPlayerPair() == null) {
                wait();
            }

            chooseWinner();

            wait(10000);
            resetCards();
            resetImages();
            resetPairs();
            cnt++;
        }
    }

    public void start() throws IOException, InterruptedException {

       player.setEntryMoney(500);
       bot.setEntryMoney(500);
       int blindAmount = (5*500)/100;
       toStart.setVisible(false);
       slabel.setVisible(false);
       player.setBlind(true);
       Check.setVisible(true);
       butonVizibil(Check);
       butonVizibil(Raise);
       butonVizibil(Bet);
       butonVizibil(Allin);
       butonVizibil(Fold);


        new Thread() {

            @Override
            public void run() {
                try {
                    Game();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start();
   }
}
