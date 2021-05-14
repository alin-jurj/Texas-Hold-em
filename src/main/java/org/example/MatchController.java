package org.example;

import Game.mechanics.*;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.User;
import services.UserService;

import java.io.IOException;
import java.util.Random;

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
    public Label mybani;
    @FXML
    public Label botaction;
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
    private ImageView blindme;
    @FXML
    private ImageView blindop;
    @FXML
    private ImageView bimg;
    @FXML
    private Label winner;
    @FXML
    private Label youhave;
    @FXML
    private Label ophas;
    @FXML
    private Label endgame;
    @FXML
    private Label cronometru;
    @FXML
    private AnchorPane scene;
    @FXML
    private Button endButton;
    @FXML
    private ImageView endImg;

    @FXML
    public void initialize() {
        scene.prefWidth(1024);
        scene.prefHeight(768);
        App.resizeStage(1034, 800);
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

        ophas.setVisible(false);
        youhave.setVisible(false);
        winner.setVisible(false);

        img = new Image(getClass().getResourceAsStream("/img/blind.png"));
        blindme.setImage(img);
        blindop.setImage(img);
        blindme.setVisible(false);
        blindme.setVisible(false);
        endgame.setVisible(false);
        cronometru.setVisible(false);
    }

    public void butonVizibil(Button b) {
        b.setVisible(true);
    }

    public void butoaneV() {
        butonVizibil(Check);
        butonVizibil(Raise);
        butonVizibil(Allin);
        butonVizibil(Fold);
    }

    public void butonInvizibil(Button b) {
        b.setVisible(false);
    }

    public void butoaneI() {
        butonInvizibil(Check);
        butonInvizibil(Raise);
        butonInvizibil(Bet);
        butonInvizibil(Allin);
        butonInvizibil(Fold);
    }

    public void labelVizibil(Label l) {
        l.setVisible(true);
    }

    public void labelInvizibil(Label l) {
        l.setVisible(false);
    }

    public void setPot(int i) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                bani.setText("Pot: " + String.valueOf(i));
            }
        });
    }

    public void setBani(int i) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                mybani.setText("My money: " + String.valueOf(i));
            }
        });
    }

    public void setBotaction(String s) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                botaction.setText(s);
            }
        });
    }

    AfterLogin entry = new AfterLogin();
    private Player player = new Player();
    private Player bot = new Player();
    private Match match = new Match(player, bot);
    private int raiseMoney = (15 * entry.getEntryPlay())/100;
    private boolean check = false, bet = false, raise = false, fold = true, allin = false;
    private int blindAmount = (10 * entry.getEntryPlay()) / 100;
    private int ok2 = 0;

    private Image img; //pentru setarea imaginilor

    public int getRaiseMoney() {
        return raiseMoney;
    }

    public void setBlindAmount(int amount) {
        blindAmount = amount;
    }

    public int getBlindAmount() {
        return blindAmount;
    }

    public void playersCards() {  //si playerul si botul primesc carti la player ataseaza si poze
        match.givePlayersCards();
        img = new Image(getClass().getResourceAsStream(player.getCard(0).whichCard()));
        my1.setImage(img);
        img = new Image(getClass().getResourceAsStream(player.getCard(1).whichCard()));
        my2.setImage(img);
    }

    public void threeCards() { // pune 2 carti pe masa si le ataseaza poza
        check = false;
        match.threeCards();
        img = new Image(getClass().getResourceAsStream(match.tableCard(0).whichCard()));
        table1.setImage(img);
        img = new Image(getClass().getResourceAsStream(match.tableCard(1).whichCard()));
        table2.setImage(img);
        img = new Image(getClass().getResourceAsStream(match.tableCard(2).whichCard()));
        table3.setImage(img);
    }

    public void fourthCard() { //pune a 4a carte pe masa si-i ataseaza poza
        match.fourthCard();
        img = new Image(getClass().getResourceAsStream(match.tableCard(3).whichCard()));
        table4.setImage(img);
    }

    public void fifthCard() { // pune a 5a carte pe masa si-i ataseaza poza
        match.fifthCard();
        img = new Image(getClass().getResourceAsStream(match.tableCard(4).whichCard()));
        table5.setImage(img);
    }

    public synchronized void chooseWinner() { // dupa ultima runda de betting se apeleaza, se arata si cartile botului si se alege castigatorul
        match.evaluate(player);
        match.evaluate(bot);
        if (match.biggerHand(player, bot) == player) {
            player.setEntryMoney(match.getPot() + player.getEntryMoney());
            System.out.println("Win");
            winner.setText("You won this round!");
        } else if (match.biggerHand(player, bot) == bot) {
            bot.setEntryMoney(match.getPot() + bot.getEntryMoney());
            System.out.println("Lose");
            winner.setText("You lost this round!");
        } else {
            player.setEntryMoney(match.getPot() / 2 + player.getEntryMoney());
            bot.setEntryMoney(match.getPot() / 2 + bot.getEntryMoney());
            System.out.println("Egal");
            winner.setText("It is a draw!");
        }
        img = new Image(getClass().getResourceAsStream(bot.getCard(0).whichCard()));
        op1.setImage(img);
        img = new Image(getClass().getResourceAsStream(bot.getCard(1).whichCard()));
        op2.setImage(img);
        System.out.println("Botul are:" + bot.getPlayerPair());
        System.out.println("Eu am:" + player.getPlayerPair());
        youhave.setText("You have: " + player.getPlayerPair());
        ophas.setText("The opponent has: " + bot.getPlayerPair());
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
    }

    public void resetCards() {
        match.resetTable();
        player.setHand(null, null);
        bot.setHand(null, null);
    }

    public void resetPairs() {
        player.setPlayerPair(null);
        bot.setPlayerPair(null);
    }

    public synchronized void Check(){
        check = true;
        notifyAll();
    }

    public synchronized void Bet() {
        bet = true;
        notifyAll();
    }

    public synchronized void Raise() {
        raise = true;
        notifyAll();
    }


    public synchronized void Fold() {
        player.fold();
        bot.setEntryMoney(bot.getEntryMoney() + match.getPot());
        match.setPot(0);
        System.out.println("S-a dat fold!");
        fold = true;
        notifyAll();
    }

    public synchronized void Allin() {
        allin = true;
        notifyAll();
    }

    public void resetButtons() {
        check = false;
        bet = false;
        raise = false;
        fold = false;
    }

    public void resetBlind(){
        if (player.getBlind()) {        //se reseteaza blind
            player.setBlind(false);
        } else {
            player.setBlind(true);
        }

        if (bot.getBlind()) {
            bot.setBlind(false);
        } else {
            bot.setBlind(true);
        }
    }

    int todo;                   //pentru ca botul sa poata actiona random
    Random rand = new Random();

    public void botRaise(int i) throws InterruptedException {

        if (bot.getEntryMoney() > i) {
            setBotaction("Raise with " +  i + "!");
            match.setPot(match.getPot() + i);
            setPot(match.getPot());
            bot.betMoney(i);
        } else {
            setBotaction("All in!");
            match.setPot(match.getPot() + bot.getEntryMoney());
            setPot(match.getPot());
            bot.betMoney(bot.getEntryMoney());
            allin = true;
        }
        butonInvizibil(Check);
        butonInvizibil(Raise);
        butonInvizibil(Allin);
        butonVizibil(Bet);
        butonVizibil(Fold);
        wait(1000);
        setBotaction("Your turn!");
        wait(30000);

        if (bet == true) {
            if (player.getEntryMoney() > i) { //am mai multi bani ca necesar pt a intra e call!
                match.setPot(match.getPot() + i);
                setPot(match.getPot());
                player.betMoney(i);
                setBani(player.getEntryMoney());
                ok2 = 1;
            } else {   //altfel daca apas bet sunt nevoit sa dau all in
                match.setPot(match.getPot() + player.getEntryMoney());
                setPot(match.getPot());
                player.betMoney(player.getEntryMoney());
                setBani(player.getEntryMoney());
                allin = true;
            }
        } else {
            Fold();
        }
    }

    public void EndGame() throws IOException {
        App m = new App();
        m.changeScene("afterLogin.fxml");
    }

    public synchronized void Game() throws InterruptedException {
        int ok, ok3 = 0;


        while (player.getEntryMoney() != 0 && bot.getEntryMoney() != 0) {
            resetBlind();

            Platform.runLater(new Runnable() {   //legat de ui
                @Override
                public void run() {
                    labelInvizibil(youhave);
                    labelInvizibil(ophas);
                    labelInvizibil(winner);
                    if (player.getBlind() == true) {
                        blindop.setVisible(false);
                        blindme.setVisible(true);
                        butoaneI();
                    } else {
                        blindop.setVisible(true);
                        blindme.setVisible(false);
                    }
                }
            });

            if (fold == true) wait(500); //reseteaza runda
            allin = false;
            match.setPot(0);
            setPot(0);
            setBani(player.getEntryMoney());
            resetButtons();
            resetCards();
            resetImages();
            resetPairs();
            match.shuffle();

            wait(500);
            playersCards();    //se dau carti la jucatori

            if(player.getEntryMoney() <= getRaiseMoney()){
                butonInvizibil(Raise);
            }

            if (player.getBlind() == true) {
                if (player.getEntryMoney() > getBlindAmount()) { // am bani sa dau cat e blind-ul
                    wait(500);
                    match.setPot(match.getPot() + getBlindAmount());
                    setPot(match.getPot());
                    player.betMoney(getBlindAmount());
                    setBani(player.getEntryMoney());
                    if (bot.getEntryMoney() > getBlindAmount()) { //si botul are destui bani
                        wait(500);
                        setBotaction("Call!");
                        match.setPot(match.getPot() + getBlindAmount());
                        setPot(match.getPot());
                        bot.betMoney(getBlindAmount());
                    } else {                                         // botul da all in pentru ca n-are destui bani
                        wait(500);
                        setBotaction("All in!");
                        match.setPot(match.getPot() + bot.getEntryMoney());
                        setPot(match.getPot());
                        bot.betMoney(bot.getEntryMoney());
                        allin = true;
                    }
                } else {                                              //n-am destui bani deci dau all in
                    wait(500);
                    match.setPot(match.getPot() + player.getEntryMoney());
                    setPot(match.getPot());
                    player.betMoney(player.getEntryMoney());
                    setBani(player.getEntryMoney());
                    allin = true;
                    if (bot.getEntryMoney() > player.getEntryMoney()) { //botul are mai multi bani ca mine
                        wait(500);
                        setBotaction("Call!");
                        match.setPot(match.getPot() + player.getEntryMoney());
                        setPot(match.getPot());
                        bot.betMoney(player.getEntryMoney());
                    } else {      //botul da si el all in
                        wait(500);
                        setBotaction("All in!");
                        match.setPot(match.getPot() + bot.getEntryMoney());
                        setPot(match.getPot());
                        bot.betMoney(bot.getEntryMoney());
                    }
                }
            } else {     //cazul in care botul e blind
                if (bot.getEntryMoney() > getBlindAmount()) { //are destui bani sa plateasca blind
                    wait(500);
                    setBotaction("Your turn!");
                    match.setPot(match.getPot() + getBlindAmount());
                    setPot(match.getPot());
                    bot.betMoney(getBlindAmount());
                    ok = 1;  //lucram cu blind
                } else {  //nu are sa plateasca blind deci da all in
                    wait(500);
                    setBotaction("All in!");
                    match.setPot(match.getPot() + bot.getEntryMoney());
                    setPot(match.getPot());
                    bot.betMoney(bot.getEntryMoney());
                    ok = 0;  //lucram cu banii botului
                    allin = true;
                    butonInvizibil(Raise);
                }

                butonVizibil(Bet);
                butoaneV();
                if(player.getEntryMoney() > bot.getEntryMoney() && ok == 0){
                    butonInvizibil(Allin);
                }
                if(player.getEntryMoney() <= getRaiseMoney() || allin == true){
                    butonInvizibil(Raise);
                }
                butonInvizibil(Check);
                wait(500);
                setBotaction("Your turn!");
                wait(30000);


                if (bet == true && ok == 1) { //botul avea cat sa plateasca blind
                    if (player.getEntryMoney() > getBlindAmount()) { //daca am si eu
                        match.setPot(match.getPot() + getBlindAmount());
                        setPot(match.getPot());
                        player.betMoney(getBlindAmount());
                        setBani(player.getEntryMoney());
                    } else {                                          //daca nu am dau all in
                        match.setPot(match.getPot() + player.getEntryMoney());
                        setPot(match.getPot());
                        player.betMoney(player.getEntryMoney());
                        setBani(player.getEntryMoney());
                        allin = true;
                    }
                } else if (bet == true && ok == 0) {                      //botul nu avea si a dat all in
                    if (player.getEntryMoney() > bot.getEntryMoney()) {     //am mai multi ca botul intru
                        match.setPot(match.getPot() + getBlindAmount());
                        setPot(match.getPot());
                        player.betMoney(getBlindAmount());
                        setBani(player.getEntryMoney());
                    } else {                                                  //dau si eu all in
                        match.setPot(match.getPot() + player.getEntryMoney());
                        setPot(match.getPot());
                        player.betMoney(player.getEntryMoney());
                        setBani(player.getEntryMoney());
                    }
                } else if (allin == true && ok == 1) { //eu dau all in si botul nu daduse
                    match.setPot(match.getPot() + player.getEntryMoney());
                    setPot(match.getPot());
                    setBani(0);
                    if ((bot.getEntryMoney() + getBlindAmount()) > player.getEntryMoney()) {  //botul da bet
                        bot.setEntryMoney(bot.getEntryMoney() + getBlindAmount());
                        player.betMoney(player.getEntryMoney());
                        match.setPot(match.getPot() - getBlindAmount());
                        wait(500);
                        setBotaction("Call!");
                        match.setPot(match.getPot() * 2);
                        setPot(match.getPot());
                        bot.betMoney(match.getPot() / 2);
                    } else { //botul da si el all in
                        bot.setEntryMoney(bot.getEntryMoney() + getBlindAmount());
                        match.setPot(match.getPot() - getBlindAmount());
                        wait(500);
                        setBotaction("All in!");
                        match.setPot(match.getPot() + bot.getEntryMoney());
                        setPot(match.getPot());
                        bot.betMoney(bot.getEntryMoney());
                        player.betMoney(player.getEntryMoney());
                    }
                } else if (raise == true && ok == 1) { //eu dau raise si botul nu daduse all in
                    player.betMoney(getRaiseMoney());
                    setBani(player.getEntryMoney());
                    match.setPot(match.getPot() + getRaiseMoney());
                    if (bot.getEntryMoney() + getBlindAmount() > getRaiseMoney()) { //are bani pentru call
                        bot.setEntryMoney(bot.getEntryMoney() + getBlindAmount());
                        match.setPot(match.getPot() - getBlindAmount());
                        wait(500);
                        setBotaction("Call!");
                        match.setPot(match.getPot() + getRaiseMoney());
                        setPot(match.getPot());
                        bot.betMoney(getRaiseMoney());
                    } else { //da all in
                        bot.setEntryMoney(bot.getEntryMoney() + getBlindAmount());
                        match.setPot(match.getPot() - getBlindAmount());
                        wait(500);
                        setBotaction("All in!");
                        match.setPot(match.getPot() + bot.getEntryMoney());
                        setPot(match.getPot());
                        bot.betMoney(bot.getEntryMoney());
                        allin = true;
                    }
                } else if (check == true && ok == 1) { //exact ca la bet
                    if (player.getEntryMoney() > getBlindAmount()) { //daca am si eu
                        match.setPot(match.getPot() + getBlindAmount());
                        setPot(match.getPot());
                        player.betMoney(getBlindAmount());
                        setBani(player.getEntryMoney());
                    } else {                                          //daca nu am dau all in
                        match.setPot(match.getPot() + player.getEntryMoney());
                        setPot(match.getPot());
                        player.betMoney(player.getEntryMoney());
                        setBani(player.getEntryMoney());
                        allin = true;
                    }
                } else if (check == true && ok == 0) {
                    if (player.getEntryMoney() > bot.getEntryMoney()) {     //am mai multi ca botul intru
                        match.setPot(match.getPot() + bot.getEntryMoney());
                        setPot(match.getPot());
                        player.betMoney(bot.getEntryMoney());
                        setBani(player.getEntryMoney());
                    } else {                                                  //dau si eu all in
                        match.setPot(match.getPot() + player.getEntryMoney());
                        setPot(match.getPot());
                        player.betMoney(player.getEntryMoney());
                        setBani(player.getEntryMoney());
                    }
                } else Fold();
            }

            butonInvizibil(Bet);
            butoaneV();

            if (fold == true)
                continue;

            if (player.getBlind() == true)
                butoaneI();

            wait(500);
            setBotaction("Your turn!");

            butoaneV();

            resetButtons();
            ok2 = 0;

            if(player.getEntryMoney() <= getRaiseMoney()){
                butonInvizibil(Raise);
            }

            threeCards();
            wait(500);

            if (bot.getBlind() == true && allin == false) {
                todo = rand.nextInt(5);
                if (todo == 0) {
                    bot.check();
                    setBotaction("Check!");
                    wait(1000);
                    setBotaction("Your turn!");
                } else if (todo == 1) {
                    bot.fold();
                    setBotaction("Fold!");
                    player.setEntryMoney(player.getEntryMoney() + match.getPot());
                    wait(500);
                    continue;
                } else if (todo == 2) {
                    botRaise((10* entry.getEntryPlay())/100);
                } else if (todo == 3) {
                    botRaise((15* entry.getEntryPlay())/100);
                } else if (todo == 4) {
                    botRaise((20* entry.getEntryPlay())/100);
                }
            }

            if (allin == false && ok2 == 0) { //ok2 verifica daca am intrat dupa ce botul a dat raise
                if (match.tableCard(3) == null) {
                    wait(30000);
                }
                if (check != true && raise != true && bet != true && allin != true) {
                    Fold();
                }
                if(check == true && !bot.getBlind()){
                    butoaneI();
                    todo = rand.nextInt(4);
                    if (todo == 0) {
                        bot.check();
                        setBotaction("Check!");
                        wait(1000);
                        setBotaction("Your turn!");
                    } else if (todo == 1) {
                        botRaise((10* entry.getEntryPlay())/100);
                    } else if (todo == 2) {
                        botRaise((15* entry.getEntryPlay())/100);
                    } else if (todo == 3) {
                        botRaise((20* entry.getEntryPlay())/100);
                    }
                }else if(raise == true) {
                    player.betMoney(getRaiseMoney());
                    setBani(player.getEntryMoney());
                    match.setPot(match.getPot() + getRaiseMoney());
                    setPot(match.getPot());
                    butoaneI();
                    todo = rand.nextInt(2);
                    if (todo == 0) {
                        if(bot.getEntryMoney() > getRaiseMoney()) {
                            setBotaction("Call!");
                            bot.betMoney(getRaiseMoney());
                            match.setPot(match.getPot() + getRaiseMoney());
                            setPot(match.getPot());
                            wait(1000);
                            setBotaction("Your turn!");
                        }else{
                            setBotaction("All in!");
                            allin = true;
                            match.setPot(match.getPot() + bot.getEntryMoney());
                            setPot(match.getPot());
                            bot.betMoney(bot.getEntryMoney());
                            wait(1000);
                            setBotaction("Your turn!");
                        }
                    } else if (todo == 1) {
                        bot.fold();
                        setBotaction("Fold!");
                        player.setEntryMoney(player.getEntryMoney() + match.getPot());
                        wait(500);
                        continue;
                    }
                }else if(allin == true){
                    butoaneI();
                    todo = rand.nextInt(2);
                    if (todo == 0) {
                        if(bot.getEntryMoney() > player.getEntryMoney()) {
                            setBotaction("Call!");
                            bot.betMoney(player.getEntryMoney());
                            match.setPot(match.getPot() + player.getEntryMoney()*2);
                            setPot(match.getPot());
                            player.betMoney(player.getEntryMoney());
                            setBani(player.getEntryMoney());
                        }else{
                            setBotaction("All in!");
                            match.setPot(match.getPot() + player.getEntryMoney() + bot.getEntryMoney());
                            bot.betMoney(bot.getEntryMoney());
                            setPot(match.getPot());
                            player.betMoney(player.getEntryMoney());
                            setBani(player.getEntryMoney());
                        }
                    } else if (todo == 1) {
                        bot.fold();
                        setBotaction("Fold!");
                        player.setEntryMoney(player.getEntryMoney() + match.getPot());
                        wait(500);
                        continue;
                    }
                }
            }

                if (fold == true)
                    continue;

                butonInvizibil(Bet);
                butoaneV();
                resetButtons();
                ok2 = 0;

                if(player.getEntryMoney() <= getRaiseMoney()){
                    butonInvizibil(Raise);
                }

                if(allin != true)
                wait(500);
                fourthCard();
                if(allin != true)
                wait(500);

                if (bot.getBlind() == true && allin == false) {
                    todo = rand.nextInt(5);
                    if (todo == 0) {
                        bot.check();
                        setBotaction("Check!");
                        wait(1000);
                        setBotaction("Your turn!");
                    } else if (todo == 1) {
                        bot.fold();
                        setBotaction("Fold!");
                        wait(500);
                        player.setEntryMoney(player.getEntryMoney() + match.getPot());
                        continue;
                    } else if (todo == 2) {
                        botRaise((10* entry.getEntryPlay())/100);
                    } else if (todo == 3) {
                        botRaise((15* entry.getEntryPlay())/100);
                    } else if (todo == 4) {
                        botRaise((20* entry.getEntryPlay())/100);
                    }
                }


                if (player.getBlind()) {
                    wait(500);
                    setBotaction("Your turn!");
                }

                if (allin == false && ok2 == 0) {
                    if (match.tableCard(4) == null) {
                        wait(30000);
                    }
                    if (check != true && raise != true && bet != true && allin != true) {
                        Fold();
                    }
                    if(check == true && !bot.getBlind()){
                        butoaneI();
                        todo = rand.nextInt(4);
                        if (todo == 0) {
                            bot.check();
                            setBotaction("Check!");
                            wait(1000);
                            setBotaction("Your turn!");
                        } else if (todo == 1) {
                            botRaise((10* entry.getEntryPlay())/100);
                        } else if (todo == 2) {
                            botRaise((15* entry.getEntryPlay())/100);
                        } else if (todo == 3) {
                            botRaise((20* entry.getEntryPlay())/100);
                        }
                    }else if(raise == true) {
                        player.betMoney(getRaiseMoney());
                        setBani(player.getEntryMoney());
                        match.setPot(match.getPot() + getRaiseMoney());
                        setPot(match.getPot());
                        butoaneI();
                        todo = rand.nextInt(2);
                        if (todo == 0) {
                            if(bot.getEntryMoney() > getRaiseMoney()) {
                                setBotaction("Call!");
                                bot.betMoney(getRaiseMoney());
                                match.setPot(match.getPot() + getRaiseMoney());
                                setPot(match.getPot());
                                wait(1000);
                                setBotaction("Your turn!");
                            }else{
                                setBotaction("All in!");
                                allin = true;
                                match.setPot(match.getPot() + bot.getEntryMoney());
                                setPot(match.getPot());
                                bot.betMoney(bot.getEntryMoney());
                                wait(1000);
                                setBotaction("Your turn!");
                            }
                        } else if (todo == 1) {
                            bot.fold();
                            setBotaction("Fold!");
                            player.setEntryMoney(player.getEntryMoney() + match.getPot());
                            wait(500);
                            continue;
                        }
                    }else if(allin == true){
                        butoaneI();
                        todo = rand.nextInt(2);
                        if (todo == 0) {
                            if(bot.getEntryMoney() > player.getEntryMoney()) {
                                setBotaction("Call!");
                                bot.betMoney(player.getEntryMoney());
                                match.setPot(match.getPot() + player.getEntryMoney()*2);
                                setPot(match.getPot());
                                player.betMoney(player.getEntryMoney());
                                setBani(player.getEntryMoney());
                            }else{
                                setBotaction("All in!");
                                allin = true;
                                match.setPot(match.getPot() + player.getEntryMoney() + bot.getEntryMoney());
                                bot.betMoney(bot.getEntryMoney());
                                setPot(match.getPot());
                                player.betMoney(player.getEntryMoney());
                                setBani(player.getEntryMoney());
                            }
                        } else if (todo == 1) {
                            bot.fold();
                            setBotaction("Fold!");
                            player.setEntryMoney(player.getEntryMoney() + match.getPot());
                            wait(500);
                            continue;
                        }
                    }
                }


            if (fold == true)
                continue;


            butonInvizibil(Bet);
            butoaneV();
            resetButtons();
            ok2 = 0;

            if(player.getEntryMoney() <= getRaiseMoney()){
                butonInvizibil(Raise);
            }

            if(allin != true)
            wait(500);
            fifthCard();
            if(allin != true)
            wait(500);

            if (bot.getBlind() == true && allin == false) {
                todo = rand.nextInt(5);
                if (todo == 0) {
                    bot.check();
                    setBotaction("Check!");
                    wait(1000);
                    setBotaction("Your turn!");
                } else if (todo == 1) {
                    bot.fold();
                    setBotaction("Fold!");
                    wait(500);
                    player.setEntryMoney(player.getEntryMoney() + match.getPot());
                    continue;
                } else if (todo == 2) {
                    botRaise((10* entry.getEntryPlay())/100);
                } else if (todo == 3) {
                    botRaise((15* entry.getEntryPlay())/100);
                } else if (todo == 4) {
                    botRaise((20* entry.getEntryPlay())/100);
                }
            }

            if (player.getBlind()) {
                wait(500);
                setBotaction("Your turn!");
            }

            if (allin == false && ok2 == 0) {
                if (player.getPlayerPair() == null) {
                    wait(30000);
                }
                if (check != true && raise != true && bet != true && allin != true) {
                    Fold();
                }
                if(check == true && !bot.getBlind()){
                    butoaneI();
                    todo = rand.nextInt(4);
                    if (todo == 0) {
                        bot.check();
                        setBotaction("Check!");
                        wait(1000);
                        setBotaction("Your turn!");
                    } else if (todo == 1) {
                        botRaise((10* entry.getEntryPlay())/100);
                    } else if (todo == 2) {
                        botRaise((15* entry.getEntryPlay())/100);
                    } else if (todo == 3) {
                        botRaise((20* entry.getEntryPlay())/100);
                    }
                }else if(raise == true) {
                    player.betMoney(getRaiseMoney());
                    setBani(player.getEntryMoney());
                    match.setPot(match.getPot() + getRaiseMoney());
                    setPot(match.getPot());
                    butoaneI();
                    butoaneI();
                    todo = rand.nextInt(2);
                    if (todo == 0) {
                        if(bot.getEntryMoney() > getRaiseMoney()) {
                            setBotaction("Call!");
                            bot.betMoney(getRaiseMoney());
                            match.setPot(match.getPot() + getRaiseMoney());
                            setPot(match.getPot());
                            wait(1000);
                            setBotaction("Your turn!");
                        }else{
                            setBotaction("All in!");
                            allin = true;
                            match.setPot(match.getPot() + bot.getEntryMoney());
                            setPot(match.getPot());
                            bot.betMoney(bot.getEntryMoney());
                            wait(1000);
                            setBotaction("Your turn!");
                        }
                    } else if (todo == 1) {
                        bot.fold();
                        setBotaction("Fold!");
                        player.setEntryMoney(player.getEntryMoney() + match.getPot());
                        wait(500);
                        continue;
                    }
                }else if(allin == true){
                    todo = rand.nextInt(2);
                    if (todo == 0) {
                        if(bot.getEntryMoney() > player.getEntryMoney()) {
                            setBotaction("Call!");
                            bot.betMoney(player.getEntryMoney());
                            match.setPot(match.getPot() + player.getEntryMoney()*2);
                            setPot(match.getPot());
                            player.betMoney(player.getEntryMoney());
                            setBani(player.getEntryMoney());
                        }else{
                            setBotaction("All in!");
                            allin = true;
                            match.setPot(match.getPot() + player.getEntryMoney() + bot.getEntryMoney());
                            bot.betMoney(bot.getEntryMoney());
                            setPot(match.getPot());
                            player.betMoney(player.getEntryMoney());
                            setBani(player.getEntryMoney());
                        }
                    } else if (todo == 1) {
                        bot.fold();
                        setBotaction("Fold!");
                        player.setEntryMoney(player.getEntryMoney() + match.getPot());
                        wait(500);
                        continue;
                    }
                }
            }

            if (fold == true)
                continue;

            butonInvizibil(Bet);
            butoaneV();
            resetButtons();
            ok2 = 0;

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    chooseWinner();
                    labelVizibil(youhave);
                    labelVizibil(ophas);
                    labelVizibil(winner);
                }
            });


            butoaneI();

            wait(5000);

            resetCards();
            resetImages();
            resetPairs();
        }



        System.out.println("Gata meciul!");
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                labelInvizibil(winner);
                labelInvizibil(ophas);
                labelInvizibil(youhave);
                labelInvizibil(bani);
                labelInvizibil(mybani);
                labelInvizibil(botaction);
                op1.setVisible(false);
                op2.setVisible(false);
                my1.setVisible(false);
                my2.setVisible(false);
                table1.setVisible(false);
                table2.setVisible(false);
                table3.setVisible(false);
                table4.setVisible(false);
                table5.setVisible(false);
                blindme.setVisible(false);
                blindop.setVisible(false);

                img = new Image(getClass().getResourceAsStream("/img/endgame.jpg"));
                bimg.setImage(img);

                if(player.getEntryMoney() != 0) {
                    endgame.setText("You won the game!");
                    endgame.setVisible(true);
                    img = new Image(getClass().getResourceAsStream("/img/StartButton.png"));
                    endImg.setImage(img);
                    endButton.setVisible(true);
                }else{
                    endgame.setText("You lost the game!");
                    endgame.setVisible(true);
                    img = new Image(getClass().getResourceAsStream("/img/StartButton.png"));
                    endImg.setImage(img);
                    endButton.setVisible(true);
                }
            }
        });

        player.setTotalMoney(player.getTotalMoney() + player.getEntryMoney());
        if(player.getEntryMoney() == 0){
            if(UserService.getWinRate(LogIn.getUsername()) > 0) {
                UserService.updateWinRate(LogIn.getUsername(),-1);
            }
        }else{
            UserService.updateWinRate(LogIn.getUsername(),1);
        }

        if(UserService.getWinRate(LogIn.getUsername()) >= 10){
            UserService.updateUserStatus(LogIn.getUsername(), "VIP");
        }

        if(player.getEntryMoney() != 0){
            //UserService.giveUserMoney(LogIn.getUsername(), player.getTotalMoney());
        }

    }
        public void start() throws IOException, InterruptedException {

            player.setEntryMoney(entry.getEntryPlay());
            bot.setEntryMoney(entry.getEntryPlay());
            setBlindAmount(getBlindAmount());
            toStart.setVisible(false);
            slabel.setVisible(false);
            player.setBlind(false);
            bot.setBlind(true);
            match.setPot(0);
            bani.setVisible(true);
            bani.setText("Pot: " + String.valueOf(match.getPot()));
            mybani.setVisible(true);

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

