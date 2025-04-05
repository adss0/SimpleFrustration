package Movements;

import Players.Player;
import Events.EventManager;

public class BounceDetector implements IMovementHandler {

        private final IMovementHandler collisionDetector;
        private final EventManager eventManager;

        public BounceDetector (IMovementHandler collisionDetector, EventManager eventManager) {
            this.collisionDetector = collisionDetector;
            this.eventManager = eventManager;
        }

        @Override
    public void movementHandler(Players.Player player, int advance, int currentPosition, int candidateIndex, int moves) {

        int END = player.getEND();
        int overflowAmount = candidateIndex - END;
        int newIndex = END - overflowAmount;
        newIndex = Math.max(newIndex, 0);

        collisionDetector.movementHandler(player, advance, currentPosition, newIndex, moves);
            if (player.isWasCollision()) {
                return;
            }

       resolveBounce(player, advance, currentPosition, newIndex, moves);
    }

    private void resolveBounce(Player player, int advance, int currentPosition, int newIndex, int moves){
        player.setPlayerPosition(newIndex);
        eventManager.onBounce(player, advance, currentPosition, newIndex, moves);
    }
}
