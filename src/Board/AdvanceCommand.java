package Board;

public class AdvanceCommand implements Command{
    private final GameBoard board;
    private final int advance;
    private int previousPosition;

    public AdvanceCommand(GameBoard board, int advance) {
        this.board = board;
        this.advance = advance;
    }

    @Override
    public void execute() {
        previousPosition = board.getCurrentPosition();
        board.advance(advance);
    }

    @Override
    public void undo() {
        board.setPosition(previousPosition);
    }
}
