package Game.mechanics;

public class Match {
    private Player[] player = new Player[2];
    private Card[] table = new Card[5];
    private Deck deck = new Deck();
    private int betmoney;

    public Match(Player player1, Player player2){
        player[0] = player1;
        player[1] = player2;
    }

    public void givePlayersCards(){
        player[0] = new Player(deck.giveCard(), deck.giveCard());
        player[1] = new Player(deck.giveCard(), deck.giveCard());
    }

    public void askForEntry(int money, Player player){
        player.betMoney(money);
    }

    public void threeCards(){
        table[0] = deck.giveCard();
        table[1] = deck.giveCard();
        table[2] = deck.giveCard();
    }

    public void betting(int money, Player player){
        player.betMoney(money);
    }

    public void fourthCard(){
        table[3] = deck.giveCard();
    }

    public void fifthCard(){
        table[4] = deck.giveCard();
    }

    public void evaluate(Player player){
        Pairs pairs = new Pairs();
        int j = 0;
        pairs.addCard(player.getCard(0), 0);
        pairs.addCard(player.getCard(1), 1);
        for(int i = 2; i<7; i++){
            pairs.addCard(table[j++], i);
        }

        player.setPlayerPair(pairs.evaluare());
    }
}
