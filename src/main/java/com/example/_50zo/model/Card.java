package com.example._50zo.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents a playing card used in the game.
 * Each card has an image, a value, and a corresponding ImageView
 * for graphical display within a JavaFX interface.
 */
public class Card {
    private String url;
    private String value;
    private Image image;
    private ImageView cardImageView;

    /**
     * Constructs a new Card with the specified image URL and value.
     * The card image is loaded from the given URL, and an ImageView is created for display.
     *
     * @param url   the relative path to the card image resource
     * @param value the value or label of the card (e.g., "5", etc.)
     */
    public Card(String url, String value) {
        this.url = url;
        this.value = value;
        try {
            this.image = new Image(String.valueOf(getClass().getResource(url)));
            this.cardImageView = createCardImageView();
        } catch (Exception e) {
            // Evita error si JavaFX no está inicializado (por ejemplo, en pruebas)
            this.image = null;
            this.cardImageView = null;
        }
    }

    /**
     * Alternative builder without graphical resources (for unit testing).
     */
    public Card(String value) {
        this.url = null;
        this.value = value;
        this.image = null;
        this.cardImageView = null;
    }



    /**
     * Returns the URL of the card image resource.
     *
     * @return the URL of the card image
     */
    public String getURL() {
        return url;
    }

    /**
     * Creates and configures an ImageView for this card’s image.
     * The ImageView is set with fixed dimensions and a predefined Y position.
     *
     * @return a configured ImageView representing this card
     */
    private ImageView createCardImageView() {
        ImageView card = new ImageView(this.image);
        card.setY(426);
        card.setFitHeight(128);
        card.setFitWidth(82);
        return card;
    }

    /**
     * Returns the ImageView representation of this card.
     * If the image or ImageView is not initialized, they are reloaded automatically.
     *
     * @return the ImageView associated with this card
     */
    public ImageView getCard() {
        if (cardImageView == null || image == null) {
            try {
                this.image = new Image(String.valueOf(getClass().getResource(url)));
                this.cardImageView = createCardImageView();
            } catch (Exception e) {
                this.image = null;
                this.cardImageView = null;
            }
        }
        return cardImageView;
    }

    /**
     * Returns the Image object representing this card.
     * If the image is not initialized, it is loaded from the URL.
     *
     * @return the Image of this card
     */
    public Image getImage() {
        if (image == null && url != null) {
            try {
                this.image = new Image(String.valueOf(getClass().getResource(url)));
            } catch (Exception e) {
                this.image = null;
            }
        }
        return image;
    }

    /**
     * Returns the logical value of this card (e.g., "7", "Ace", etc.).
     *
     * @return the value of the card
     */
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
        if (this.value == null) {
            System.err.println("Advertencia: El valor de la carta es nulo. Devolviendo 0.");
            return 0;}
            switch (this.value) {
                case "A":
                    return (currentTotal + 10 <= 50) ? 10 : 1;
                case "2":
                    return 2;
                case "3":
                    return 3;
                case "4":
                    return 4;
                case "5":
                    return 5;
                case "6":
                    return 6;
                case "7":
                    return 7;
                case "8":
                    return 8;
                case "9":
                    return 0;
                case "10":
                    return 10;
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

