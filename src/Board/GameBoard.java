package Board;

import Events.EventManager;
import Movements.*;
import Players.Player;
import Players.PlayerManager;

public class GameBoard implements IGameBoard {

    private boolean gameWon = false; // Track if someone has won
    public int moves;
    int totalMoves;
    private final int BOARD_SIZE;
    private final boolean disableBounceEvent;

    private final EventManager eventManager;
    private final PlayerManager playerManager;
    private final IMovementHandler bounceDetector;
    private final IMovementHandler handleUnderflow;
    private final IMovementHandler handleOverflow;


    public GameBoard(int BOARD_SIZE, boolean disableHitEvent, boolean disableBounceEvent, PlayerManager playerManager) {
        this.BOARD_SIZE = BOARD_SIZE;
        this.disableBounceEvent = disableBounceEvent;
        this.playerManager = playerManager;
        eventManager = new EventManager();
        IMovementHandler collisionDetector = new CollisionDetector(this.playerManager, disableHitEvent, this.eventManager);
        this.bounceDetector = new BounceDetector(this.playerManager, collisionDetector, this.eventManager);
        this.handleUnderflow = new HandleUnderflow(this.playerManager, collisionDetector, this.eventManager);
        this.handleOverflow = new HandleOverflow(this.playerManager, collisionDetector, this.eventManager, this.BOARD_SIZE);
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
        player.setSpecialCase(false);
        playerManager.setPlayerPosition(player, lastValidPosition);
        this.totalMoves = Math.max( this.totalMoves - 1, 1);
    }

    @Override
    public boolean isGameWon() {
        return gameWon;
    }

    @Override
    public void advancePlayer(Player player, int advance) {
        int currentPosition = playerManager.getPlayerPosition(player);
        int END = playerManager.getPlayerEnd(player);
        boolean overflowed = playerManager.hasOverflowed(player);
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
    }

