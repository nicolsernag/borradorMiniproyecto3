package com.example._50zo.controller;

import com.example._50zo.model.*;
import com.example._50zo.model.Threads.MachineThread;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

/**
 * Main controller for the "Cincuentazo" game stage.
 * <p>
 * This class connects the user interface (JavaFX) with the game logic (Model classes),
 * handling all events, visual updates, and turn coordination between the human
 * player and machine opponents using concurrent threads.
 */
public class GameStage2Controller {

    private int numMachinePlayers; // valor que viene del menú

    @FXML private GridPane gridMachine1;
    @FXML private GridPane gridMachine2;
    @FXML private GridPane gridMachine3;
    @FXML private GridPane gridPaneHumanPlayer;

    @FXML private ImageView Table;
    @FXML private ImageView Deck;

    @FXML private Label labelTable;
    @FXML private Label msgHumanPlayer;


    private Deck deck;
    private Table table;
    private Player humanPlayer;
    private List<Player> machinePlayers = new ArrayList<>();
    private List<GridPane> machineGrids = new ArrayList<>();

    private Game50 game;
    private boolean humanEliminated = false;

    private final Image backCardImage = new Image(
            getClass().getResourceAsStream(CardEnum.CARD_FACE_DOWN.getFilePath())
    );

    /**
     * Sets the number of machine players selected in the previous stage.
     *
     * @param num number of machine opponents (1–3)
     */
    public void setNumMachinePlayers(int num) {

        this.numMachinePlayers = num;
    }

    /**
     * Initializes the core game objects such as the deck, table,
     * and player instances.
     */
    public void initVariables(){
        humanPlayer = new Player();
        deck = new Deck();
        table = new Table();
        game = new Game50(humanPlayer, deck, table);
        machineGrids = List.of(gridMachine1, gridMachine2, gridMachine3);
    }

    /**
     * Initializes the full game session.
     * <ul>
     *     <li>Creates machine players</li>
     *     <li>Shuffles the deck</li>
     *     <li>Deals the cards to all players</li>
     *     <li>Places the first card on the table</li>
     *     <li>Renders all visual elements</li>
     * </ul>
     */
    public void initGame() {
        initVariables();
        createMachinePlayers();
        deck.shuffle();

        dealCards();
        dealCardTable();

        showHumanCards();
        showMachineCards();
        msgHumanPlayer.setText("Tu turno. Juega una carta.");

    }

    /**
     * Creates the specified number of machine players.
     */
    private void createMachinePlayers(){
        for (int i = 0; i < numMachinePlayers; i++) {
            machinePlayers.add(new Player());
        }
    }

    /**
     * Creates an ImageView for a card back image (used for hidden machine cards).
     *
     * @return ImageView of the back of a card
     */
    private ImageView createBackCardImageView() {
        ImageView img = new ImageView(backCardImage);
        img.setFitWidth(82);
        img.setFitHeight(128);
        return img;
    }

    /**
     * Deals 4 cards to the human player and each machine player.
     */
    private void dealCards(){
        for (int i = 0; i < 4; i++) {
            humanPlayer.addCard(deck.takeCard());
        }

        for(Player machine : machinePlayers){
            for (int i = 0; i < 4; i++) {
                machine.addCard(deck.takeCard());
            }
        }
    }

    /**
     * Places the first card from the deck on the table to start the game.
     */
    private void dealCardTable(){
        Card card = deck.takeCard();
        table.addCardOnTheTable(card);
        Table.setImage(card.getImage());
        labelTable.setText("Total mesa: " + table.getTotalValue());
    }

    /**
     * Renders the machine players' cards as face-down cards in their respective grids.
     */
    private void showMachineCards(){
        for (int i = 0; i < 3; i++) {
            GridPane grid = machineGrids.get(i);

            if(i < machinePlayers.size()){
                grid.setVisible(true);
                grid.getChildren().clear();
                Player cpu = machinePlayers.get(i);
                int col = 0;
                for (Card ignored : cpu.getCardsPlayer()) {
                    grid.add(createBackCardImageView(), col++, 0);
                }
            } else {
                grid.setVisible(false);
            }
        }
    }

    /**
     * Displays the human player's cards and assigns click events to each.
     */
    private void showHumanCards (){
        gridPaneHumanPlayer.getChildren().clear();
        int col = 0;
        for (Card card : humanPlayer.getCardsPlayer()){
            ImageView img = card.getCard();
            setupCardClick(img, card, col);
            gridPaneHumanPlayer.add(img, col++, 0);
        }
    }

    /**
     * Assigns the mouse click event to a specific human player card.
     * Handles playing the card, validating the move, and updating the UI.
     *
     * @param img ImageView of the clicked card
     * @param card Card object corresponding to the ImageView
     * @param columnIndex position of the card in the player's grid
     */
    private void setupCardClick(ImageView img, Card card, int columnIndex) {
        img.setOnMouseClicked(event -> {
            if (humanEliminated) {
                msgHumanPlayer.setText("Has sido eliminado");
                return;
            }
            int possibleTotal = table.getTotalValue() + card.getNumericValue(table.getTotalValue());
            if (possibleTotal > 50) {
                msgHumanPlayer.setText("Esa carta supera 50.");
                if (!humanPlayer.hasPlayableCard(table.getTotalValue())) {
                    humanEliminated = true;
                    msgHumanPlayer.setText("No puedes jugar ninguna carta. Eliminado.");
                    checkGameOver();
                }
                return;
            }

            game.playCard(humanPlayer, card);
            Table.setImage(card.getImage());
            updateMesaLabel();
            showHumanCards();
            msgHumanPlayer.setText("Jugaste " + card.getValue() + ". Total: " + table.getTotalValue());

            // Iniciar turnos de máquinas
            startMachineTurns();

        });
    }

    /**
     * Starts the turns for all active machine players using concurrent threads.
     * Each machine plays automatically after a random delay (2–4 seconds).
     */
    private void startMachineTurns() {
        for (int i = 0; i < machinePlayers.size(); i++) {
            Player machine = machinePlayers.get(i);
            int finalI = i;

            MachineThread machineThread = new MachineThread(
                    machine,
                    game,
                    () -> Platform.runLater(() -> {
                        updateMesaLabel();
                        showMachineCards();
                        updatePlayedCardImage();
                        msgHumanPlayer.setText("Maquina " + (finalI + 1) +
                                " Jugó. Total: " + table.getTotalValue() + "\n" + "Tu turno, juega una carta.");
                        checkGameOver();
                    })
            );

            machineThread.start();
        }
    }
//NEW CHANGES
    private void updatePlayedCardImage() {
        Card lastCard = game.getTable().getCurrentCardOnTheTable();

        if (lastCard != null) {
            String imagePath = lastCard.getURL();

            java.net.URL imageUrl = getClass().getResource(imagePath);

            if (imageUrl != null) {
                try {
                    String urlString = imageUrl.toExternalForm();
                    Image cardImage = new Image(urlString);
                    Table.setImage(cardImage);
                } catch (Exception e) {
                    System.err.println("ERROR: La imagen no se encontró. Revisa la ruta: " + imagePath + e.getMessage());
                }
            }
        }
    }

    /**
     * Updates the table total label with the current value of all cards on the table.
     */
    private void updateMesaLabel() {
        labelTable.setText("Total mesa: " + table.getTotalValue());
    }

    /**
     * Checks whether the game is over (only one player remains).
     * Displays a message when the game ends.
     */
    private void checkGameOver() {
        int activePlayers = !humanEliminated ? 1 : 0;
        for (Player p : machinePlayers) {
            if (!p.getCardsPlayer().isEmpty()) activePlayers++;
        }

        if (activePlayers <= 1) {
            msgHumanPlayer.setText("Fin del juego!");
        }
    }
}
