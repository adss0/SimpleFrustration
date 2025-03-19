package Board;

public interface Command {
    void execute();

    void undo(Players.Player player);
}
