package Players;

public interface IPlayerFactory {
    public Player createPlayer(Colors color, int HOME, int tailDiversion, int numberOfTailPositions);

}
