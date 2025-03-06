package Board;

public final class HomeEvent extends PositionChangeEvent {

    HomeEvent(int advance, int originalPosition, int newPosition) {
        super(advance, originalPosition, newPosition);
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder("HomeEvent");
        sb.append(super.toString());
        return sb.toString();
    }
}