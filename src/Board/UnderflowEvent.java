package Board;

public final class UnderflowEvent extends PositionChangeEvent {
    UnderflowEvent(int advance, int oldPosition, int newPosition) {

        super(advance, oldPosition, newPosition);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UnderflowEvent");
        sb.append(super.toString());
        return sb.toString();
    }
}
