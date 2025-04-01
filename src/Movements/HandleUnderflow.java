package Movements;

import Events.EventManager;
import Players.IPlayerManager;

public class HandleUnderflow implements IMovementHandler {
    private final IPlayerManager playerManager;
    private final ICollisionDetector collisionDetector;
    private final EventManager eventManager;
    private final boolean disableHitEvent;

    public HandleUnderflow(IPlayerManager playerManager,
                                        ICollisionDetector collisionDetector,
                                        EventManager eventManager,
                                        boolean disableHitEvent) {
        this.playerManager = playerManager;
        this.collisionDetector = collisionDetector;
        this.eventManager = eventManager;
        this.disableHitEvent = disableHitEvent;
    }

    @Override
    public void movementHandler(Players.Player player, int advance, int originalIndex, int newIndex, int moves) {

        if (!disableHitEvent && collisionDetector.movementHandler(player, advance, originalIndex, newIndex, moves)) {
            return;
        }
        playerManager.setPlayerPosition(player, newIndex);
        eventManager.onUnderflow(player, advance, originalIndex, newIndex, moves);
    }

//
//    private void executeUnderflow(Player player, int advance, int originalIndex, int newIndex, int moves) {
//        playerManager.setPlayerPosition(player, newIndex);
//        eventManager.onUnderflow(player, advance, originalIndex, newIndex, moves);
//    }
}
