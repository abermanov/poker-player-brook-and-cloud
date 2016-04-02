package org.leanpoker.player.model;

import org.leanpoker.player.Suit;

/**
 * Created by adippel on 02.04.2016.
 */
public class Card {

    private int rank;
    private Suit suit;

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }
}
