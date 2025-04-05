package Movements;

import Events.EventManager;

import Players.PlayerManager;

public class HandleUnderflow implements IMovementHandler {
    private final PlayerManager playerManager;
    private final IMovementHandler collisionDetector;
    private final EventManager eventManager;

    public HandleUnderflow(PlayerManager playerManager,
                           IMovementHandler collisionDetector,
                                        EventManager eventManager) {
        this.playerManager = playerManager;
        this.collisionDetector = collisionDetector;
        this.eventManager = eventManager;
    }

    @Override
    public void movementHandler(Players.Player player, int advance, int originalIndex, int newIndex, int moves) {
//
//        if (!disableHitEvent && collisionDetector.movementHandler(player, advance, originalIndex, newIndex, moves)) {
//            return;
//        }
        collisionDetector.movementHandler(player, advance, originalIndex, newIndex, moves);

        if (player.isWasCollision()) {
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
