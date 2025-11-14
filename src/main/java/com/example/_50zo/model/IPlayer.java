package com.example._50zo.model;

import java.util.ArrayList;

/**
 * Interface representing a generic player in the 50zo card game.
 * <p>
 * This interface defines the required operations that any type of player
 * (human or machine) must support, including card management and determining
 * whether the player can make a valid move based on the current table total.
 * <p>
 * Implementing classes must manage their own hand of cards and provide logic
 * to evaluate playable cards according to the game rules.
 */
public interface IPlayer {

    /**
     * Adds a card to the player's hand.
     *
     * @param card the {@link Card} object to be added
     */
    void addCard(Card card);

    /**
     * Returns the list of cards currently held by the player.
     *
     * @return an {@code ArrayList<Card>} containing the player's hand
     */
    ArrayList<Card> getCardsPlayer();

    /**
     * Removes a card from the player's hand at the specified index.
     *
     * @param index the index of the card to remove
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    void removeCard(int index);

    /**
     * Determines whether the player has at least one card that can be legally played,
     * given the current total value on the table.
     * <p>
     * A card is considered playable if adding its numeric value
     * (according to game rules) to the current table total does not exceed 50.
     *
     * @param currentTotal the current accumulated value on the table
     * @return {@code true} if the player has at least one valid move,
     *         {@code false} otherwise
     */
    boolean hasPlayableCard(int currentTotal);
}
