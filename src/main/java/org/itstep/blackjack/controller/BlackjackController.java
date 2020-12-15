package org.itstep.blackjack.controller;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.util.stream.Collectors;

import org.itstep.blackjack.Card;
import org.itstep.blackjack.Game;
import org.itstep.blackjack.Rank;
import org.itstep.blackjack.Suite;
import org.itstep.blackjack.ui.CardView;

public class BlackjackController {

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

    // private CardView[] cards;
    // private int idx;

    private final Game game;

    public BlackjackController() {
        game = new Game();
    }

    @FXML
    public void onPlay(ActionEvent actionEvent) {
        System.out.println("Play game");
        lblBlackJack.setVisible(false);

        game.play();

        hbPlayerCards.getChildren().clear();
        hbDealerCards.getChildren().clear();

        hbPlayerCards.getChildren()
                .addAll(game.getPlayer().getCards().stream().map(CardView::new).collect(Collectors.toList()));
        hbDealerCards.getChildren()
                .addAll(game.getDealer().getCards().stream().map(CardView::new).collect(Collectors.toList()));
        updatePoints();
    }

    private void updatePoints() {
        lblPlayer.setText("Player: " + game.getPlayer().getPoints());
        lblDealer.setText("Dealer: " + game.getDealer().getPoints());
    }

    @FXML
    public void onStand(ActionEvent actionEvent) {
        System.out.println("Stand");
        game.stand();
        updatePoints();
        hbDealerCards.getChildren().clear();
        hbDealerCards.getChildren()
                .addAll(game.getDealer().getCards().stream().map(CardView::new).collect(Collectors.toList()));
    }

    @FXML
    void onHit(ActionEvent actionEvent) {
        System.out.println("Hit");
        game.hit();
        hbPlayerCards.getChildren().add(new CardView(game.getPlayer().getLastCard()));
        updatePoints();
    }
}
