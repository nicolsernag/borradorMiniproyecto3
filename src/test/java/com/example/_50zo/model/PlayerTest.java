package com.example._50zo.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Player} class.
 * <p>
 * These tests verify that a player correctly manages their hand of cards,
 * including adding cards, checking playable options, and retrieving cards
 * by index.
 * </p>
 */
class PlayerTest {
    private Player player;

    // Create a new player before each trial.
    /**
     * Initializes a fresh {@link Player} instance before each test.
     */
    @BeforeEach
    void setUp() {
        player = new Player();
    }

    // Verify that when adding a card, it is correctly stored in the player's hand.
    /**
     * Tests that adding a card using {@link Player#addCard(Card)}
     * correctly stores the card in the player's hand.
     * <p>
     * After insertion:
     * - The hand size should increase to 1.
     * - The first card should match the inserted instance.
     * </p>
     */
    @Test
    void testAddCard() {
        Card card = new Card("5");
        player.addCard(card);
        assertEquals(1, player.getCardsPlayer().size());
        assertEquals(card, player.getCard(0));
    }

    /**
     * Tests that {@link Player#hasPlayableCard(int)} returns {@code true}
     * when at least one card can be legally played without exceeding 50.
     * <p>
     * Scenario:
     * - Hand contains "10" and "5".
     * - Current total is 40.
     * - Playing "10" results in exactly 50 → playable.
     * </p>
     */
    @Test
    void testHasPlayableCardTrue() {
        player.addCard(new Card("10"));
        player.addCard(new Card("5"));

        boolean result = player.hasPlayableCard(40); // 40 + 10 = 50
        assertTrue(result);
    }

    /**
     * Tests that {@link Player#hasPlayableCard(int)} returns {@code false}
     * when none of the cards in the player's hand can be legally played
     * without exceeding 50.
     * <p>
     * Scenario:
     * - Hand contains two "10" cards.
     * - Current total is 41.
     * - 41 + 10 = 51 → exceeds 50 → no playable cards.
     * </p>
     */
    @Test
    void testHasPlayableCardFalse() {
        // Two "10"s in the hand: each one has a value of 10
        player.addCard(new Card("10"));
        player.addCard(new Card("10"));

        // currentTotal = 41 -> 41 + 10 = 51 (>50) -> no card is playable
        boolean result = player.hasPlayableCard(41);
        assertFalse(result);
    }

}