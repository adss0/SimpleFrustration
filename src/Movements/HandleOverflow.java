package Movements;

import Events.EventManager;

import Players.Player;
import Players.PlayerManager;

public class HandleOverflow implements IMovementHandler {
    private final PlayerManager playerManager;
    private final IMovementHandler collisionDetector;
    private final EventManager eventManager;
    private final int BOARD_SIZE;

    public HandleOverflow(PlayerManager playerManager, IMovementHandler collisionDetector, EventManager eventManager, int BOARD_SIZE) {
        this.playerManager = playerManager;
        this.collisionDetector = collisionDetector;
        this.eventManager = eventManager;
        this.BOARD_SIZE = BOARD_SIZE;
    }
    @Override
    public void movementHandler(Player player, int advance, int originalIndex, int candidateIndex, int moves) {
        int newIndex = (candidateIndex > BOARD_SIZE) ? (candidateIndex % BOARD_SIZE) : candidateIndex;

        collisionDetector.movementHandler(player, advance, originalIndex, newIndex, moves);
        if (player.isWasCollision()) {
            return;
        }

        playerManager.setOverflowed(player, true);
        playerManager.setPlayerPosition(player, newIndex);
        if(originalIndex == player.getHOME() && newIndex < player.getHOME()){
            player.setSpecialCase(true);
        } else{player.setSpecialCase(false);}

        eventManager.onOverflow(player, advance, originalIndex, newIndex, moves);


    }
}
