package com.simpleFrustration.movements;

import com.simpleFrustration.events.EventManager;
import com.simpleFrustration.players.Player;
import com.simpleFrustration.players.PlayerManager;


public class HandleCollision implements IMovementHandler {
    private final PlayerManager playerManager;
    private final boolean disableHitEvent;
    private final EventManager eventManager;

    public HandleCollision(PlayerManager playerManager, boolean disableHitEvent, EventManager eventManager) {
        this.playerManager = playerManager;
        this.disableHitEvent = disableHitEvent;
        this.eventManager = eventManager;
    }

    @Override
    public void movementHandler(Player player, int advance, int currentPosition, int candidateIndex, int moves) {
        player.setWasCollision(false);
        if (disableHitEvent) return;

        // Check all other players for collision
        for (Player otherPlayer : playerManager.getPlayerList()) {
            if (!otherPlayer.equals(player)) {
                int otherPosition = otherPlayer.getPlayerPosition();

                // If players land on the same position (not their home), it's a collision
                if (otherPosition == candidateIndex && candidateIndex != otherPlayer.getHOME()) {
                    if(candidateIndex > otherPlayer.getTailDiversion()  && otherPlayer.isOverflowed()){
                        return;
                    }else {
                        resolveCollision(otherPlayer, player, candidateIndex, advance, currentPosition, moves);
                        return;
                    }
                }
            }
        }
    }


    private void resolveCollision(Player otherPlayer, Player player, int candidateIndex, int advance, int currentPosition, int moves) {
        player.setWasCollision(true);
        int hitPlayerOldPosition = otherPlayer.getPlayerPosition();
        otherPlayer.setPlayerPosition(otherPlayer.getHOME());
        player.setPlayerPosition(candidateIndex);
        eventManager.onCollision(player, advance, currentPosition, candidateIndex, moves, otherPlayer,hitPlayerOldPosition);
    }
}