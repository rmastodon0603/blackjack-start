package org.itstep.blackjack;

public enum Suite {
    CLUBS, DIAMONDS, HEARTS, SPADES;

    public String getName() {
        return this.toString().toLowerCase();
    }
}
