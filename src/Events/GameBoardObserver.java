package Events;

public interface GameBoardObserver {
    void onEvent(OverflowEvent overflowEvent);
    void onEvent(UnderflowEvent underflowEvent);
    void onEvent(HomeEvent homeEvent);
    void onEvent(BounceEvent onBounceEvent);
    void onEvent(HitEvent onHitEvent);
}
