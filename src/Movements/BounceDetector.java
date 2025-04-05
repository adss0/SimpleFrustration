package Movements;

import Players.Player;
import Events.EventManager;
import Players.PlayerManager;

public class BounceDetector implements IMovementHandler {

        private final PlayerManager playerManager;
        private final IMovementHandler collisionDetector;
        private final EventManager eventManager;

        public BounceDetector(PlayerManager playerManager, IMovementHandler collisionDetector, EventManager eventManager) {
            this.playerManager = playerManager;
            this.collisionDetector = collisionDetector;
            this.eventManager = eventManager;
        }

        @Override
    public void movementHandler(Players.Player player, int advance, int currentPosition, int candidateIndex, int moves) {

        int END = playerManager.getPlayerEnd(player);
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
        playerManager.setPlayerPosition(player, newIndex);
        eventManager.onBounce(player, advance, currentPosition, newIndex, moves);
    }
}
