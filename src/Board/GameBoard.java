package Board;

public interface GameBoard {
    int getCurrentPosition();
    void setPosition(int newPosition);
    void advance(int count);

}