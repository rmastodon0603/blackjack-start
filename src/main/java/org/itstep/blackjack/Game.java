package org.itstep.blackjack;

public class Game {
    private final Player player;
    private final Player dealer;
    private final Deck deck;

    public Game() {
        deck = new Deck();
        dealer = new Player(0);
        player = new Player(1000);
    }

    public void hit() {
        player.takeCard(deck.getOne());
    }

    public Player getDealer() {
        return dealer;
    }

    public Player getPlayer() {
        return player;
    }

    public void stand() {
        dealer.getCards().get(0).setHide(false);
        while (dealer.getPoints() < 15) {
            dealer.takeCard(deck.getOne());
        }
    }

    public void setBet(int amount) {

    }

    public void play() {
        System.out.println("Game start");
        deck.shuffle();
        player.clear();
        dealer.clear();
        player.takeCard(deck.getOne());
        player.takeCard(deck.getOne());

        Card hiddenCard = deck.getOne();
        hiddenCard.setHide(true);
        dealer.takeCard(hiddenCard);
        dealer.takeCard(deck.getOne());

    }
}
