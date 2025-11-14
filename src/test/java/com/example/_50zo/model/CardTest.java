package com.example._50zo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@code Card} class.
 * <p>
 * These tests verify the correct initialization of cards,
 * their logical values, and the behavior of special cards
 * according to the rules of the game.
 * </p>
 */
class CardTest {

    // Test of the builder without URL
    /**
     * Tests the constructor that initializes a card with only a logical value.
     * Ensures that the URL, Image, and card image fields are {@code null}
     * when not provided.
     */
    @Test
    void testConstructorWithOnlyValue() {
        Card card = new Card("5");
        assertEquals("5", card.getValue());
        assertNull(card.getURL());
        assertNull(card.getImage());
        assertNull(card.getCard());
    }

    // Logical value getter test
    /**
     * Tests the getter method for the logical card value.
     * Ensures the value passed in the constructor is returned correctly.
     */
    @Test
    void testGetValue() {
        Card card = new Card("A");
        assertEquals("A", card.getValue());
    }

    // As behavior test (A = 10 o 1)
    /**
     * Tests the behavior of the Ace card ("A"), which can be worth 10 or 1.
     * <ul>
     *   <li>If the current total + 10 is ≤ 50, the Ace counts as 10.</li>
     *   <li>If the current total + 10 exceeds 50, the Ace counts as 1.</li>
     * </ul>
     */
    @Test
    void testGetNumericValueForAce() {
        Card card = new Card("A");

        // If current total + 10 <= 50, then As is 10
        assertEquals(10, card.getNumericValue(30));

        // If current total + 10 > 50, then As is 1
        assertEquals(1, card.getNumericValue(45));
    }

    /**
     * Tests the numeric value returned for cards from "2" to "8".
     * These cards should return their corresponding integer values.
     */
    @Test
    void testGetNumericValueNumbers2to8() {
        for (int i = 2; i <= 8; i++) {
            Card card = new Card(String.valueOf(i));
            assertEquals(i, card.getNumericValue(0));
        }
    }

    /**
     * Tests the numeric values of special cards:
     * <ul>
     *   <li>"9" is worth 0</li>
     *   <li>"10" is worth 10</li>
     *   <li>"J", "Q", "K" are worth -10</li>
     * </ul>
     */
    @Test
    void testGetNumericValueForSpecialCards() {
        assertEquals(0, new Card("9").getNumericValue(0));   // 9 = 0
        assertEquals(10, new Card("10").getNumericValue(0)); // 10 = 10
        assertEquals(-10, new Card("J").getNumericValue(0)); // J = -10
        assertEquals(-10, new Card("Q").getNumericValue(0)); // Q = -10
        assertEquals(-10, new Card("K").getNumericValue(0)); // K = -10
    }
}