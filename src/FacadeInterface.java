public interface FacadeInterface {
    void play(int numberOfDice, int numberOfPlayers, int boardSize, boolean disableHitEvent, boolean disableBounceEvent);
    void undo(Players.Player player);


}
