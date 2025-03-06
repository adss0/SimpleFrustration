package Board;

public class Observable implements GameBoardObserver {
    @Override
    public void onEvent(AdvanceEvent event) {
        System.out.format("%s%n", event);
    }

    @Override
    public void onEvent(PositionChangeEvent event) {
        System.out.format("%s%n", event);
    }

    @Override
    public void onEvent(OverflowEvent overflowEvent) {
        System.out.format("%s%n", overflowEvent);
    }

    @Override
    public void onEvent(UnderflowEvent underflowEvent) {
        System.out.format("%s%n", underflowEvent);
    }

    @Override
    public void onEvent(HomeEvent homeEvent) {
        System.out.format("%s%n", homeEvent);
    }
}
