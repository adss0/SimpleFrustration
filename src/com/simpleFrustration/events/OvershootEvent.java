package com.simpleFrustration.events;

import com.simpleFrustration.players.Player;

public final class OvershootEvent extends PositionChangeEvent {
    OvershootEvent(Player player, int play, int advance, int oldPosition, int newPosition) {
        super(player,  play, advance, oldPosition, newPosition);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(String.format("%s Overshoots!\n" , getPlayer()));
        sb.append(super.toString());
        return sb.toString();
    }
}
