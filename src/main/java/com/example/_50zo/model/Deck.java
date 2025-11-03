package com.example._50zo.model;

import java.util.Collections;
import java.util.Stack;

public class Deck {
    private Stack<Card> deckOfCards;

    /**
     * Constructs a new deck of Uno cards and initializes it.
     */
    public Deck() {
        deckOfCards = new Stack<>();
        initializeDeck();
    }

    /**
     * Initializes the deck with cards based on the EISCUnoEnum values.
     */
    private void initializeDeck() {
        for (CardEnum cardEnum : CardEnum.values()) {
            if(cardEnum != CardEnum.CARD_FACE_DOWN){
                deckOfCards.push(new Card(cardEnum.getFilePath(), getCardValue(cardEnum.name())));
            }
        }
        Collections.shuffle(deckOfCards);
    }

    private String getCardValue(String name) {
        if (name.endsWith("9")){
            return "0";
        } else if (name.endsWith("1")){
            return "A"; //Su valor es 1 o 10, segun convenga
        } else if (name.endsWith("2")){
            return "2";
        } else if (name.endsWith("3")){
            return "3";
        } else if (name.endsWith("4")){
            return "4";
        } else if (name.endsWith("5")){
            return "5";
        } else if (name.endsWith("6")){
            return "6";
        } else if (name.endsWith("7")){
            return "7";
        } else if (name.endsWith("8")){
            return "8";
        } else if (name.endsWith("10")){
            return "10";
        } else if (name.endsWith("J")){
            return "J";
        } else if (name.endsWith("Q")){
            return "Q";
        } else if (name.endsWith("K")){
            return "K";
        } else {
            return null;
        }
    }

    /**
     * Takes a card from the top of the deck.
     *
     * @return the card from the top of the deck
     * @throws IllegalStateException if the deck is empty
     */
    public Card takeCard() {
        if (deckOfCards.isEmpty()) {
            throw new IllegalStateException("No hay más cartas en el mazo.");
        }
        return deckOfCards.pop();
    }

    public void shuffle(){
        Collections.shuffle(deckOfCards);
    }
}
