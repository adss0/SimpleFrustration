package com.simpleFrustration.events;

import com.simpleFrustration.players.Player;

public final class HomeEvent extends PositionChangeEvent {

    HomeEvent(Player player, int play, int advance, int oldPosition, int newPosition, int totalMoves) {
        super(player, play, advance, oldPosition, newPosition, totalMoves);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append("\n");
        sb.append(String.format("%s wins in %d moves\n", getPlayer(), getMoves()));
        sb.append(String.format("Total plays %d", getTotalMoves()));
        return sb.toString();
    }
}