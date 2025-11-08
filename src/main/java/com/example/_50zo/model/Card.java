package com.example._50zo.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Card{
    private String url;
    private String value;
    private Image image;
    private ImageView cardImageView;

    public Card(String url, String value) {
        this.url = url;
        this.value = value;
        this.image = new Image(String.valueOf(getClass().getResource(url)));
        this.cardImageView = createCardImageView();
    }


    private ImageView createCardImageView() {
        ImageView card = new ImageView(this.image);
        card.setY(426);
        card.setFitHeight(128);
        card.setFitWidth(82);
        return card;
    }


    public ImageView getCard() {
        if (cardImageView == null || image == null) {
            this.image = new Image(String.valueOf(getClass().getResource(url)));
            this.cardImageView = createCardImageView();
        }
        return cardImageView;
    }


    public Image getImage() {
        if (image == null) {
            this.image = new Image(String.valueOf(getClass().getResource(url)));
        }
        return image;
    }


    public String getValue() {
        return value;
    }

    /**
     * Returns the numeric value of the card according to the game rules.
     * A = 1 or 10 (whichever is more convenient without exceeding 50)
     * 2–8 = face value
     * 9 = 0
     * 10 = 10
     * J, Q, K = -10
     *
     * @param currentTotal the current sum on the table
     * @return the numeric value of the card
     */
    public int getNumericValue(int currentTotal) {
        switch (this.value) {
            case "A":
                return (currentTotal + 10 <= 50) ? 10 : 1;// o 10 según tus reglas
            case "2": return 2;
            case "3": return 3;
            case "4": return 4;
            case "5": return 5;
            case "6": return 6;
            case "7": return 7;
            case "8": return 8;
            case "9": return 0;
            case "10": return 10;
            case "J":
            case "Q":
            case "K":
                return -10;
            case "0":
                return 0;
            default:
                try {
                    return Integer.parseInt(value);
                } catch (NumberFormatException e) {
                    return 0;
                }
        }
    }
}
