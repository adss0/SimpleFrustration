package Board;

import Dice.DiceShaker;
import Players.Player;
import Players.PlayerManager;
import java.util.Scanner;


public class Commands implements ICommands {
    private final GameBoard board;
    private final DiceShaker shaker;
    private final Scanner scanner;
    private final PlayerManager playerManager;

    private Player currentPlayer;
    private int advance;

    public Commands(GameBoard board, DiceShaker shaker, PlayerManager playerManager) {
        this.board = board;
        this.shaker = shaker;
        this.playerManager = playerManager;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void execute() {
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

    @Override
    public void undo() {
            board.undoPlayerPosition(this.currentPlayer, advance);
            System.out.println(this.currentPlayer + "'s move has been undone.");
    }
}