package Board;

import Players.Player;

public final class BounceEvent extends PositionChangeEvent {
    BounceEvent(Player player, int play, int advance, int oldPosition, int newPosition) {
        super(player,  play, advance, oldPosition, newPosition);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BounceEvent");
        sb.append(super.toString());
        return sb.toString();
    }
}
