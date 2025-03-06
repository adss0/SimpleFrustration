package Board;

public final class OverflowEvent extends PositionChangeEvent {
    OverflowEvent(int advance,int oldPosition, int newPosition) {
        super(advance, oldPosition, newPosition);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OverflowEvent");
        sb.append(super.toString());
        return sb.toString();
    }
}
