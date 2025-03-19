package Board;

public interface GameBoard {
    int getCurrentPosition(Players.Player player);
    void setPlayerPosition(Players.Player player, int newPosition);
    void advance();
    boolean isGameWon();

}