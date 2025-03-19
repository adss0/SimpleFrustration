package Dice;

public class FixedDiceShaker implements DiceShaker{
    private static final int [] fixedDiceRoll = {10, 1, 2, 1, 2};
    private static int rollIndex= 0 ;


    @Override
    public int shake() {
        // Return the next roll in the fixedDiceRoll array, then increment the index
        int roll = fixedDiceRoll[rollIndex];

        // You can loop back to the start once all values have been returned
        rollIndex = (rollIndex + 1) % fixedDiceRoll.length;

        return roll;
    }
}
