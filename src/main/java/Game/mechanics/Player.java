package Game.mechanics;

public class Player {
    private Card[] playerHand = new Card[2];
    private int money;
    private String playerPair;
    private String Status;

    public Player(Card card1, Card card2){
        playerHand[0] = card1;
        playerHand[1] = card2;
    }

    public void setPlayerPair(String pair){
        playerPair = pair;
    }

    public Card getCard(int i){
        return playerHand[i];
    }

    public String toString(){
        return playerHand[0].toString() + "and" + playerHand[1].toString();
    }

    public void setMoney(int money){
        this.money = money;
    }

    public int getMoney(){
        return money;
    }

    public int betMoney(int amount){
        money = money - amount;
        return amount;
    }

    public int raiseMoney(int amount){
        money = money - amount;
        return amount;
    }

    public void check(){}

    public void fold(){}
}
