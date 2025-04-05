package Events;

import Players.Player;

public abstract class PositionChangeEvent {
    private int oldPosition;
    private int newPosition;
    private final int advance;
    private final int moves;
    private final int totalMoves;
    private final Players.Player player;

    // Constructor for Underflow, Overflow, Hit, and Bounce Events
    PositionChangeEvent(Players.Player player, int moves, int advance, int oldPosition, int newPosition) {
        this(player, moves, advance, oldPosition, newPosition, 0);
    }

    // Constructor for HomeEvent
    PositionChangeEvent(Players.Player player, int moves, int advance, int oldPosition, int newPosition, int totalMoves) {
        this.player = player;
        this.advance = advance;
        this.moves = moves;
        this.oldPosition = oldPosition;
        this.newPosition = newPosition;
        this.totalMoves = totalMoves;
    }

    public int getOldPosition() {
        return oldPosition; }
    public int getNewPosition() { return newPosition; }
    public int getAdvance() { return advance; }
    public int getMoves() { return moves; }
    public int getTotalMoves() { return totalMoves; }
    public Player getPlayer() { return player; }

    private String checkTailPosition(int position, boolean isOld) {
        if(player.isSpecialCase() && isOld) {
            return"HOME ";
        }

        if (position > player.getTailDiversion() && player.isOverflowed()) {
            int adjustedPos = position - player.getTailDiversion();
            if (adjustedPos < player.getNumberOfTailPositions()) {
                if (isOld) this.oldPosition = adjustedPos;
                else this.newPosition = adjustedPos;
                return "TAIL ";
            } else if (adjustedPos == player.getNumberOfTailPositions()) {
                if (!isOld) this.newPosition = 0;
                return "END";
            }
        }
        if(position == player.getHOME() && isOld){
            return "HOME ";
        }

        return "";
    }

    private String checkHomePosition() {
        return (player.getHOME() == this.oldPosition) ? "HOME " : "";
    }

    @Override
    public String toString() {
        String rollInfo = String.format("{%s play %d rolls %d}%n", player, moves, advance);
        String homeInfo = checkHomePosition();
        String oldPosInfo = checkTailPosition(oldPosition, true);
        String newPosInfo = checkTailPosition(newPosition, false);


        // ✅ If old position info is empty, use home info
        String oldPositionLabel = oldPosInfo;
        // ✅ If new position is "END", only show "END"
        String newPositionLabel = newPosInfo.equals("END") ? "END" : newPosInfo + "Position " + newPosition;

        return rollInfo + String.format(
                "%s moves from %sPosition %d to %s",
                player, oldPositionLabel, oldPosition, newPositionLabel
        );
    }
}
