package com.simpleFrustration.events;

import com.simpleFrustration.players.Player;

public class CollisionEvent extends PositionChangeEvent {
    private final Player hitPlayer;
    private final int hitPlayerOldPosition;
    CollisionEvent(Player player, int play, int advance, int oldPosition, int newPosition, Player hitPlayer, int hitPlayerOldPosition) {
        super(player, play, advance, oldPosition, newPosition);
        this.hitPlayer = hitPlayer;
        this.hitPlayerOldPosition = hitPlayerOldPosition;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append("\n");
        sb.append(String.format("%s Position %d hit! %s moves from Position %d to HOME (Position %d)",
                hitPlayer, hitPlayerOldPosition,
                hitPlayer, hitPlayerOldPosition,
                hitPlayer.getHOME()));
        return sb.toString();
    }
}

