package com.simpleFrustration.movements;

import com.simpleFrustration.events.EventManager;
import com.simpleFrustration.players.Player;

public class HandleOverflow implements IMovementHandler {
    private final IMovementHandler collisionDetector;
    private final EventManager eventManager;
    private final int BOARD_SIZE;

    public HandleOverflow(IMovementHandler collisionDetector, EventManager eventManager, int BOARD_SIZE) {
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

        player.setOverflowed(true);
        player.setPlayerPosition(newIndex);

        player.setSpecialCase(originalIndex == player.getHOME() && newIndex < player.getHOME());

        eventManager.onOverflow(player, advance, originalIndex, newIndex, moves);


    }
}
