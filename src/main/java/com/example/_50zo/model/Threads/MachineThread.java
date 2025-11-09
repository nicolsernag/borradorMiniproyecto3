package com.example._50zo.model.Threads;

import com.example._50zo.model.*;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.List;
import java.util.function.IntConsumer;

/**
 * This class represents a thread that controls the turns of the machine players
 * in the "Cincuentazo" game.
 * <p>
 * Each machine plays its turn automatically, placing a valid card on the table
 * if possible, and drawing a new one from the deck if available.
 */
public class MachineThread extends Thread{
    private final List<Player> machinePlayers;
    private final List<GridPane> machineGrids;
    private final Deck deck;
    private final Game50 game;
    private final Table table;
    private final ImageView tableImageView;
    private final Runnable updateMachineCards; // callback para refrescar las cartas
    private final java.util.function.IntConsumer updateMesaValue; // callback para sumar el valor
    private int counterTable;

    /**
     * Creates a new thread to control machine players’ turns.
     *
     * @param table              The table where cards are placed.
     * @param machinePlayers     The list of machine players.
     * @param machineGrids       The GridPanes displaying each machine’s cards.
     * @param deck               The deck used in the game.
     * @param game               The main game logic handler.
     * @param tableImageView     The ImageView showing the card on the table.
     * @param updateMachineCards Runnable action to refresh machine cards on the UI.
     * @param updateMesaValue    Function to update the table’s accumulated value.
     * @param counterTable       Initial total value on the table.
     */
    public MachineThread(Table table,List<Player> machinePlayers, List<GridPane> machineGrids,
                         Deck deck, Game50 game, ImageView tableImageView, Runnable updateMachineCards,
                         IntConsumer updateMesaValue, int counterTable) {
        this.machinePlayers = machinePlayers;
        this.machineGrids = machineGrids;
        this.deck = deck;
        this.game = game;
        this.table = table;
        this.tableImageView = tableImageView;
        this.updateMachineCards = updateMachineCards;
        this.updateMesaValue = updateMesaValue;
        this.counterTable = counterTable;
    }
    /**
     * Runs the automatic turns of all machine players.
     * <p>
     * Each machine tries to play a card whose numeric value doesn’t exceed
     * a total of 50 on the table. If no card can be played, the machine is
     * eliminated from the game.
     * <p>
     * Between each turn, the thread sleeps for 2 seconds to simulate thinking time.
     */
    @Override
    public void run() {
        try {
            for (Player machine : machinePlayers) {

                // Esperar un momento antes del turno (simula el pensamiento de la máquina)
                Thread.sleep(2000);

                // Buscar una carta jugable
                Card playableCard = null;
                for (Card card : machine.getCardsPlayer()) {
                    int value = card.getNumericValue(counterTable);
                    if (counterTable + value <= 50) {
                        playableCard = card;
                        break;
                    }
                }

                if (playableCard == null) {
                    // Máquina eliminada si no puede jugar
                    System.out.println("Máquina eliminada (no puede jugar sin exceder 50)");
                    continue;
                }

                // Jugar la carta encontrada
                Card finalCard = playableCard;
                counterTable += finalCard.getNumericValue(counterTable);

                Platform.runLater(() -> {
                    // Actualizar la mesa visualmente
                    table.addCardOnTheTable(finalCard);
                    tableImageView.setImage(finalCard.getImage());

                    // Eliminar la carta jugada
                    machine.getCardsPlayer().remove(finalCard);

                    // Si el mazo no está vacío, robar una nueva carta
                    if (!deck.isEmpty()) {
                        machine.addCard(deck.takeCard());
                    }

                    // Actualizar UI (cartas y valor de mesa)
                    updateMachineCards.run();
                    updateMesaValue.accept(counterTable);
                });
            }
        } catch (InterruptedException e) {
            System.out.println("Machine thread interrupted");
            Thread.currentThread().interrupt();
        }
    }
}

