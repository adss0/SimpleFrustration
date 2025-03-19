package Board;

public class HitEvent extends PositionChangeEvent {
    HitEvent(Players.Player player, int play, int advance, int oldPosition, int newPosition) {
        super(player, play, advance, oldPosition, newPosition);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("HITEVENT");
        sb.append(super.toString());
        return sb.toString();
    }
}

