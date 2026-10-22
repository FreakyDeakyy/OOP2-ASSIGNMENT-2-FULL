package com.champlain.oop2assignment3;

public class SimpleCountStrategy implements ScoreStrategy {

    @Override
    public int calculateScore(CardCollection pCards) {
        return pCards.size();
    }
}
