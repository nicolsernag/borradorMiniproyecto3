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
}
