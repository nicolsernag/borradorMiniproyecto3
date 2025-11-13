package com.example._50zo.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player in the "Cincuentazo" game.
 * The class also provides methods to check which cards can be legally played
 * according to the current total on the table.
 */
public class Player implements IPlayer{
    private ArrayList<Card> cardsPlayer;

    /**
     * Constructs a new Player object with an empty hand of cards.
     */
    public Player(){

        this.cardsPlayer = new ArrayList<>();
    }

    /**
     * Adds a card to the player's hand.
     *
     * @param card The card to be added to the player's hand.
     */
    @Override
    public void addCard(Card card){

        cardsPlayer.add(card);
    }

    /**
     * Retrieves all cards currently held by the player.
     *
     * @return An ArrayList containing all cards in the player's hand.
     */
    @Override
    public ArrayList<Card> getCardsPlayer() {

        return cardsPlayer;
    }

    /**
     * Removes a card from the player's hand based on its index.
     *
     * @param index The index of the card to remove.
     */
    @Override
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

    /**
     * Inserts a card at a specific position.
     *
     * @param index The index where the card will be inserted.
     * @param card  The card to insert.
     */
    public void addCardAt(int index, Card card) {
        if (index >= 0 && index <= cardsPlayer.size()) {
            cardsPlayer.add(index, card);
        } else {
            cardsPlayer.add(card);
        }
    }

    /**
     * Checks if this player has any card that can be legally played
     * according to the current total on the table.
     *
     * @param currentTotal current accumulated value on the table
     * @return true if the player can play at least one card; {@code false} otherwise
     */
    @Override
    public boolean hasPlayableCard(int currentTotal) {
        for (Card card : cardsPlayer) {
            int value = card.getNumericValue(currentTotal);
            if (currentTotal + value <= 50) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a list of cards that can be legally played according to the current total.
     *
     * @param currentTotal current accumulated value on the table
     * @return list of playable cards
     */
    public List<Card> getPlayableCard(int currentTotal) {
        List<Card> playable = new ArrayList<>();
        for (Card card : cardsPlayer) {
            int value = card.getNumericValue(currentTotal);
            if (currentTotal + value <= 50) {
                playable.add(card);
            }
        }
        return playable;
    }

    /**
     * String representation (for debugging or logs)
     * @return a string containing the values of all cards in the player's hand
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Cartas del jugador: ");
        for (Card c: cardsPlayer) {
            sb.append(c.getValue()).append("");
        }
        return sb.toString().trim();
    }
}
