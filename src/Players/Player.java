package Players;

public class Player implements IPlayer {

    private Colors color;
    private int HOME;
    private int tailDiversion;

    public Player(Colors color, int HOME, int tailDiversion ) {
        this.color=color;
        this.HOME= HOME;
        this.tailDiversion = tailDiversion;
    }

    @Override
    public int getHOME() {
        return HOME;
    }
    @Override
    public void setHOME(int HOME) {
        this.HOME = HOME;
    }
    @Override
    public int getTailDiversion() {
        return tailDiversion;
    }
    @Override
    public void setTailDiversion(int tailDiversion) {
        this.tailDiversion = tailDiversion;
    }
    @Override
    public Colors getColor() {
        return color;
    }


    @Override
    public String toString() {
        return  color.toString() ;
    }
}
