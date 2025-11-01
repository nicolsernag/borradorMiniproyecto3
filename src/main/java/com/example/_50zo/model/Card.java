package com.example._50zo.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Card {
    private final CardEnum type;

    public Card(CardEnum type) {
        this.type = type;
    }

    public String getFilePath() {
        return type.getFilePath(); // devuelve ruta de la imagen
    }

    public CardEnum getType() {
        return type;
    }
}
