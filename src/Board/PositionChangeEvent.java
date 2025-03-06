package Board;

public abstract class PositionChangeEvent {
    private final int oldPosition;
    private final int newPosition;

    PositionChangeEvent(int oldPosition, int newPosition) {
        this.oldPosition = oldPosition;
        this.newPosition = newPosition;
    }
    PositionChangeEvent(int advance, int oldPosition, int newPosition) {
        this.oldPosition = oldPosition;
        this.newPosition = newPosition;
    }

    public int oldPosition() {
        return oldPosition;
    }

    public int newPosition() {
        return newPosition;
    }

    @Override
    public String toString() {
        return String.format("{oldPosition=%d, newPosition=%d}",oldPosition, newPosition);
    }
}
