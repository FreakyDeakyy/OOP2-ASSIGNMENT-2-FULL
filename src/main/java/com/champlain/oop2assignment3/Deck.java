package com.champlain.oop2assignment3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a deck of playing cards.
 * <p>
 * This class allows for creating a standard deck, shuffling it, drawing cards,
 * and checking if the deck is empty.
 * </p>
 */
public class Deck extends CardCollection implements CardSource {
    /**
     * The list of cards in the deck.
     */
    private final List<Card> aCards = new ArrayList<>();

    /**
     * Singleton instance of the Deck class.
     */
    private static Deck aInstance;

    /**
     * Constructs a new Deck containing all standard playing cards. The deck is
     * initialized with one of each rank and suit combination.
     */
    private Deck() {
        for (Rank currentRank : Rank.values()) {
            for (Suit currentSuit : Suit.values()) {
                this.aCards.add(new Card(currentRank, currentSuit));
            }
        }
        this.aCards.add(new Card(Rank.ACE, Suit.SPADES));
    }

    /**
     * Returns the singleton instance of the Deck class.
     *
     * @return the singleton instance of the Deck
     */
    public static Deck getInstance() {
        if (aInstance == null) {
            aInstance = new Deck();
        }
        return aInstance;
    }

    /**
     * Sorts the cards in this deck using the specified comparator.
     *
     * @param comparator the comparator to determine the order of the cards
     * @author Owen Huxter
     */
    public void sort(Comparator<Card> comparator) {
        Collections.sort(this.aCards, comparator);
    }

    /**
     * Shuffles the cards in this deck randomly.
     */
    public void shuffle() {
        Collections.shuffle(this.aCards);
    }

    /**
     * Draws a card from the top of the deck. The drawn card is removed from the
     * deck.
     *
     * @return the drawn card
     */
    @Override
    public Card draw() {
        int last = this.aCards.size() - 1;
        Card myCard = this.aCards.get(last);
        this.aCards.remove(last);
        return myCard;
    }

    /**
     * Checks if the deck is empty.
     *
     * @return true if the deck is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return this.aCards.isEmpty();
    }

    /**
     * Returns an iterator over the cards in this deck.
     *
     * @return an iterator for the cards
     */
    @Override
    public Iterator<Card> iterator() {
        return Collections.unmodifiableList(this.aCards).iterator();
    }
}
