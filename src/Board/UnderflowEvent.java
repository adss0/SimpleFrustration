package Board;

public final class UnderflowEvent extends PositionChangeEvent {
    UnderflowEvent(Players.Player player, int play, int advance, int oldPosition, int newPosition) {

        super(player, play, advance, oldPosition, newPosition);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UnderflowEvent");
        sb.append(super.toString());
        return sb.toString();
    }
}
