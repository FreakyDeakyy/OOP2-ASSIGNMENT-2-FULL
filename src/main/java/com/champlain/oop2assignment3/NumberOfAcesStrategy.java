package com.champlain.oop2assignment3;

public class NumberOfAcesStrategy implements ScoreStrategy {
    @Override
    public int calculateScore(CardCollection pHand) {
        int score = 0;
        for (Card card : pHand) {
            if (card.getRank() == Rank.ACE) {
                score++;
            }
        }
        return score;
    }

}
