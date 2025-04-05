package Movements;

import Events.EventManager;
import Players.Player;
import Players.PlayerManager;


public class CollisionDetector implements IMovementHandler {
    private final PlayerManager playerManager;
    private final boolean disableHitEvent;
    private final EventManager eventManager;

    public CollisionDetector(PlayerManager playerManager, boolean disableHitEvent, EventManager eventManager) {
        this.playerManager = playerManager;
        this.disableHitEvent = disableHitEvent;
        this.eventManager = eventManager;
    }

    @Override
    public void movementHandler(Players.Player player, int advance, int currentPosition, int candidateIndex, int moves) {
        player.setWasCollision(false);
        if (disableHitEvent) return;

        // Check all other players for collision
        for (Player otherPlayer : playerManager.getPlayerList()) {
            if (!otherPlayer.equals(player)) {
                int otherPosition = otherPlayer.getPlayerPosition();

                // If players land on the same position (not their home), it's a collision
                if (otherPosition == candidateIndex && candidateIndex != otherPlayer.getHOME()) {
                    System.out.println("Collision Detected! " + player + " hit " + otherPlayer);
                    resolveCollision(otherPlayer, player, candidateIndex, advance, currentPosition, moves);
                    return;
                }

//        for (Map.Entry<Players.Player, Integer> entry : playerManager.getPlayerPositions().entrySet()) {
//            Players.Player otherPlayer = entry.getKey();
//            int otherPosition = entry.getValue();
//
//            if (!otherPlayer.equals(player) && otherPosition == candidateIndex && candidateIndex != otherPlayer.getHOME() ) {
////                playerManager.getPlayerHome(otherPlayer)
//                System.out.println("Collision Detected! " + player + " hit " + otherPlayer);
//                resolveCollision(otherPlayer, player, candidateIndex, advance, currentPosition, moves);
//                return;
            }
        }
    }


    private void resolveCollision(Player otherPlayer, Player player, int candidateIndex, int advance, int currentPosition, int moves) {
        player.setWasCollision(true);
        otherPlayer.setPlayerPosition(otherPlayer.getHOME());
        player.setPlayerPosition(candidateIndex);
        eventManager.onHit(player, advance, currentPosition, candidateIndex, moves);
    }
}