package Players;

public class PlayerFactory implements IPlayerFactory {

    @Override
    public Player createPlayer(Colors color, int HOME, int tailDiversion) {
        return new Player(color, HOME, tailDiversion);
    }

}
