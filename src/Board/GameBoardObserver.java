package Board;

public interface GameBoardObserver {
    //    void update(Payload payload);
    void onEvent(AdvanceEvent event);
    void onEvent(PositionChangeEvent event);

    void onEvent(OverflowEvent overflowEvent);
    void onEvent(UnderflowEvent underflowEvent);
    void onEvent(HomeEvent homeEvent);
}
