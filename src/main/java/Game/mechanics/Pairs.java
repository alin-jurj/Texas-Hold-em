package Game.mechanics;

import Game.mechanics.Card;

import java.util.Arrays;
public class Pairs {

    private Card[] hand = new Card[7];
    private int[] ranks = new int[7];
    private String[] suits = new String[7];

    public Pairs(){}

    public void addCard(Card card, int i){
        hand[i] = card;
    }

    public Card getCard(int i){
        return hand[i];
    }

    public void addRanks(){
        int i;
        for(i = 0; i<7; i++){
            ranks[i] = hand[i].getRank();
        }
    }

    public void addSuits(){
        int i;
        for(i = 0; i<7; i++){
            suits[i] = hand[i].getSuit();
        }
    }

    public void sortRanks(){
        Arrays.sort(ranks);
    }

    public void sortSuits(){
        Arrays.sort(suits);
    }

    public String royalFlush(){
        String aux;
        int i, cnt = 0;

        if((suits[0].equals(suits[1]) && suits[1].equals(suits[2]) && suits[2].equals(suits[3]) && suits[3].equals(suits[4]))
                || (suits[1].equals(suits[2]) && suits[2].equals(suits[3]) && suits[3].equals(suits[4]) && suits[4].equals(suits[5]))
                || (suits[2].equals(suits[3]) && suits[3].equals(suits[4]) && suits[4].equals(suits[5]) && suits[5].equals(suits[6]))){

            aux = suits[3];

            if((ranks[0] == 1 || ranks[1] == 1 || ranks[2] == 1 || ranks[3] == 1)
                    && (ranks[1] == 10 || ranks[2] == 10 || ranks[3] == 10)
                    && (ranks[2] == 11 || ranks[3] == 11 || ranks[4] == 11)
                    && (ranks[3] == 12 || ranks[4] == 12 || ranks[5] == 12)
                    && (ranks[4] == 13 || ranks[5] == 13 || ranks[6] == 13)){

                for(i = 0; i<7; i++){
                    if((hand[i].getRank() == 1 || hand[i].getRank() == 10 || hand[i].getRank() == 11 || hand[i].getRank() == 12 || hand[i].getRank() == 13)
                            && hand[i].getSuit().equals(aux))
                        cnt++;
                }

                if(cnt == 5)
                    return "Royal flush";

            }
        }

        return null;
    }

    public String straightFlush(){
        int i, j, cnt1 = 0, cnt2 = 0;

        if((suits[0].equals(suits[1]) && suits[1].equals(suits[2]) && suits[2].equals(suits[3]) && suits[3].equals(suits[4]))
                || (suits[1].equals(suits[2]) && suits[2].equals(suits[3]) && suits[3].equals(suits[4]) && suits[4].equals(suits[5]))
                || (suits[2].equals(suits[3]) && suits[3].equals(suits[4]) && suits[4].equals(suits[5]) && suits[5].equals(suits[6]))){

            for(i = 0; i<6; i++){
                if(ranks[i+1] - ranks[i] == 1 || ranks[0] - ranks[6] == -12){
                    cnt1++;
                }
            }

            if(cnt1 >= 4){

                for(i = 0; i<6; i++){
                    for(j = 1; j<7; j++){
                        if(hand[j].getRank() - hand[i].getRank() == 1 && hand[j].getSuit().equals(hand[i].getSuit()))
                            cnt2++;
                    }
                }

                if(cnt2 >= 4 + 3 + 2 + 1)
                    return "Straight flush";
            }


        }

        return null;
    }

    public String fourOfaKind(){
        int i;
        int cnt = 0;
        for(i = 0; i<6; i++){
            if(ranks[i+1] == ranks[i])
                cnt++;
        }

        if(cnt == 4)
            return "Four of a kind";
        else return null;
    }

    public String fullHouse(){
        if(ranks[0] == ranks[1] && ranks [1] == ranks[2]){
            if(ranks[3] == ranks[4] || ranks[4] == ranks[5] || ranks[5] == ranks[6])
                return "Full house";
        }

        if(ranks[1] == ranks[2] && ranks [2] == ranks[3]){
            if(ranks[4] == ranks[5] || ranks[5] == ranks[6])
                return "Full house";
        }

        if(ranks[2] == ranks[3] && ranks [3] == ranks[4]){
            if(ranks[0] == ranks[1] || ranks[5] == ranks[6])
                return "Full house";
        }

        if(ranks[3] == ranks[4] && ranks [4] == ranks[5]){
            if(ranks[0] == ranks[1] || ranks[1] == ranks[2])
                return "Full house";
        }

        if(ranks[4] == ranks[5] && ranks [5] == ranks[6]){
            if(ranks[0] == ranks[1] || ranks[1] == ranks[2] || ranks[2] == ranks[3])
                return "Full house";
        }

        return null;
    }

    public String flush(){
        if((suits[0].equals(suits[1]) && suits[1].equals(suits[2]) && suits[2].equals(suits[3]) && suits[3].equals(suits[4]))
                || (suits[1].equals(suits[2]) && suits[2].equals(suits[3]) && suits[3].equals(suits[4]) && suits[4].equals(suits[5]))
                || (suits[2].equals(suits[3]) && suits[3].equals(suits[4]) && suits[4].equals(suits[5]) && suits[5].equals(suits[6])))
            return "Flush";

        return null;
    }

    public String straight(){
        int i;
        int cnt = 0;
        for(i = 0; i<6; i++){
            if(ranks[i+1] - ranks[i] == 1 || ranks[0] - ranks[6] == -12)
                cnt++;
        }

        if(cnt >=4)
            return "Straight";
        else return null;
    }

    public String threeOfaKind(){
        if(ranks[0] == ranks[1] && ranks [1] == ranks[2]
                || ranks[1] == ranks[2] && ranks [2] == ranks[3]
                || ranks[2] == ranks[3] && ranks [3] == ranks[4]
                || ranks[3] == ranks[4] && ranks [4] == ranks[5]
                || ranks[4] == ranks[5] && ranks [5] == ranks[6])
            return "Three of a kind";

        else return null;
    }

    public String twoPair(){
        int i, cnt = 0;
        for(i = 0; i<6; i++){
            if(ranks[i+1] == ranks[i])
                cnt++;
        }

        if(cnt == 2)
            return "Two pair";
        else return null;
    }

    public String pair(){
        int i, cnt = 0;
        for(i = 0; i<6; i++){
            if(ranks[i+1] == ranks[i])
                cnt++;
        }

        if(cnt == 1)
            return "Pair";
        else return null;
    }

    public String evaluare(){
        String rezultat;

        rezultat = royalFlush();

        if(rezultat == null || rezultat.length() == 0)
            rezultat = straightFlush();

        if(rezultat == null || rezultat.length() == 0)
            rezultat = fourOfaKind();

        if(rezultat == null || rezultat.length() == 0)
            rezultat = fullHouse();

        if(rezultat == null || rezultat.length() == 0)
            rezultat = flush();

        if(rezultat == null || rezultat.length() == 0)
            rezultat = straight();

        if(rezultat == null || rezultat.length() == 0)
            rezultat = threeOfaKind();

        if(rezultat == null || rezultat.length() == 0)
            rezultat = twoPair();

        if(rezultat == null || rezultat.length() == 0)
            rezultat = pair();

        if(rezultat == null || rezultat.length() == 0)
            rezultat = "High card";

        return rezultat;
    }

}
