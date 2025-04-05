package Players;

public class Player {

    private final Colors color;
    private int HOME;
    private int END;
    private int lastValidPosition;
    private int playerPosition;
    private int tailDiversion;
    private boolean overflowed = false;
    private boolean isSpecialCase = false;
    private int numberOfTailPositions;
    private boolean wasCollision= false;
    private static int boardSize;

    public Player(Colors color, int HOME, int tailDiversion, int numberOfTailPositions, int boardSize) {
        this.color = color;
        this.HOME = HOME;
        this.tailDiversion = tailDiversion;
        this.numberOfTailPositions = numberOfTailPositions;
        this.END = tailDiversion + numberOfTailPositions;
        this.boardSize= boardSize;
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
        if(getHOME() == 1){
            setOverflowed(true);
        }
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
    public int getEND() {
        return END;
    }

    public void setEND(int END) {
        this.END = END;
    }
    public int getLastValidPosition() {
        return lastValidPosition;
    }

    public void setLastValidPosition(int lastValidPosition) {
        this.lastValidPosition = lastValidPosition;
    }

    public int getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(int playerPosition) {
        this.playerPosition = playerPosition;

        if (playerPosition < 0 || playerPosition > boardSize + numberOfTailPositions) {
            throw new IndexOutOfBoundsException("Invalid position");
        }
        if(isOverflowed()){
            setSpecialCase(false);
        }
        setLastValidPosition(getPlayerPosition());
        this.playerPosition = playerPosition;
    }

    @Override
    public String toString() {
        return color.toString();
    }
}
