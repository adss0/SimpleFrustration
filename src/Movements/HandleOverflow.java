package Movements;

import Events.EventManager;
import Players.IPlayerManager;
import Players.Player;

public class HandleOverflow implements IMovementHandler {
    private final IPlayerManager playerManager;
    private final ICollisionDetector collisionDetector;
    private final EventManager eventManager;
    private final boolean disableHitEvent;
    private final int BOARD_SIZE;

    public HandleOverflow(IPlayerManager playerManager,
                          ICollisionDetector collisionDetector,
                          EventManager eventManager,
                          boolean disableHitEvent,
                          int BOARD_SIZE) {
        this.playerManager = playerManager;
        this.collisionDetector = collisionDetector;
        this.eventManager = eventManager;
        this.disableHitEvent = disableHitEvent;
        this.BOARD_SIZE = BOARD_SIZE;
    }
    @Override
    public void movementHandler(Player player, int advance, int originalIndex, int candidateIndex, int moves) {
        int newIndex = (candidateIndex > BOARD_SIZE) ? (candidateIndex % BOARD_SIZE) : candidateIndex;

        if (!disableHitEvent && collisionDetector.movementHandler(player, advance, originalIndex, newIndex, moves)) {
            return;
        }
        playerManager.setOverflowed(player, true);
        playerManager.setPlayerPosition(player, newIndex);
        eventManager.onOverflow(player, advance, originalIndex, newIndex, moves);
    }
}
