package com.example._50zo.model;

import java.util.List;

public class Game50 {
    private Player humanPlayer;
    private Deck deck;
    private Table table;

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

    public boolean playCard (Player player, Card card) {
        int currentTotal = table.getTotalValue();
        int cardValue = card.getNumericValue(currentTotal);

        if (currentTotal + cardValue > 50) {
            System.out.println("Jugada invalida: superas 50.");
            return false;
        }
        table.addCardOnTheTable(card);
        player.getCardsPlayer().remove(card);

        if(!deck.isEmpty()){
            player.addCard(deck.takeCard());
        } else{
            List<Card> cardsFromTable = table.removeAllExceptLast();
            deck.refillFromTable(cardsFromTable);
            if(!deck.isEmpty()){
                player.addCard(deck.takeCard());
            }
        }
        return true;
    }

    /**
     * Draws a card directly from the deck.
     */
    public Card drawFromDeck(){
        return deck.takeCard();
    }

    /**
     * Returns the total value currently on the table.
     */
    public int getTableTotal(){
        return table.getTotalValue();
    }

    /**
     * Returns the deck used in the game.
     */
    public Deck getDeck() {
        return deck;
    }

    /**
     * Returns the table used in the game.
     */
    public Table getTable() {
        return table;
    }

    /**
     * Returns the human player.
     */
    public Player getHumanPlayer() {
        return humanPlayer;
    }

    /**
     * Determines if a player has any playable card left.
     * Used to check if a player should be eliminated.
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
     */
    public void eliminatePlayer(Player player) {
        System.out.println("Jugador eliminado, sus cartas regresan al fondo del mazo.");
        deck.addAllToBottom(player.getCardsPlayer());
        player.getCardsPlayer().clear();
    }
}

