package Board;

import Players.Player;

public interface IGameBoard {
    boolean isGameWon();
    void advancePlayer(Player player, int advance);
    void undoPlayerPosition(Player player, int advance);
}