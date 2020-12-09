package org.itstep.blackjack.ui;

import javafx.scene.image.Image;
import org.itstep.blackjack.Card;

public class CardView {
    private final Card card;
    private final Image image;

    private String pathToCard(String cardFilename) {
        if(cardFilename == null) return null;
        String[] parts = cardFilename.split("/");
        return CardView.class.getClassLoader().getResource(parts[0]) + (parts.length == 2 ? parts[1] : "");
    }

    public CardView(Card card) {
        this.card = card;
        String imagePath = "cards/" + card.getRank().getName()
                + "_of_" + card.getSuite().getName() + ".png";
        this.image = new Image(pathToCard(imagePath));
    }

    public Image getImage() {
        return image;
    }

    public Card getCard() {
        return card;
    }
}
