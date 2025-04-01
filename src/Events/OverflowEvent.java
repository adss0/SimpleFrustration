package Events;

public final class OverflowEvent extends PositionChangeEvent {
    OverflowEvent(Players.Player player, int play, int advance, int oldPosition, int newPosition) {
        super(player, play, advance, oldPosition, newPosition);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OverflowEvent");
        sb.append(super.toString());
        return sb.toString();
    }
}
