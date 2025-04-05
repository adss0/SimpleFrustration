package Players;

public class Player {

    private final Colors color;
    private int HOME;
    private int tailDiversion;
    private boolean overflowed = false;
    private boolean isSpecialCase = false;
    private int numberOfTailPositions;
    private boolean wasCollision= false;

    public Player(Colors color, int HOME, int tailDiversion, int numberOfTailPositions) {
        this.color = color;
        this.HOME = HOME;
        this.tailDiversion = tailDiversion;
        this.numberOfTailPositions = numberOfTailPositions;
    }


    public int getHOME() {
        return HOME;
    }

    public void setHOME(int HOME) {
        this.HOME = HOME;
    }

    public int getTailDiversion() {
        return tailDiversion;
    }

    public void setTailDiversion(int tailDiversion) {
        this.tailDiversion = tailDiversion;
    }

    public Colors getColor() {
        return color;
    }

    public boolean isOverflowed() {
        return overflowed;
    }

    public void setOverflowed(boolean overflowed) {
        this.overflowed = overflowed;
    }

    public int getNumberOfTailPositions() {
        return numberOfTailPositions;
    }

    public void setNumberOfTailPositions(int numberOfTailPositions) {
        this.numberOfTailPositions = numberOfTailPositions;
    }

    public boolean isSpecialCase() {
        return isSpecialCase;
    }

    public void setSpecialCase(boolean specialCase) {
        isSpecialCase = specialCase;
    }
    public boolean isWasCollision() {
        return wasCollision;
    }

    public void setWasCollision(boolean wasCollision) {
        this.wasCollision = wasCollision;
    }



    @Override
    public String toString() {
        return color.toString();
    }
}
