package org.itstep.blackjack;

import org.itstep.blackjack.card.Card;
import org.itstep.blackjack.event.EventListener;
import org.itstep.blackjack.event.EventType;
import org.itstep.blackjack.event.GameContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
    private final Player player;
    private final Player dealer;
    private final Deck deck;
    private final Map<EventType, List<EventListener<? super GameContext>>> events;

    public Game() {
        deck = new Deck();
        dealer = new Player(0);
        player = new Player(1000);
        events = new HashMap<>();
    }

    public void addEvent(EventType type, EventListener<? super GameContext> listener) {
        events.putIfAbsent(type, new ArrayList<>());
        events.get(type).add(listener);
    }

    public void hit() {
        Card card = deck.getOne();
        player.takeCard(card);
        publishHit(card);
        if(player.getPoints() > 21) {
            publishGameOver();
        }
    }

    public Player getDealer() {
        return dealer;
    }

    public Player getPlayer() {
        return player;
    }

    public void stand() {
        dealer.getCards().get(0).setHide(false);
        publishStand();
        while (dealer.getPoints() < 15) {
            Card card = deck.getOne();
            dealer.takeCard(card);
            publishDealerTakeCard(card);
        }
        publishGameOver();
    }

    public void setBet(int amount) throws NoMoneyEnough {
        player.setBet(amount);
    }

    private void publishStand() {
        if(events.containsKey(EventType.STAND)) {
            events.get(EventType.STAND)
                    .forEach(eventListener -> eventListener.handle(new GameContext()));
        }
    }

    private void publishHit(Card card) {
        if(events.containsKey(EventType.HIT)) {
            events.get(EventType.HIT)
                    .forEach(l -> l.handle(new GameContext(card)));
        }
    }

    private void publishStart() {
        if(events.containsKey(EventType.START)) {
            events.get(EventType.START)
                    .forEach(eventListener -> eventListener.handle(new GameContext()));
        }
    }

    private void publishGameOver() {
        if(events.containsKey(EventType.GAME_OVER)) {
            events.get(EventType.GAME_OVER)
                    .forEach(l -> l.handle(new GameContext()));
        }
    }

    private void publishPlayerTakeCard(Card card) {
        if(events.containsKey(EventType.PLAYER_GET_CARD)) {
            events.get(EventType.PLAYER_GET_CARD)
                    .forEach(eventListener -> eventListener.handle(new GameContext(card)));
        }
    }

    private void publishDealerTakeCard(Card card) {
        if(events.containsKey(EventType.DEALER_GET_CARD)) {
            events.get(EventType.DEALER_GET_CARD)
                    .forEach(eventListener -> eventListener.handle(new GameContext(card)));
        }
    }

    public String getWinner() {
        if(player.getPoints() <= 21) {
            if(dealer.getPoints() > 21) {
                return "Player";
            } else if(player.getPoints() > dealer.getPoints()) {
                return "Player";
            } else {
                return "Dealer";
            }
        }
        return "Dealer";
    }

    public void play() {
        System.out.println("Game start");
        deck.shuffle();
        player.clear();
        dealer.clear();
        publishStart();

        Card first = deck.getOne();
        player.takeCard(first);
        publishPlayerTakeCard(first);

        Card second = deck.getOne();
        player.takeCard(second);
        publishPlayerTakeCard(second);

        Card hiddenCard = deck.getOne();
        hiddenCard.setHide(true);
        dealer.takeCard(hiddenCard);
        publishDealerTakeCard(hiddenCard);

        Card fourth = deck.getOne();
        dealer.takeCard(fourth);
        publishDealerTakeCard(fourth);
    }
}
