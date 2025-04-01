package Events;

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

    public void onOverflow(Players.Player player, int advance, int originalIndex, int newIndex, int moves) {
        OverflowEvent event = new OverflowEvent(player, moves, advance, originalIndex, newIndex);
        notifyObservers(observer -> observer.onEvent(event));

    }

    public void onUnderflow(Players.Player player, int advance, int originalIndex, int newIndex,int moves) {
        UnderflowEvent event = new UnderflowEvent(player, moves, advance, originalIndex, newIndex);
        notifyObservers(observer -> observer.onEvent(event));
    }

    public void onHomeEvent(Players.Player player, int advance, int currentPosition, int candidateIndex, int moves, int totalMoves){
        HomeEvent event = new HomeEvent(player, moves, advance, currentPosition, candidateIndex, totalMoves);
        notifyObservers(observer -> observer.onEvent(event));
    }

    public void onBounce(Players.Player player, int advance, int currentPosition, int newIndex, int moves) {
        BounceEvent event = new BounceEvent(player, moves, advance, currentPosition, newIndex);
        notifyObservers(observer -> observer.onEvent(event));
    }

    public void onHit(Players.Player player, int advance, int currentPosition, int candidateIndex, int moves) {
        HitEvent event = new HitEvent(player, moves, advance, currentPosition, candidateIndex);
        notifyObservers(observer -> observer.onEvent(event));

    }

    private void notifyObservers(Consumer<GameBoardObserver> action) {
        observers.forEach(action);
    }
}
