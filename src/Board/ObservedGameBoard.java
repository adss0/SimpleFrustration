package Board;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ObservedGameBoard  implements GameBoard {

    private static  int HOME;
    private static  int END;
    private static final int LENGTH = END - HOME + 1;
    private int currentIndex;
    private final List<GameBoardObserver> observers = new ArrayList<>();

    private static int indexToPosition(int index) {
        return index + 1;
    }

    public ObservedGameBoard(int HOME, int END) {
        this.HOME=HOME;
        this.END = END;
        setIndex(HOME);
    }

    private void setIndex(int newIndex) {
        if (newIndex >= HOME && newIndex <= END) {
            currentIndex = newIndex;
        } else {
            throw new IndexOutOfBoundsException(newIndex);
        }
    }

    public void add(GameBoardObserver observer) {
        observers.add(observer);
    }

    public void detach(GameBoardObserver observer) {
        observers.remove(observer);
    }


    @Override
    public int getCurrentPosition() {
        return indexToPosition(currentIndex);
    }

    @Override
    public void setPosition(int newPosition) {
        this.currentIndex = newPosition - 1;
    }

    @Override
    public void advance(int advance) {
        int candidateIndex = currentIndex + advance;
        if (candidateIndex > END) {
            onOverflow(advance, candidateIndex);
        } else {
            onUnderflow(advance, candidateIndex);
        }
    }

    private void onOverflow(int advance, int candidateIndex) {
        int originalIndex = currentIndex;
        int newIndex = candidateIndex % LENGTH;
        setIndex(newIndex);
        if (newIndex == HOME) {
            HomeEvent event = new HomeEvent(advance, indexToPosition(originalIndex),
                    indexToPosition(newIndex));
            notifyObservers(observer -> observer.onEvent(event));
        } else {
            OverflowEvent event = new OverflowEvent(advance, indexToPosition(originalIndex),
                    indexToPosition(newIndex));
            notifyObservers(observer -> observer.onEvent(event));
        }

    }

    private void onUnderflow(int advance, int newIndex) {
        int originalIndex = currentIndex;
        setIndex(newIndex);
        UnderflowEvent event = new UnderflowEvent(advance, indexToPosition(originalIndex),
                indexToPosition(newIndex));
        notifyObservers(observer -> observer.onEvent(event));
    }

    private void notifyObservers(Consumer<GameBoardObserver> action) {
        observers.forEach(action);
    }
}