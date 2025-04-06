package com.simpleFrustration.movements;

import com.simpleFrustration.events.EventManager;
import com.simpleFrustration.players.Player;


public class HandleUnderflow implements IMovementHandler {
    private final IMovementHandler collisionDetector;
    private final EventManager eventManager;

    public HandleUnderflow(IMovementHandler collisionDetector,EventManager eventManager) {
        this.collisionDetector = collisionDetector;
        this.eventManager = eventManager;
    }

    @Override
    public void movementHandler(Player player, int advance, int originalIndex, int newIndex, int moves) {

        collisionDetector.movementHandler(player, advance, originalIndex, newIndex, moves);

        if (player.isWasCollision()) {
            return;
        }
//        System.out.println(newIndex + " this is the new index for the player");
//        System.out.println(originalIndex + " this is the original index for the player");
        player.setPlayerPosition(newIndex);
        eventManager.onUnderflow(player, advance, originalIndex, newIndex, moves);
    }
}
