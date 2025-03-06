package Players;

public class Players {

    Colors color;

    Players(Colors color ){
        this.color=color;
    }


    public Colors getColor() {
        return color;
    }


    @Override
    public String toString() {
        return  color.toString() ;
    }
}
