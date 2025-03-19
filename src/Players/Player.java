package Players;

import java.util.*;

public class Player {

    Colors color;
    private int HOME;
    private int tailDiversion;


    public Player(Colors color ) {
        this.color=color;
//        this.HOME= HOME;
//        this.tailDiversion = tailDiversion;


    }
    public void setHOME(int HOME) {
        this.HOME = HOME;
    }
    public void setTailDiversion(int tailDiversion) {
        this.tailDiversion = tailDiversion;
    }


    public int getHome(){
        return HOME;
    }

    public Colors getColor() {
        return color;
    }
    public int getHOME() {
        return HOME;
    }

    public int getTailDiversion() {
        return tailDiversion;
    }


    @Override
    public String toString() {
        return  color.toString() ;
    }
}
