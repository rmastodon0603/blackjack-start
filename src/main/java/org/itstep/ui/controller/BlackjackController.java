package org.itstep.ui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;
import org.itstep.blackjack.Game;
import org.itstep.blackjack.NoMoneyEnough;
import org.itstep.blackjack.card.Card;
import org.itstep.blackjack.event.EventListener;
import org.itstep.blackjack.event.EventType;
import org.itstep.blackjack.event.GameContext;
import org.itstep.ui.CardView;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class BlackjackController implements Initializable {

    @FXML
    private Label lblDealer;
    @FXML
    private Label lblBlackJack;
    @FXML
    private Label lblPlayer;
    @FXML
    private Label lblCash;

    @FXML
    private Button btnPlay;
    @FXML
    private Button btnStand;
    @FXML
    private Button btnHit;

    @FXML
    private TextField tfBet;

    @FXML
    private HBox hbDealerCards;
    @FXML
    private HBox hbPlayerCards;

    private final Game game;

    public BlackjackController() {
        game = new Game();
        game.addEvent(EventType.HIT, data -> {
            Card card = data.getCard();
            hbPlayerCards.getChildren().add(new CardView(card));
            updatePoints();
        });
        game.addEvent(EventType.START, data -> {
            start();
            updatePoints();
        });
        game.addEvent(EventType.PLAYER_GET_CARD, data -> {
            hbPlayerCards.getChildren().add(new CardView(data.getCard()));
            updatePoints();
        });
        game.addEvent(EventType.DEALER_GET_CARD, data -> {
            hbDealerCards.getChildren().add(new CardView(data.getCard()));
            updatePoints();
        });
        game.addEvent(EventType.STAND, data -> {
            CardView node = (CardView) hbDealerCards.getChildren().get(0);
            Card card = node.getCard();
            card.setHide(false);
            hbDealerCards.getChildren().set(0, new CardView(card));
            updatePoints();
        });
        game.addEvent(EventType.GAME_OVER, data -> {
            updatePoints();
            stop();
            lblBlackJack.setText(game.getWinner() + " WIN");
        });
    }

    @FXML
    public void onPlay(ActionEvent actionEvent) {
        System.out.println("Play game");
        start();
        game.play();
        try {
            game.setBet(getBet());
        } catch (NoMoneyEnough noMoneyEnough) {
            noMoneyEnough.printStackTrace();
        }
    }

    private int getBet() {
        if(tfBet.getText().isEmpty()) {
            return 0;
        }
        return Integer.parseInt(tfBet.getText());
    }

    private void updatePoints() {
        lblPlayer.setText("Player: " + game.getPlayer().getPoints());
        lblDealer.setText("Dealer: " + game.getDealer().getPoints());
    }

    @FXML
    public void onStand(ActionEvent actionEvent) {
        System.out.println("Stand");
        stop();
        game.stand();
    }

    @FXML
    void onHit(ActionEvent actionEvent) {
        System.out.println("Hit");
        game.hit();
    }

    private void start() {
        hbPlayerCards.getChildren().clear();
        hbDealerCards.getChildren().clear();
        btnHit.setDisable(false);
        btnStand.setDisable(false);
        btnPlay.setDisable(true);
        lblBlackJack.setVisible(false);
    }

    private void stop() {
        btnHit.setDisable(true);
        btnStand.setDisable(true);
        btnPlay.setDisable(false);
        lblBlackJack.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tfBet.setTextFormatter(new TextFormatter<>(change -> {
            String text = change.getText();

            if (text.matches("[0-9]*")) {
                return change;
            }

            return null;
        }));
        stop();
    }
}
