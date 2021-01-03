package org.itstep.blackjack;

public class Game {

    private final Player player;
    private final Player dealer;
    private final Deck deck;

    public Game() {
        this.player = new Player(100);
        this.dealer = new Player(0);
        this.deck = new Deck();
    }

    public Player getPlayer() {
        return player;
    }

    public Player getDealer() {
        return dealer;
    }

    public void play(){
        deck.reset();
        player.clear();
        dealer.clear();
        deck.shuffle();

        Card first = deck.getOne();
        player.takeCard(first);

        Card second = deck.getOne();
        player.takeCard(second);

        Card dealerFirst = deck.getOne();
        dealerFirst.setHide(true);
        dealer.takeCard(dealerFirst);

        Card dealerSecond = deck.getOne();
        dealer.takeCard(dealerSecond);
    }

    public void hit(){
        Card card = deck.getOne();
        player.takeCard(card);
    }

    public void setBet(int amount) throws NoMoneyEnough {
        player.setBet(amount);
    }

    public void stand(){
        if(player.getPoints()<21){
            while (dealer.getPoints()<16){
                Card card = deck.getOne();
                dealer.takeCard(card);
            }
        }
    }
}