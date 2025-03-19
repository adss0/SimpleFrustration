package Board;


public abstract class PositionChangeEvent {
    private final int oldPosition;
    private final int newPosition;
    private int advance;
    private int moves;
    private int totalMoves;
    public  Players.Player player;
    private String Info ;


    PositionChangeEvent(Players.Player player, int moves, int advance, int oldPosition, int newPosition) {
        this.player = player;
        this.advance = advance;
        this.moves = moves;
        this.oldPosition = oldPosition;
        this.newPosition = newPosition;
    }

    PositionChangeEvent(Players.Player player, int moves, int advance, int oldPosition, int newPosition, int totalMoves) {
        this.player = player;
        this.advance = advance;
        this.moves = moves;
        this.oldPosition = oldPosition;
        this.newPosition = newPosition;
        this.totalMoves = totalMoves;
    }

    public int oldPosition() {
        return oldPosition;
    }

    public int getMoves() {
        return moves;
    }
    public int getTotalMoves() {
        return totalMoves;
    }
    public int newPosition() {
        return newPosition;
    }
    public int getAdvance() {
        return advance;
    }
    public void setPlayer(Players.Player player) {
        this.player = player;
    }
    public String checkForTailPosition(Players.Player player){
//            if(player.getTailPositions().contains(newPosition)){
//                return "TAIL ";
//            }
        return "";
    }
    public String checkForHomePostion(Players.Player player){
//        if(oldPosition == player.getHOME() || newPosition == player.getHome()){
//            return "HOME ";
//        }
        return "";

    }

    @Override
    public String toString() {
        String rollInfo = String.format("{%s play %d rolls %d} ", player, moves, advance);
        String tailInfo = checkForTailPosition(player);
        String homeInfo= checkForHomePostion(player);

        String moveInfo = String.format("%s moves from %sPosition %d to %sPosition %d", player, homeInfo, oldPosition, tailInfo, newPosition);
        return rollInfo + moveInfo;
    }
}
