package com.example._50zo.model;

import com.example._50zo.model.exceptions.InvalidMoveException;

import java.util.List;

/**
 * Represents the main logic of the "Game50" card game.
 * <p>
 * This class manages the interaction between the player, the deck, and the table.
 * It enforces the game’s main rule: the total sum of played cards on the table
 * must not exceed 50. It also handles card drawing, deck refilling, and player elimination.
 */
public class Game50 {
    private Player humanPlayer;
    private Deck deck;
    private Table table;

    private Runnable onDeckRefilled;

    /**
     * Constructs a new Game50 instance.
     *
     * @param humanPlayer   The human player participating in the game.
     * @param deck          The deck of cards used in the game.
     * @param table         The table where cards are placed during the game.
     */
    public Game50(Player humanPlayer, Deck deck, Table table) {
        this.humanPlayer = humanPlayer;
        this.deck = deck;
        this.table = table;
    }

    /**
     * Sets a callback to run when the deck is refilled from the table.
     */
    public void setOnDeckRefilled(Runnable onDeckRefilled) {
        this.onDeckRefilled = onDeckRefilled;
    }

    /**
     * Checks if a given card can be legally played according to the rules.
     *
     * @param card the card to test
     * @return true if playing the card does not exceed 50, false otherwise
     */
    public boolean canPlayCard(Card card) {
        int currentTotal = table.getTotalValue();
        int newValue = card.getNumericValue(currentTotal);
        return (currentTotal + newValue) <= 50;
    }

    /**
     * Allows a player to play a card during the game.
     * <p>
     * The method checks whether adding the selected card's value to the current table total
     * would exceed 50. If it does, the move is invalid and no changes are made.
     * Otherwise, the card is added to the table, removed from the player's hand,
     * and the player draws a new card from the deck if available.
     * <p>
     * If the deck is empty, it is refilled with cards from the table (except the last one),
     * and the player then draws a new card if possible.
     *
     * @param player the player who is playing the card
     * @param card   the card the player chooses to play
     * @return {@code true} if the card was played successfully; {@code false} if the move was invalid
     */
    public boolean playCard (Player player, Card card) throws InvalidMoveException{
        int currentTotal = table.getTotalValue();
        int cardValue = card.getNumericValue(currentTotal);

        if (currentTotal + cardValue > 50) {
            throw new InvalidMoveException("Jugada invalida: superas 50.");
        }
        table.addCardOnTheTable(card);
        player.getCardsPlayer().remove(card);

        if(!deck.isEmpty()){
            player.addCard(deck.takeCard());
        } else{
            List<Card> cardsFromTable = table.removeAllExceptLast();
            deck.refillFromTable(cardsFromTable);
            if (onDeckRefilled != null) {
                onDeckRefilled.run();
            }

            if(!deck.isEmpty()){
                player.addCard(deck.takeCard());
            }
        }
        return true;
    }

    /**
     * Draws a card directly from the deck.
     * @return the card drawn from the deck
     */
    public Card drawFromDeck(){
        return deck.takeCard();
    }

    /**
     * Returns the total value currently on the table.
     * @return the sum of the values of all cards currently on the table
     */
    public int getTableTotal(){
        return table.getTotalValue();
    }

    /**
     * Returns the deck used in the game.
     * @return the {@code Deck} instance used in the game
     */
    public Deck getDeck() {
        return deck;
    }

    /**
     * Returns the table used in the game.
     *  @return the {@code Table} instance representing the playing area
     */
    public Table getTable() {
        return table;
    }

    /**
     * Returns the human player.
     * @return the {@code Player} instance representing the human player
     */
    public Player getHumanPlayer() {
        return humanPlayer;
    }

    /**
     * Determines if a player has any playable card left.
     * Used to check if a player should be eliminated.
     * @param player the player whose cards are being checked
     * @return {@code true} if the player has at least one playable card; {@code false} otherwise
     */
    public boolean hasPlayableCard(Player player) {
        int currentTotal = table.getTotalValue();
        for (Card c : player.getCardsPlayer()) {
            if (canPlayCard(c)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Removes a player's cards and sends them to the bottom of the deck.
     * @param player the player to be eliminated
     */
    public void eliminatePlayer(Player player) {
        System.out.println("Jugador eliminado, sus cartas regresan al fondo del mazo.");
        deck.addAllToBottom(player.getCardsPlayer());
        player.getCardsPlayer().clear();
    }
}

