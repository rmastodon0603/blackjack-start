package org.itstep.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards;
    private final List<Card> taken;

    public Deck() {
        this.taken = new ArrayList<>();
        this.cards = new ArrayList<>(52);
        for (Suite suite : Suite.values()) {
            for (Rank rank : Rank.values()) {
                this.cards.add(new Card(rank, suite));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card getOne() {
        Card card = cards.remove(0);
        taken.add(card);
        return card;
    }

    public void reset() {
        cards.addAll(taken);
        taken.clear();
    }

}
