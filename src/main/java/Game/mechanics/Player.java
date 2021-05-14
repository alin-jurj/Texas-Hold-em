package Game.mechanics;

import model.User;

public class Player extends User {
    private Card[] playerHand = new Card[2]; //cele 2 carti pe care le are in fiecare tura
    private boolean blind; //daca e "big blind" sau nu la inceputul rundei
    private int totalMoney; //toti banii pe care-i are in cont
    private int entryMoney; //banii cu care se joaca in timpul matchului
    private String playerPair; //perechea pe care o are la sfarsitul rundei de ex: Flush
    private String Status;  //VIP sau regular
    private int winrate;

    public Player(){}

    public void setHand(Card card1, Card card2){    //seteaza la inceputul rundei cele 2 carti (din Deck)
        playerHand[0] = card1;
        playerHand[1] = card2;
    }

    public void setWinrate(int i){
        winrate = winrate + i;
    }

    public int getWinrate(){
        return winrate;
    }

    public void setStatus(String S){
        Status = S;
    }

    public void setEntryMoney(int amount){ entryMoney = amount; }  //seteaza banii cu care intra in match (amount va fi luat din scena entry)
    public int getEntryMoney() { return entryMoney; } //returneaza banii pe care-i mai are in timpul matchului

    public void setPlayerPair(String pair){ playerPair = pair; } //dupa evaluare ii asocieaza jucatorului perechea pe care o are
    public String getPlayerPair(){ return playerPair; } // returneaza perechea pentru comparare (apoi se decide castigatorul)

    public void setBlind(Boolean bool){ blind = bool; }  //se va modifica conditia dupa fiecare runda
    public Boolean getBlind(){ return blind; } //pentru a se verifica daca e sau nu blind pentru a se putea incepe runda

    public Card getCard(int i){
        return playerHand[i];
    } //returneaza cartea 1 sau 2 din mana

    /*public String toString(){
        return playerHand[0].toString() + "and" + playerHand[1].toString();
    }*/

    public void setTotalMoneyMoney(int money){  // se ca seta la logare sau cand primeste jucatorul daily reward sau castiga match
        this.totalMoney = money;
    }
    public int getTotalMoneyMoney(){  // returneaza valoarea pentru a verifica daca poate intra in match sau nu
        return totalMoney;
    }

    public void betMoney(int amount){ entryMoney = entryMoney - amount; } // in momentul apasarii butonului bet amount va fi calculat si functia va fi apelata, iar suma pe care o are jucatorul va fi modificata

    public void raiseMoney(int amount){ entryMoney = entryMoney - amount; } // in momentul apasarii butonului raise amount va fi introdus intr-un TextField si returnat de acolo si functia va fi apelata, iar suma pe care o are jucatorul va fi modificata

    public void check(){} //asteptam ca botul sa faca ceva

    public void fold(){} //runda se termina si botul primeste banii
}
