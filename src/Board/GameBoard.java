package Board;

import Dice.DiceShaker;
import Events.EventManager;
import Movements.*;
import Players.IPlayerManager;
import Players.Player;
import Players.PlayerManager;

import java.util.List;

public class GameBoard implements IGameBoard {

    private boolean gameWon = false; // Track if someone has won
    public int moves;
    int totalMoves;
    private int numberOfTailPositions;
    private int BOARD_SIZE;
    private boolean disableHitEvent, disableBounceEvent;

    private final DiceShaker shaker;
    private final EventManager eventManager;
    private final IPlayerManager playerManager;
    private final ICollisionDetector collisionDetector;
    private final IMovementHandler bounceDetector;
    private final IMovementHandler handleUnderflow;
    private final IMovementHandler handleOverflow;


    public GameBoard(int BOARD_SIZE, List<Player> playerList, DiceShaker shaker, boolean disableHitEvent, boolean disableBounceEvent, PlayerManager playerManager) {
        this.numberOfTailPositions = (BOARD_SIZE <= 18) ? 3 : 6;
        this.BOARD_SIZE = BOARD_SIZE;
        this.shaker = shaker;
        this.disableHitEvent = disableHitEvent;
        this.disableBounceEvent = disableBounceEvent;
        this.playerManager = playerManager;
        eventManager = new EventManager();
        this.collisionDetector = new CollisionDetector(this.playerManager, this.disableHitEvent, this.eventManager );
        this.bounceDetector = new BounceDetector(this.playerManager, this.collisionDetector, this.eventManager, this.disableHitEvent);
        this.handleUnderflow = new HandleUnderflow(this.playerManager, this.collisionDetector, this.eventManager, this.disableHitEvent);
        this.handleOverflow = new HandleOverflow(this.playerManager, this.collisionDetector, this.eventManager,this.disableHitEvent, this.BOARD_SIZE);
    }


    @Override
    public void undoPlayerPosition(Player player, int advance) {
        // Get the last valid position
        int lastValidPosition = playerManager.getLastValidPosition(player);

        // Check if the reverse position is less than 1 (i.e., needs to wrap around)
        if (playerManager.getPlayerHome(player) !=1 && (lastValidPosition + advance) > BOARD_SIZE  ) {
            playerManager.setOverflowed(player, false);
            playerManager.setPlayerPosition(player, lastValidPosition);
        }

        playerManager.setPlayerPosition(player, lastValidPosition);
        this.totalMoves = Math.max( this.totalMoves - 1, 1);
    }

    @Override
    public boolean isGameWon() {
        return gameWon;
    }


    void advancePlayer(Player player, int advance) {
        int currentPosition = playerManager.getPlayerPosition(player);
        int HOME = playerManager.getPlayerHome(player);
        int END = playerManager.getPlayerEnd(player);
        boolean overflowed = playerManager.hasOverflowed(player) || HOME == 1;
        int candidateIndex = currentPosition + advance;
        this.totalMoves++;

        try {
            if (candidateIndex < END) {
                handleUnderflow.movementHandler(player, advance, currentPosition, candidateIndex, moves);
            } // for positions that are greater than end
            else if (!overflowed && candidateIndex < BOARD_SIZE) {
                handleUnderflow.movementHandler(player, advance, currentPosition, candidateIndex, moves);
            }
            // Handle Overflow
            else if (!overflowed && candidateIndex > BOARD_SIZE) {
                handleOverflow.movementHandler(player, advance, currentPosition, candidateIndex, moves);
            }
            // If player lands exactly on END after already overflowing
            else if (candidateIndex == END && overflowed) {
                eventManager.onHomeEvent(player, advance, currentPosition, END, moves, totalMoves);
                gameWon = true;
            }
            // If the player moves past END after overflowing
            else if (overflowed && candidateIndex > END) {
                if (!disableBounceEvent) {
                    bounceDetector.movementHandler(player, advance, currentPosition, candidateIndex, moves);
                } else {
                    eventManager.onHomeEvent(player, advance, currentPosition, END, moves, totalMoves);
                    gameWon = true;
                }
            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
//
//    private void underflow(Players.Player player, int advance, int originalIndex, int newIndex, int moves) {
//
//        if (!disableHitEvent && collisionDetector.detectCollision(player, advance, originalIndex, newIndex, moves)) {
//            return;
//        }
//        playerManager.setPlayerPosition(player, newIndex);
//        eventManager.onUnderflow(player, advance, originalIndex, newIndex, moves);
//    }

//    private void overflow(Players.Player player, int advance, int originalIndex, int candidateIndex, int moves) {
//
//        int newIndex = (candidateIndex > BOARD_SIZE) ? (candidateIndex % BOARD_SIZE) : candidateIndex;
//
//        if (!disableHitEvent && collisionDetector.detectCollision(player, advance, originalIndex, newIndex, moves)) {
//            return;
//        }
//
//        playerManager.setOverflowed(player, true);
//        playerManager.setPlayerPosition(player, newIndex);
//        eventManager.onOverflow(player, advance, originalIndex, newIndex, moves);
//
//    }

//    private void detectBounce(Players.Player player, int advance, int currentPosition, int candidateIndex, int moves) {
//
//        int END = playerManager.getPlayerEnd(player);
//        int overflowAmount = candidateIndex - END;
//        int newIndex = END - overflowAmount;
//        newIndex = Math.max(newIndex, 0);
//
//        if (!disableHitEvent && collisionDetector.detectCollision(player, advance, currentPosition, newIndex, moves)) {
//            return;
//        }
//        playerManager.setPlayerPosition(player, newIndex);
//        eventManager.onBounce(player, advance, currentPosition, newIndex, moves);
//
//    }
//
//    private boolean detectCollision(Players.Player player, int advance, int currentPosition, int candidateIndex, int moves) {
//        for (Map.Entry<Players.Player, Integer> entry : playerManager.getPlayerPositions().entrySet()) {
//            Players.Player otherPlayer = entry.getKey();
//            int otherPosition = entry.getValue();
//
//            if (!otherPlayer.equals(player) && otherPosition == candidateIndex && candidateIndex != playerManager.getPlayerHome(otherPlayer)) {
//                System.out.println("Collision Detected! " + player + " hit " + otherPlayer);
//                playerManager.setPlayerPosition(otherPlayer, playerManager.getPlayerHome(otherPlayer));
//                playerManager.setPlayerPosition(player, candidateIndex);
//                eventManager.onHit(player, advance, currentPosition, candidateIndex, moves);
//                return true;
//            }
//        }
//        return false;
//    }

    }

