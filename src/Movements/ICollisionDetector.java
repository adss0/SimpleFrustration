package Movements;

public interface ICollisionDetector {
    boolean movementHandler(Players.Player player, int advance, int currentPosition, int candidateIndex, int moves) ;

}
