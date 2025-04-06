package com.simpleFrustration.board;

import com.simpleFrustration.dice.DiceShaker;
import com.simpleFrustration.players.Player;
import com.simpleFrustration.players.PlayerManager;
import java.util.Scanner;


public class Commands implements ICommands {
    private final GameBoard board;
    private final DiceShaker shaker;
    private final Scanner scanner;
    private final PlayerManager playerManager;
    private Player currentPlayer;
    private int advance;

    public Commands(GameBoard board, DiceShaker shaker, PlayerManager playerManager, Scanner scanner) {
        this.board = board;
        this.shaker = shaker;
        this.playerManager = playerManager;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        while (!board.isGameWon()) {
            board.moves++;

            for (int i = 0; i < playerManager.getPlayerList().size() && !board.isGameWon(); ) {
                this.currentPlayer = playerManager.getPlayerList().get(i);
                this.advance = shaker.shake();
                board.advancePlayer(this.currentPlayer, advance);
                if (board.isGameWon()) break;

                System.out.println("Do you want to undo your move? (yes/no)");
                String input = scanner.nextLine().trim().toLowerCase();
                if (input.equals("yes")) {
                    undo();
                } else {
                    i++;
                }
            }
        }
    }

    @Override
    public void undo() {
            board.undoPlayerPosition(this.currentPlayer, this.advance);
    }

}