package Game.mechanics;

import Game.mechanics.Card;

import java.util.Random;
public class Deck {

        private Card[] deck = new Card[52];
        private int[] marked = new int[52];  // marcheaza cartea care a fost deja extrasa sa se evite duplicatele

        Random random = new Random();
        int upperbound = 52;

        public Deck(){
            int k;
            for(k = 0; k<13;k++){
                deck[k] = new Card(k+1, "hearts");  //13 carti de prima culoare
                marked[k] = 0;
            }
            for(k = 13; k<26;k++){
                deck[k] = new Card(k-12, "diamonds"); //13 carti de a doua culoare
                marked[k] = 0;
            }
            for(k = 26; k<39;k++){
                deck[k] = new Card(k-25, "clubs"); //13 carti de a treia culoare
                marked[k] = 0;
            }
            for(k = 39; k<52;k++){
                deck[k] = new Card(k-38, "spades"); // 13 carti de a patra culoare
                marked[k] = 0;
            }
        }

        public Card giveCard(){                     //extrage o carte si o marcheaza pentru evitarea duplicatelor
            int i = random.nextInt(upperbound);
            while(marked[i] == 1){
                i = random.nextInt(upperbound);
            }

            marked[i] = 1;
            return deck[i];
        }

        public void resetMarked(){
            for(int i = 0; i<52; i++)
                marked[i] = 0;
        }
}

