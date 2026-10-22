package com.champlain.oop2assignment3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a hand of playing cards.
 * <p>
 * This class allows for adding cards to the hand and checking if the hand is
 * empty. It also provides an iterator to traverse the cards in the hand.
 * </p>
 */
public class Hand extends CardCollection {
    /**
     * The list of cards in this hand.
     */
    private final List<Card> aCards = new ArrayList<>();

    /**
     * Adds a card to this hand.
     *
     * @param pCard the card to be added
     */
    public void addCard(Card pCard) {
        this.aCards.add(pCard);
    }

    /**
     * Checks if this hand is empty.
     *
     * @return true if the hand has no cards, false otherwise
     */
    public boolean isEmpty() {
        return this.aCards.isEmpty();
    }

    /**
     * Checks whether this hand contains two equal cards.
     *
     * @return true if two cards in the hand are equal
     */
    public boolean hasMatchingCards() {
        for (int i = 0; i < this.aCards.size(); i++) {
            for (int j = i + 1; j < this.aCards.size(); j++) {
                if (this.aCards.get(i).equals(this.aCards.get(j))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns an iterator over the cards in this hand.
     *
     * @return an iterator for the cards
     */
    @Override
    public Iterator<Card> iterator() {
        return this.aCards.iterator();
    }
}
