package org.itstep.blackjack;

public class Card {
    private final Rank rank;
    private final Suite suite;

    public Card(Rank rank, Suite suite) {
        this.rank = rank;
        this.suite = suite;
    }

    public Rank getRank() {
        return rank;
    }

    public Suite getSuite() {
        return suite;
    }

    public int getValue() {
        return rank.getValue();
    }

    @Override
    public String toString() {
        return "Card{" +
                "rank=" + rank +
                ", suite=" + suite +
                '}';
    }
}
