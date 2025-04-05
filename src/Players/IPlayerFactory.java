package Players;

public interface IPlayerFactory {
    Player createPlayer(Colors color, int HOME, int tailDiversion, int numberOfTailPositions, int boardSize);

}
