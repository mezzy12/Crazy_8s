package com.example.kyle.texasholdem;
import java.util.Random;
/**
 * Created by Kyle on 9/11/2015.
 */

import java.util.Stack;
import java.util.Vector;

public class Dealer {

    public Stack<Card> deck;
    public Card lastPlayed;

    public void shuffleDeck()
    {
        int counter = 0;
        Card[] temp = new Card[52];
        Card swapper, swape;
        Random random = new Random();
        random.nextInt();
        int change;

        // Initialize the deck
        for(int i=1;i<13;i++)
        {
            for(int j=1;j<4;j++)
            {
                temp[counter].value = i;
                temp[counter].suit = j;
                counter++;
            }
        }

        // Shuffle the deck
        for(int n=0; n<52; n++)
        {
            change = n + random.nextInt(52 - n);
            swapper = temp[change];
            swape = temp[n];
            temp[n] = swapper;
            temp[change] = swape;
        }

        // Move deck to a stack
        for(int k=0; k<52; k++)
        {
            deck.push(temp[k]);

        }
    }

    public Card draw()
    {
        return deck.pop();
    }


    public void newGame(Player p1, cpuPlayer p[])
    {
        shuffleDeck();
        p1.draw(true, this);
        for(int i=0; i<3; i++)
            p[i].draw(true, this);
        lastPlayed = deck.pop();

        while(deck.size() != 0)
        {
            round(p1, p);
        }

    }

    public void round(Player p1, cpuPlayer p[])
    {
        lastPlayed = p1.turn(lastPlayed, this);
        // Would be nice to add a 3 sec delay between cpuPlayer turns
        // without locking up the UI because of sleep(3000);
        for(int i=0;i<3;i++)
            lastPlayed = p[i].turn(lastPlayed, this);
    }

}
