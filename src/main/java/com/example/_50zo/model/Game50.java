package com.example._50zo.model;

import java.util.ArrayList;
import java.util.List;

public class Game50 {
    private Player humanPlayer;
    private Player machinePlayer;
    private Deck deck;
    private Table table;

    /**
     * Constructs a new GameUno instance.
     *
     * @param humanPlayer   The human player participating in the game.
     * @param deck          The deck of cards used in the game.
     * @param table         The table where cards are placed during the game.
     */
    public Game50(Player humanPlayer, Deck deck, Table table) {
        this.humanPlayer = humanPlayer;
        //this.machinePlayer = machinePlayer;
        this.deck = deck;
        this.table = table;
    }

    public void startGame(int nMachine) {
        List<Player> players = new ArrayList<>();
        players.add(humanPlayer);

        for (int i = 0; i < nMachine; i++) {
            players.add(machinePlayer);
        }

        for (int card = 0; card < 5; card++) {
            for (Player p : players) {
                p.addCard(deck.takeCard());
            }
        }
        //firstCard();
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
