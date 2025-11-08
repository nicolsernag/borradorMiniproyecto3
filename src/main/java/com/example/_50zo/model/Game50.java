package com.example._50zo.model;

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

    public Card playCard(Player player, Card card) {
        this.table.addCardOnTheTable(card);
        player.getCardsPlayer().remove(card);
        return card;
    }

    public Card drawFromDeck(){
        return deck.takeCard();
    }

    public Deck getDeck() {
        return deck;
    }
}
