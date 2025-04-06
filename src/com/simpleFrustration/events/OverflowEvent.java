package com.simpleFrustration.events;

import com.simpleFrustration.players.Player;

public final class OverflowEvent extends PositionChangeEvent {
    OverflowEvent(Player player, int play, int advance, int oldPosition, int newPosition) {
        super(player, play, advance, oldPosition, newPosition);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(String.format("%s Wraps around the board\n", getPlayer()));
        sb.append(super.toString());
        return sb.toString();
    }
}
