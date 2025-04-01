package Movements;

import Players.IPlayerManager;
import Players.Player;
import Events.EventManager;

public class BounceDetector implements IMovementHandler {

        private final IPlayerManager playerManager;
        private final ICollisionDetector collisionDetector;
        private final EventManager eventManager;
        private final boolean disableHitEvent;

        public BounceDetector(IPlayerManager playerManager,
                                     ICollisionDetector collisionDetector,
                                     EventManager eventManager,
                                     boolean disableHitEvent) {
            this.playerManager = playerManager;
            this.collisionDetector = collisionDetector;
            this.eventManager = eventManager;
            this.disableHitEvent = disableHitEvent;
        }

        @Override
    public void movementHandler(Players.Player player, int advance, int currentPosition, int candidateIndex, int moves) {

        int END = playerManager.getPlayerEnd(player);
        int overflowAmount = candidateIndex - END;
        int newIndex = END - overflowAmount;
        newIndex = Math.max(newIndex, 0);

        if (!disableHitEvent && collisionDetector.movementHandler(player, advance, currentPosition, newIndex, moves)) {
            return;
        }
       resolveBounce(player, advance, currentPosition, newIndex, moves);
    }

    private void resolveBounce(Player player, int advance, int currentPosition, int newIndex, int moves){
        playerManager.setPlayerPosition(player, newIndex);
        eventManager.onBounce(player, advance, currentPosition, newIndex, moves);
    }
}
