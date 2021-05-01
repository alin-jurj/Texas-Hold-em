package Game.mechanics;

public class Card {
        private int rank;
        private String suit;

        public Card(int rank, String suit){ //constructor carte
            this.rank = rank;
            this.suit = suit;
        }

        public String getSuit(){
            return suit;
        }  //returneaza culoarea cartii

        public int getRank(){
            return rank;
        }  //returneaza numarul cartii

        public String toString(){
            return rank + "of"  + suit;
        }

        public String whichCard(){          //Cauta poza corespunzatoare fiecarei carti
            if(suit.equals("hearts")){
                if(rank == 2)
                    return "/img/2h.png";
                if(rank == 3)
                    return "/img/3h.png";
                if(rank == 4)
                    return "/img/4h.png";
                if(rank == 5)
                    return "/img/5h.png";
                if(rank == 6)
                    return "/img/6h.png";
                if(rank == 7)
                    return "/img/7h.png";
                if(rank == 8)
                    return "/img/8h.png";
                if(rank == 9)
                    return "/img/9h.png";
                if(rank == 10)
                    return "/img/10h.png";
                if(rank == 11)
                    return "/img/jh.png";
                if(rank == 12)
                    return "/img/qh.png";
                if(rank == 13)
                    return "/img/kh.png";
            }

            if(suit.equals("clubs")){
                if(rank == 1)
                    return "/img/ac.png";
                if(rank == 2)
                    return "/img/2c.png";
                if(rank == 3)
                    return "/img/3c.png";
                if(rank == 4)
                    return "/img/4c.png";
                if(rank == 5)
                    return "/img/5c.png";
                if(rank == 6)
                    return "/img/6c.png";
                if(rank == 7)
                    return "/img/7c.png";
                if(rank == 8)
                    return "/img/8c.png";
                if(rank == 9)
                    return "/img/9c.png";
                if(rank == 10)
                    return "/img/10c.png";
                if(rank == 11)
                    return "/img/jc.png";
                if(rank == 12)
                    return "/img/qc.png";
                if(rank == 13)
                    return "/img/kc.png";
            }

            if(suit.equals("spades")){
                if(rank == 1)
                    return "/img/as.png";
                if(rank == 2)
                    return "/img/2s.png";
                if(rank == 3)
                    return "/img/3s.png";
                if(rank == 4)
                    return "/img/4s.png";
                if(rank == 5)
                    return "/img/5s.png";
                if(rank == 6)
                    return "/img/6s.png";
                if(rank == 7)
                    return "/img/7s.png";
                if(rank == 8)
                    return "/img/8s.png";
                if(rank == 9)
                    return "/img/9s.png";
                if(rank == 10)
                    return "/img/10s.png";
                if(rank == 11)
                    return "/img/js.png";
                if(rank == 12)
                    return "/img/qs.png";
                if(rank == 13)
                    return "/img/ks.png";
            }

            if(suit.equals("diamonds")){
                if(rank == 1)
                    return "/img/ad.png";
                if(rank == 2)
                    return "/img/2d.png";
                if(rank == 3)
                    return "/img/3d.png";
                if(rank == 4)
                    return "/img/4d.png";
                if(rank == 5)
                    return "/img/5d.png";
                if(rank == 6)
                    return "/img/6d.png";
                if(rank == 7)
                    return "/img/7d.png";
                if(rank == 8)
                    return "/img/8d.png";
                if(rank == 9)
                    return "/img/9d.png";
                if(rank == 10)
                    return "/img/10d.png";
                if(rank == 11)
                    return "/img/jd.png";
                if(rank == 12)
                    return "/img/qd.png";
                if(rank == 13)
                    return "/img/kd.png";
            }
            return "/img/ah.png";
        }

}
