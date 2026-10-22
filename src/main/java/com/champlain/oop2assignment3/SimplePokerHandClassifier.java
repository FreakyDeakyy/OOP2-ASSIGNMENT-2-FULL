package com.champlain.oop2assignment3;

public class SimplePokerHandClassifier implements PokerHandClassifier {

    private Deck aDeck;

    @Override
    public PokerHandType classify(CardCollection pCards) {
        if (pCards == null) {
            throw new IllegalArgumentException("Card collection cannot be null.");
        }
        if (pCards.size() != 5) {
            throw new IllegalArgumentException("Card collection must contain exactly 5 cards.");
        }

        this.aDeck = (Deck) pCards;

        this.aDeck.sort(new ReverseRankFirstComparator());

        return PokerHandType.HIGH_CARD;
    }

    private boolean isFlush(CardCollection pCards) {
        Suit firstSuit = null;
        for (Card card : pCards) {
            if (firstSuit == null) {
                firstSuit = card.getSuit();
            } else if (card.getSuit() != firstSuit) {
                return false;
            }
        }
        return true;
    }
}
