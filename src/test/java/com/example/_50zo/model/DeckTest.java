package com.example._50zo.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;
class DeckTest {

    Card card = new Card("A");

    private Deck deck;

    @BeforeEach
    void setUp() {
        deck = new Deck();
    }

    // El mazo se inicializa correctamente
    @Test
    void testDeckIsNotEmptyOnCreation() {
        assertFalse(deck.isEmpty(), "El mazo no debería estar vacío al crearse");
    }

    // Contiene 52 cartas (sin incluir CARD_FACE_DOWN)
    @Test
    void testDeckHasExpectedCardCount() {
        assertEquals(52, getDeckSize(deck), "El mazo debería tener 52 cartas");
    }

    private int getDeckSize(Deck deck) {
        try {
            Field f = Deck.class.getDeclaredField("deckOfCards");
            f.setAccessible(true);
            Stack<?> stack = (Stack<?>) f.get(deck);
            return stack.size();
        } catch (Exception e) {
            fail("No se pudo acceder al campo deckOfCards: " + e.getMessage());
            return -1;
        }
    }

}