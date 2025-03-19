package Board;

public class AdvanceCommand implements Command{

    private final GameBoard board;
    private int previousPosition;


    public AdvanceCommand(GameBoard board) {
        this.board = board;
    }

    @Override
    public void execute() {
        board.advance();

    }

    @Override
    public void undo(Players.Player player) {
        previousPosition = board.getCurrentPosition(player);
        board.setPlayerPosition(player, previousPosition);
    }
}