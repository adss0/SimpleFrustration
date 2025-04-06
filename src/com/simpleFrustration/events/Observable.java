package com.simpleFrustration.events;

public class Observable implements GameBoardObserver {

    @Override
    public void onEvent(OverflowEvent overflowEvent) {
        System.out.format("%s%n", overflowEvent);
    }

    @Override
    public void onEvent(UnderflowEvent underflowEvent) {System.out.format("%s%n", underflowEvent);}

    @Override
    public void onEvent(HomeEvent homeEvent) {System.out.format("%s%n", homeEvent);}

    @Override
    public void onEvent(OvershootEvent onOvershootEvent) {System.out.format("%s%n", onOvershootEvent);}
    @Override
    public void onEvent(CollisionEvent onCollisionEvent) { System.out.format("%s%n", onCollisionEvent);}
}
