package com.example._50zo.model;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Represents the deck of cards used in the Game50.
 * <p>
 * This class manages a stack of {@link Card} objects that can be shuffled,
 * drawn, or refilled from the table. It also supports adding cards back
 * to the bottom of the deck, maintaining the game’s flow when the deck runs out.
 */
public class Deck {
    private Stack<Card> deckOfCards;
    private Runnable onDeckRefilled;

    /**
     * Constructs a new deck of cards and initializes it.
     */
    public Deck() {
        deckOfCards = new Stack<>();
        initializeDeck();
    }

    public void setOnDeckRefilled(Runnable onDeckRefilled) {
        this.onDeckRefilled = onDeckRefilled;
    }

    /**
     * Initializes the deck with cards based on the values.
     */
    private void initializeDeck() {
        for (CardEnum cardEnum : CardEnum.values()) {
            if(cardEnum != CardEnum.CARD_FACE_DOWN){
                deckOfCards.push(new Card(cardEnum.getFilePath(), getCardValue(cardEnum.name())));
            }
        }
        Collections.shuffle(deckOfCards);
    }


    /**
     * Returns the corresponding display value of a card based on its name.
     * For example, cards ending in "1" are labeled "A", and those ending in "J", "Q", or "K"
     * correspond to face cards.
     *
     * @param name the name of the card from {@link CardEnum}
     * @return the value symbol (e.g., "A", "2", "10", "K") or {@code null} if not found
     */
    private String getCardValue(String name) {
        if (name.endsWith("9")){
            return "9";
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

    /**
     * Shuffles the deck of cards randomly.
     * <p>
     * This method uses {@link Collections#shuffle(List)} to reorder
     * the elements in {@code deckOfCards} in a random sequence,
     * ensuring a fair and unpredictable distribution of cards.
     */
    public void shuffle(){
        Collections.shuffle(deckOfCards);
    }

    /**
     * Checks if the deck is empty.
     *
     * @return true if the deck is empty, false otherwise
     */
    public boolean isEmpty() {
        return deckOfCards.isEmpty();
    }

    /**
     * Refills the deck from the table (except the last card).
     *  @param cardsFromTable the list of cards retrieved from the table to refill the deck
     */
    public void refillFromTable(List<Card> cardsFromTable) {
        if(cardsFromTable == null || cardsFromTable.isEmpty()) return;
        Collections.shuffle(cardsFromTable);
        for ( Card c : cardsFromTable ) {
            deckOfCards.push(c);
        }
        if (onDeckRefilled != null) {
            onDeckRefilled.run();
        }
    }

    /**
     * Adds a list of cards to the bottom of the deck, preserving their order.
     * The last card in the list becomes the bottommost card in the deck.
     *
     * @param cards the list of cards to be added to the bottom of the deck
     */
    public void addAllToBottom(List<Card> cards){
        if(cards != null && !cards.isEmpty()){
            for ( int i = cards.size() - 1; i >= 0; i-- ) {
                deckOfCards.insertElementAt(cards.get(i), 0);
            }
        }
    }
}
