package com.example._50zo.model.Threads;

import com.example._50zo.model.Card;
import com.example._50zo.model.Game50;
import com.example._50zo.model.Player;

/**
 * Represents a concurrent thread controlling a single machine player's turn
 * in the "Cincuentazo" game.
 * <p>
 * Each machine waits a random delay (between 2 and 4 seconds) to simulate
 * thinking time before attempting to play a valid card.
 * <p>
 * If the machine cannot play any card without exceeding 50, it is eliminated.
 * The thread safely updates the user interface using a provided {@code Runnable}
 * executed through {@link javafx.application.Platform#runLater(Runnable)}.
 */
public class MachineThread extends Thread{
    /** The machine player controlled by this thread. */
    private final Player machinePlayer;
    private final int playerIndex;
    private final Runnable eliminateMachinePlayer;

    /** The main game logic controller. */
    private final Game50 game;

    /** Runnable action executed to update the JavaFX UI after the machine plays. */
    private final Runnable updateUI;

    /**
     * Constructs a new {@code MachineThread}.
     *
     * @param machinePlayer the machine player that this thread controls
     * @param game          the main {@link Game50} instance handling logic
     * @param updateUI      a {@link Runnable} executed on the JavaFX Application Thread
     *                      to safely update the interface after a play
     */
    public MachineThread(Player machinePlayer, Game50 game, Runnable updateUI, int index, Runnable eliminateMachinePlayer) {
        this.machinePlayer = machinePlayer;
        this.game = game;
        this.updateUI = updateUI;
        this.playerIndex = index;
        this.eliminateMachinePlayer = eliminateMachinePlayer;
    }

    /**
     * Executes the machine player's turn:
     * <ol>
     *     <li>Waits 2–4 seconds to simulate thinking.</li>
     *     <li>Tries to play the first valid card that does not exceed 50.</li>
     *     <li>If no valid card is found, eliminates the player.</li>
     *     <li>Calls {@code updateUI} to refresh the JavaFX user interface.</li>
     * </ol>
     */
    @Override
    public void run() {
        try {
            // Simulate "thinking" time between 2 and 4 seconds
            int delay = 2000 + (int) (Math.random() * 2000);
            Thread.sleep(delay);

            synchronized (game){
                if (game.hasPlayableCard(machinePlayer)) {
                    for (Card card : machinePlayer.getCardsPlayer()) {
                        if (game.canPlayCard(card)) {
                            game.playCard(machinePlayer, card);
                            break;
                        }
                    }
                } else {
                    // No valid cards: eliminate player
                    game.eliminatePlayer(machinePlayer);
                    if (eliminateMachinePlayer != null) {
                        eliminateMachinePlayer.run();
                    }
                }
            }

            // Update the UI on the JavaFX Application Thread
            if (updateUI != null) {
                updateUI.run();
            }

        } catch (InterruptedException e) {
            System.err.println("Hilo maquina interrumpido: " + e.getMessage());
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            System.err.println("Error durante la ejecucion del hilo maquina: " + e.getMessage());
        }
    }
}

