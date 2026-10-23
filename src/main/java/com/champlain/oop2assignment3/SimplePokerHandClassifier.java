package com.champlain.oop2assignment3;

import java.util.ArrayList;
import java.util.List;

public class SimplePokerHandClassifier implements PokerHandClassifier {

    private final int ROYAL_FLUSH_ORDINAL = PokerHandType.ROYAL_FLUSH.ordinal();
    private final int STRAIGHT_FLUSH_ORDINAL = PokerHandType.STRAIGHT_FLUSH.ordinal();
    private final int FOUR_OF_A_KIND_ORDINAL = PokerHandType.FOUR_OF_A_KIND.ordinal();
    private final int FULL_HOUSE_ORDINAL = PokerHandType.FULL_HOUSE.ordinal();
    private final int FLUSH_ORDINAL = PokerHandType.FLUSH.ordinal();
    private final int STRAIGHT_ORDINAL = PokerHandType.STRAIGHT.ordinal();
    private final int THREE_OF_A_KIND_ORDINAL = PokerHandType.THREE_OF_A_KIND.ordinal();
    private final int TWO_PAIR_ORDINAL = PokerHandType.TWO_PAIR.ordinal();
    private final int ONE_PAIR_ORDINAL = PokerHandType.PAIR.ordinal();
    private final int HIGH_CARD_ORDINAL = PokerHandType.HIGH_CARD.ordinal();

    @Override
    public PokerHandType classify(CardCollection pCards) {
        if (pCards == null) {
            throw new IllegalArgumentException("Card collection cannot be null.");
        }
        if (pCards.size() != 5) {
            throw new IllegalArgumentException("Card collection must contain exactly 5 cards.");
        }

        List<Card> sortedCards = new ArrayList<>();

        for (Card card : pCards) {
            sortedCards.add(card);
        }

        sortedCards.sort(new ReverseRankFirstComparator());

        System.out.println("Sorted Cards: " + sortedCards);
        return pokerHands(sortedCards);
    }

    private PokerHandType pokerHands(List<Card> sortedCards) {
        int EnumeratorValue;
        if (allSameSuit(sortedCards)) {
            if (isStraight(sortedCards)) {
                if (sortedCards.get(0).getRank() == Rank.KING) {
                    EnumeratorValue = ROYAL_FLUSH_ORDINAL;
                } else {
                    EnumeratorValue = STRAIGHT_FLUSH_ORDINAL;
                }
            } else {
                EnumeratorValue = FLUSH_ORDINAL;
            }
        } else if (isStraight(sortedCards)) {
            EnumeratorValue = STRAIGHT_ORDINAL;
        } else if (isFullHouse(sortedCards)) {
            EnumeratorValue = FULL_HOUSE_ORDINAL;
        } else {
            EnumeratorValue = xOfAKind(sortedCards);
        }

        return PokerHandType.values()[EnumeratorValue];
    }

    private boolean allSameSuit(List<Card> sortedCards) {
        for (Card card : sortedCards) {
            if (card.getSuit() != sortedCards.get(0).getSuit()) {
                return false;
            }
        }
        return true;
    }

    private boolean isStraight(List<Card> sortedCards) {
        for (int i = 0; i < sortedCards.size() - 1; i++) {
            if (sortedCards.get(i).getRank().ordinal() - sortedCards.get(i + 1).getRank().ordinal() != 1) {
                return false;
            }
        }
        return true;
    }

    private boolean isFullHouse(List<Card> sortedCards) {
        int firstGroupSize = 1;
        int secondGroupSize = 1;

        for (int i = 1; i < sortedCards.size(); i++) {
            if (sortedCards.get(i).getRank() == sortedCards.get(i - 1).getRank()) {
                firstGroupSize++;
            } else {
                secondGroupSize = sortedCards.size() - i;
                break;
            }
        }

        return (firstGroupSize == 3 && secondGroupSize == 2) || (firstGroupSize == 2 && secondGroupSize == 3);
    }

    private int xOfAKind(List<Card> sortedCards) {
        int largestGroup = 1;
        int currentGroup = 1;
        int pairCount = 0;

        for (int i = 1; i < sortedCards.size(); i++) {
            if (sortedCards.get(i).getRank() == sortedCards.get(i - 1).getRank()) {
                currentGroup++;
            } else {
                if (currentGroup == 2) {
                    pairCount++;
                }
                currentGroup = 1;
            }

            if (currentGroup > largestGroup) {
                largestGroup = currentGroup;
            }
        }

        if (currentGroup == 2) {
            pairCount++;
        }

        return xOfAKindSwitch(largestGroup, pairCount);
    }

    private int xOfAKindSwitch(int largestGroup, int pairCount) {
        return switch (largestGroup) {
        case 4 -> FOUR_OF_A_KIND_ORDINAL;
        case 3 -> THREE_OF_A_KIND_ORDINAL;
        case 2 -> pairCount == 2 ? TWO_PAIR_ORDINAL : ONE_PAIR_ORDINAL;
        default -> HIGH_CARD_ORDINAL;
        };
    }
}
