package Movements;

import Events.EventManager;
import Players.IPlayerManager;
import Players.Player;

import java.util.Map;

public class CollisionDetector implements ICollisionDetector {
    private final IPlayerManager playerManager;
    private final boolean disableHitEvent;
    private final EventManager eventManager;

    public CollisionDetector(IPlayerManager playerManager, boolean disableHitEvent, EventManager eventManager) {
        this.playerManager = playerManager;
        this.disableHitEvent = disableHitEvent;
        this.eventManager = eventManager;
    }

    @Override
    public boolean movementHandler(Players.Player player, int advance, int currentPosition, int candidateIndex, int moves) {
        for (Map.Entry<Players.Player, Integer> entry : playerManager.getPlayerPositions().entrySet()) {
            Players.Player otherPlayer = entry.getKey();
            int otherPosition = entry.getValue();

            if (!otherPlayer.equals(player) && otherPosition == candidateIndex && candidateIndex != playerManager.getPlayerHome(otherPlayer)) {
                System.out.println("Collision Detected! " + player + " hit " + otherPlayer);
                resolveCollision(otherPlayer, player, candidateIndex, advance, currentPosition, moves);
                return true;
            }
        }
        return false;
    }

    private void resolveCollision(Player otherPlayer, Player player, int candidateIndex, int advance, int currentPosition, int moves) {
        playerManager.setPlayerPosition(otherPlayer, playerManager.getPlayerHome(otherPlayer));
        playerManager.setPlayerPosition(player, candidateIndex);
        eventManager.onHit(player, advance, currentPosition, candidateIndex, moves);
    }
}