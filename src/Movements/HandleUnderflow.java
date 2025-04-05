package Movements;

import Events.EventManager;


public class HandleUnderflow implements IMovementHandler {
    private final IMovementHandler collisionDetector;
    private final EventManager eventManager;

    public HandleUnderflow(IMovementHandler collisionDetector,EventManager eventManager) {
        this.collisionDetector = collisionDetector;
        this.eventManager = eventManager;
    }

    @Override
    public void movementHandler(Players.Player player, int advance, int originalIndex, int newIndex, int moves) {
        collisionDetector.movementHandler(player, advance, originalIndex, newIndex, moves);

        if (player.isWasCollision()) {
            return;
        }

        player.setPlayerPosition(newIndex);
        eventManager.onUnderflow(player, advance, originalIndex, newIndex, moves);
    }
}
