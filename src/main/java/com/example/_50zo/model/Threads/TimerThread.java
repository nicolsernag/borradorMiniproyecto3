package com.example._50zo.model.Threads;

import com.example._50zo.controller.GameStage2Controller;
import javafx.application.Platform;

/**
 * TimerThread is responsible for managing a countdown timer in the game.
 * It updates the timer label in the UI every second and triggers an action
 * when the time expires.
 */

public class TimerThread extends Thread{
    private final GameStage2Controller controller;
    private final int timeLimitSeconds = 10; //time limit
    private volatile boolean isRunning = true; // Flag to control the thread execution

    /**
     * Constructs a TimerThread with the specified controller.
     * @param controller
     */
    public TimerThread(GameStage2Controller controller) {
        this.controller = controller;
    }

    /**
     * Stops the timer thread gracefully.
     */
    public void stopTimer() {
        this.isRunning = false;
    }

    /**
     * Runs the timer thread, updating the UI every second and handling time expiration.
     */
    @Override
    public void run() {
        for (int i = timeLimitSeconds; i >= 0 && isRunning; i--) {
            final int secondsLeft = i;

            Platform.runLater(() -> {
                controller.updateTimerLabel(secondsLeft);
            });

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        if (isRunning) {
            Platform.runLater(() -> {
                controller.handleTimeExpired();
            });
        }
    }
}

