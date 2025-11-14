package com.example._50zo.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Table} class.
 * <p>
 * These tests validate the behavior of the table where cards are played,
 * specifically checking the accumulated total, the order of cards, and
 * the special handling of card values based on game rules.
 * </p>
 */
class TableTest {
    private Table table;
    private Card cardA;
    private Card card5;
    private Card cardQ;
    private Card card10;
    private Card card9;

    /**
     * Initializes a fresh {@link Table} instance and sample cards
     * before each test execution.
     */
    @BeforeEach
    void setUp() {
        table = new Table();
        cardA = new Card("A");
        card5 = new Card("5");
        cardQ = new Card("Q");
        card10 = new Card("10");
        card9 = new Card("9");
    }

    /**
     * Tests that {@link Table#getTotalValue()} correctly calculates the sum of
     * all cards using each card's {@link Card#getNumericValue(int)} logic.
     * <p>
     * Scenario:
     *  - "5"   → 5
     *  - "Q"   → -10
     *  - "10"  → 10
     * Total = 5 - 10 + 10 = 5
     * </p>
     */
    @Test
    void testGetTotalValueSimple() {
        // 5 + Q(-10) + 10 = 5 -10 +10 = 5
        table.addCardOnTheTable(card5);
        table.addCardOnTheTable(cardQ);
        table.addCardOnTheTable(card10);

        int total = table.getTotalValue();
        assertEquals(5, total);
    }

    /**
     * Tests that the table preserves card order and that
     * {@link Table#getCurrentCardOnTheTable()} always returns the last card added.
     */
    @Test
    void testGetCurrentCardOnTheTableReturnsLastCard() {
        table.addCardOnTheTable(card5);
        table.addCardOnTheTable(cardQ);
        table.addCardOnTheTable(card10);

        Card current = table.getCurrentCardOnTheTable();
        assertEquals(card10, current);
    }

    /**
     * Tests the correct scoring behavior of the Ace ("A"), which can be worth
     * either 10 or 1 depending on the accumulated total.
     * <p>
     * In this scenario, the Ace counts as 10 because adding 10 does not exceed 50.
     * </p>
     */
    @Test
    void testGetTotalValueWithAceBehavior() {
        table.addCardOnTheTable(cardA);
        table.addCardOnTheTable(card9);
        table.addCardOnTheTable(card10);
        table.addCardOnTheTable(cardQ);

        int total = table.getTotalValue();
        assertEquals(10, total);
    }
}