package Board;

public class Observable implements GameBoardObserver {

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
        int moves = homeEvent.getMoves();
        int totalMoves = homeEvent.getTotalMoves();
        System.out.format("%s%n", homeEvent);
        System.out.format("%s wins in %d moves%n", homeEvent.player, moves);
        System.out.format("Total plays %d", totalMoves);
    }

    @Override
    public void onEvent(BounceEvent onBounceEvent) {
        System.out.format("%s%n", onBounceEvent);

    }
    @Override
    public void onEvent(HitEvent onHitEvent) {
        System.out.format("%s%n", onHitEvent);

    }
}
