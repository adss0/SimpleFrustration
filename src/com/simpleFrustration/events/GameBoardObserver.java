package com.simpleFrustration.events;

public interface GameBoardObserver {
    void onEvent(OverflowEvent overflowEvent);
    void onEvent(UnderflowEvent underflowEvent);
    void onEvent(HomeEvent homeEvent);
    void onEvent(OvershootEvent onOvershootEvent);
    void onEvent(CollisionEvent onCollisionEvent);
}
