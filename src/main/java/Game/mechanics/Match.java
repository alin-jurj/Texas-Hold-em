package Game.mechanics;

public class Match {
    private Player[] player = new Player[2];
    private Card[] table = new Card[5];
    private Deck deck = new Deck();
    private int pot; //cati bani sunt pe masa (cat s-a pariat pana in momentul respectiv)

    public Match(Player player1, Player player2){
        player[0] = player1;
        player[1] = player2;
    }

    public void givePlayersCards(){
        player[0].setHand(deck.giveCard(), deck.giveCard());
        player[1].setHand(deck.giveCard(), deck.giveCard());
    }

    public void threeCards(){
        table[0] = deck.giveCard();
        table[1] = deck.giveCard();
        table[2] = deck.giveCard();
    }

    public Card tableCard(int i){
        return table[i];
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
        pairs.addRanks();
        pairs.sortRanks();
        pairs.addSuits();
        pairs.sortSuits();

        player.setPlayerPair(pairs.evaluare());
    }

    public Player biggerHand(Player player1, Player player2){
        int i, j;
        String[] posibilitati = new String[]{"High card", "Pair", "Two pair", "Three of a kind", "Straight", "Flush", "Full house", "Four of a kind", "Straight flush", "Royal flush"};

        for(i = 0; i<10; i++){
            if(player1.getPlayerPair().equals(posibilitati[i]))
                break;
        }

        for(j = 0; j<10; j++){
            if(player2.getPlayerPair().equals(posibilitati[j]))
                break;
        }

        if(i<j){
            return player2;
        }
        if(i>j){
            return player1;
        }
        return null;
    }

    public void setPot(int amount){
        pot = amount;
    }
    public int getPot(){ return pot; }
}
