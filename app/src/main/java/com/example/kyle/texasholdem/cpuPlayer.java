package com.example.kyle.texasholdem;

import java.io.Serializable;
import java.util.Random;

public class cpuPlayer extends Player{

    public String announcement;

    public Card turn(Card last, Dealer dealer)
    {
        Card played = new Card();
        boolean done = false;
        // 1) Check for any 8's and play them first
        // 2) play a card with the same suit or value as the last played card
        // If there are none of those draw until one of the previous conditions are met

        for(int i=0; i<(hand.size()); i++) // 1
        {
            if (hand.get(i).value == 8) {
                played.value = 0; // After an 8 is played, you have to match the called suit. 0 will never match with any value
                Random random = new Random();
                played.suit = random.nextInt(4) + 1; //TODO: make this pick the suit that this player has the most of
                points += 50;
                hand.removeElement(hand.get(i));
                done = true;
                break;
            }
        }
        do
        {
            for(int i=0; i<(hand.size()); i++) // 2
            {
                if (hand.get(i).value == last.value || hand.get(i).value == last.suit) //TODO: make this pick the largest value card
                {
                    played = hand.get(i);
                    if (played.value > 10)
                        points += 10;
                    else
                        points += played.value;
                    done = true;
                    break; //remove this when implementing picking the largest value so it can cycle through all options
                }
            }
            if(!done)
                draw(false, dealer); //Draw until you get a card you can play
        }while(!done);

        Serializable name = (played.value==1)?"Ace":played.value;
        announcement = "played a " + name + " of ";

        if(played.suit == 1)
            announcement += "Hearts.";
        else if(played.suit == 2)
            announcement += "Diamonds.";
        else if(played.suit == 3)
            announcement += "Clubs.";
        else
            announcement += "Spades.";

        return played;
    }




}
