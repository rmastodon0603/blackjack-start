package org.itstep.ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import org.itstep.blackjack.card.Card;

public class CardView extends ImageView {
    private final Card card;
    private final Image image;

    private String pathToCard(String cardFilename) {
        if (cardFilename == null)
            return null;
        String[] parts = cardFilename.split("/");
        return CardView.class.getClassLoader().getResource(parts[0]) + (parts.length == 2 ? parts[1] : "");
    }

    public CardView(Card card) {
        this.card = card;
        String imagePath;
        if(card.isHide()) {
            imagePath = "cards/backside.png";
        } else {
            imagePath = "cards/" + card.getRank().getName() + "_of_" + card.getSuite().getName() + ".png";
        }
        this.image = new Image(pathToCard(imagePath));
        setImage(this.image);
        setFitHeight(120);
        setPreserveRatio(true);
    }

    public Card getCard() {
        return card;
    }
}
