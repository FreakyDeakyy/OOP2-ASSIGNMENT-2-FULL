package com.champlain.oop2assignment3;

import java.util.Comparator;

public class ReverseRankFirstComparator implements Comparator<Card> {
    /**
     * Compares two cards first by suit in reverse order, and then by rank in
     * reverse order if the suits are the same.
     *
     * @param card1 the first card to be compared
     * @param card2 the second card to be compared
     * @return a negative integer, zero, or a positive integer as the first card
     * is less than, equal to, or greater than the second card
     * @author Owen Huxter
     */
    @Override
    public int compare(Card card1, Card card2) {
        // Compare by suit first (in reverse order)
        int RankComparison = card2.getRank().compareTo(card1.getRank());
        if (RankComparison != 0) {
            return RankComparison;
        }
        // If suits are the same, compare by rank (in reverse order)
        return card2.getSuit().compareTo(card1.getSuit());
    }

}
