package Events;

import Players.Player;

public final class HomeEvent extends PositionChangeEvent {

    HomeEvent(Player player, int play, int advance, int oldPosition, int newPosition, int totalPlayers) {
        super(player, play, advance, oldPosition, newPosition, totalPlayers);
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder("HomeEvent");
        sb.append(super.toString());
        return sb.toString();
    }
}