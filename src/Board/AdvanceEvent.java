package Board;

public class AdvanceEvent {
    private final int advance;
    private final int oldPosition;
    private final int newPosition;

    AdvanceEvent(int advance, int oldPosition, int newPosition) {
        this.advance = advance;
        this.oldPosition = oldPosition;
        this.newPosition = newPosition;
    }

    public int getAdvance() {
        return advance;
    }

    public int oldPosition() {
        return oldPosition;
    }

    public int newPosition() {
        return newPosition;
    }

    @Override
    public String toString() {
        return String.format("{advance %d oldPosition=%d, newPosition=%d}", advance, oldPosition, newPosition);
    }
}