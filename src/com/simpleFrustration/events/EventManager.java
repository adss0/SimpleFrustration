package com.simpleFrustration.events;

import com.simpleFrustration.players.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class EventManager {

    private final List<GameBoardObserver> observers = new ArrayList<>();

    public EventManager() {
        add(new Observable());  // Automatically add observer when initialized
    }

    public void add(GameBoardObserver observer)
    {
        observers.add(observer);
    }

    public void detach(GameBoardObserver observer) {
        observers.remove(observer);
    }

    public void onOverflow(Player player, int advance, int originalIndex, int newIndex, int moves) {
        OverflowEvent event = new OverflowEvent(player, moves, advance, originalIndex, newIndex);
        notifyObservers(observer -> observer.onEvent(event));

    }

    public void onUnderflow(Player player, int advance, int originalIndex, int newIndex, int moves) {
        UnderflowEvent event = new UnderflowEvent(player, moves, advance, originalIndex, newIndex);
        notifyObservers(observer -> observer.onEvent(event));
    }

    public void onHomeEvent(Player player, int advance, int currentPosition, int candidateIndex, int moves, int totalMoves){
        HomeEvent event = new HomeEvent(player, moves, advance, currentPosition, candidateIndex, totalMoves);
        notifyObservers(observer -> observer.onEvent(event));
    }

    public void onOvershoot(Player player, int advance, int currentPosition, int newIndex, int moves) {
        OvershootEvent event = new OvershootEvent(player, moves, advance, currentPosition, newIndex);
        notifyObservers(observer -> observer.onEvent(event));
    }

    public void onCollision(Player player, int advance, int currentPosition, int candidateIndex, int moves, Player hitPlayer, int hitPlayerOldPosition) {
        CollisionEvent event = new CollisionEvent(player, moves, advance, currentPosition, candidateIndex, hitPlayer, hitPlayerOldPosition );
        notifyObservers(observer -> observer.onEvent(event));

    }

    private void notifyObservers(Consumer<GameBoardObserver> action) {
        observers.forEach(action);
    }
}
