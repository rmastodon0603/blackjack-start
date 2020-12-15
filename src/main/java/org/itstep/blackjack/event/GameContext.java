package org.itstep.blackjack.event;

import org.itstep.blackjack.card.Card;

public class GameContext {
    private Card card;

    public GameContext(Card card) {
        this.card = card;
    }

    public GameContext() {

    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
