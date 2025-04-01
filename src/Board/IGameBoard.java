package Board;

import Players.Player;

public interface IGameBoard {
    boolean isGameWon();
    void undoPlayerPosition(Player player, int advance);
}