package com.example._50zo.model;

import java.util.ArrayList;

public class Player {
    private ArrayList<Card> cardsPlayer;

    /**
     * Constructs a new Player object with an empty hand of cards.
     */
    public Player(){
        this.cardsPlayer = new ArrayList<Card>();
    };

    /**
     * Adds a card to the player's hand.
     *
     * @param card The card to be added to the player's hand.
     */
    public void addCard(Card card){
        cardsPlayer.add(card);
    }

    /**
     * Retrieves all cards currently held by the player.
     *
     * @return An ArrayList containing all cards in the player's hand.
     */
    public ArrayList<Card> getCardsPlayer() {
        return cardsPlayer;
    }

    /**
     * Removes a card from the player's hand based on its index.
     *
     * @param index The index of the card to remove.
     */
    public void removeCard(int index) {
        cardsPlayer.remove(index);
    }

    /**
     * Retrieves a card from the player's hand based on its index.
     *
     * @param index The index of the card to retrieve.
     * @return The card at the specified index in the player's hand.
     */
    public Card getCard(int index){
        return cardsPlayer.get(index);
    }
}
