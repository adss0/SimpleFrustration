package com.simpleFrustration.board;

import com.simpleFrustration.players.Player;

public interface IGameBoard {
    boolean isGameWon();
    void advancePlayer(Player player, int advance);
    void undoPlayerPosition(Player player, int advance);
}