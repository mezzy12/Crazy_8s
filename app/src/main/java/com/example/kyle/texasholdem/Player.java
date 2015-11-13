package com.example.kyle.texasholdem;
import java.util.Vector;

/**
 * Created by Kyle on 9/11/2015.
 */
public class Player {
    public Vector<Card> hand = new Vector<>(8,2);
    public int points = 0;

    public Card turn(Card last, Dealer dealer)
    {
        // User picks a card from their hand
        Card picked = new Card();
        boolean done = false;
        do {
            if(last.suit == picked.suit || last.value == picked.value || picked.value == 8)
            {
                if(picked.value == 8)
                {
                    //pick new suit

                    points += 50;
                }
                done = true;
            }

            //pick a different card or draw

        }while(!done);
        return picked;
    }

    public void draw(boolean firstDraw, Dealer dealer){
        if(firstDraw)
        {
            hand.removeAllElements();
            for(int i=0;i<8;i++)
            {
                draw(false, dealer);
            }
        }
        else
        {
            if(dealer.deck.size() != 0)
                hand.add(dealer.draw());
        }

    }



}


