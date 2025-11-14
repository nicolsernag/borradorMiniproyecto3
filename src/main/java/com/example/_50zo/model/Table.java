package com.example._50zo.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the table in the "Cincuentazo" (Game50) card game.
 * <p>
 * The table keeps track of all cards that have been played during the game.
 * It provides methods to add, remove, and retrieve cards, as well as to
 * calculate the total accumulated value of all cards currently on the table.
 */
public class Table {
    private ArrayList<Card> cardsTable;

    /**
     * Constructs a new Table object with no cards on it.
     */
    public Table(){
        this.cardsTable = new ArrayList<Card>();
    }

    /**
     * Adds a card to the table.
     *
     * @param card The card to be added to the table.
     */
    public void addCardOnTheTable(Card card){
        this.cardsTable.add(card);
    }

    /**
     * Retrieves the current card on the table.
     *
     * @return The card currently on the table.
     * @throws IndexOutOfBoundsException if there are no cards on the table.
     */
    public Card getCurrentCardOnTheTable() throws IndexOutOfBoundsException {
        if (cardsTable.isEmpty()) {
            throw new IndexOutOfBoundsException("No hay cartas en la mesa ");
        }
        return this.cardsTable.get(this.cardsTable.size()-1);
    }

    /**
     * Calculates the total sum of all cards currently on the table.
     * Uses each card's getNumericValue() logic (including As).
     *
     * @return total accumulated value of the table
     */
    public int getTotalValue(){
        int total = 0;
        for (Card card : cardsTable){
            total += card.getNumericValue(total);
        }
        return total;
    }

    /**
     * Removes all cards from the table except the last one.
     * Used when refilling the deck.
     *
     * @return a list of cards removed from the table
     */
    public List<Card> removeAllExceptLast(){
        List<Card> removed = new ArrayList<>();
        if (cardsTable.size() <= 1) return removed;

        for (int i = 0; i < cardsTable.size() -1; i++){
            removed.add(cardsTable.get(i));
        }

        Card last = cardsTable.get(cardsTable.size()-1);
        cardsTable.clear();
        cardsTable.add(last);
        return removed;
    }

    /**
     * Returns a copy of all cards currently on the table.
     */
    public List<Card> getAllCards() {
        return new ArrayList<>(cardsTable);
    }

    /**
     * Clears all cards from the table.
     */
    public void clear() {
        cardsTable.clear();
    }

    /**
     * Returns the number of cards currently on the table.
     */
    public int size() {
        return cardsTable.size();
    }
}
