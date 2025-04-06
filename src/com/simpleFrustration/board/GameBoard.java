package com.simpleFrustration.board;

import com.simpleFrustration.events.EventManager;
import Game.Movements.*;
import com.simpleFrustration.movements.*;
import com.simpleFrustration.players.Player;
import com.simpleFrustration.players.PlayerManager;

public class GameBoard implements IGameBoard {

    private boolean gameWon = false; // Track if someone has won
    public static int moves;
    public static int totalMoves;
    private final int BOARD_SIZE;
    private final boolean disableBounceEvent;

    private final EventManager eventManager;
    private final IMovementHandler bounceDetector;
    private final IMovementHandler handleUnderflow;
    private final IMovementHandler handleOverflow;


    public GameBoard(int BOARD_SIZE, boolean disableHitEvent, boolean disableBounceEvent, PlayerManager playerManager) {
        this.BOARD_SIZE = BOARD_SIZE;
        this.disableBounceEvent = disableBounceEvent;
        eventManager = new EventManager();
        IMovementHandler collisionDetector = new HandleCollision(playerManager, disableHitEvent, this.eventManager);
        this.bounceDetector = new HandleOvershoot(collisionDetector, this.eventManager);
        this.handleUnderflow = new HandleUnderflow(collisionDetector, this.eventManager);
        this.handleOverflow = new HandleOverflow( collisionDetector, this.eventManager, this.BOARD_SIZE);
    }

    @Override
    public boolean isGameWon() {
        return gameWon;
    }

    @Override
    public void advancePlayer(Player player, int advance) {
        int currentPosition = player.getPlayerPosition();
        int END = player.getEND();
        boolean overflowed = player.isOverflowed();
        int candidateIndex = currentPosition + advance;
        totalMoves++;

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
    @Override
    public void undoPlayerPosition(Player player, int advance) {
        // Get the last valid position
        int lastValidPosition = player.getLastValidPosition();
        // Check if the reverse position is less than 1 (i.e., needs to wrap around)
        if( player.getHOME()!=1 && (lastValidPosition + advance) > BOARD_SIZE  ) {
            player.setOverflowed(false);
            player.setPlayerPosition(lastValidPosition);
        }
        player.setSpecialCase(false);
        player.setPlayerPosition(lastValidPosition);
        totalMoves = Math.max( totalMoves - 1, 1);
        System.out.println(player + "'s move has been undone.");
    }
    }

