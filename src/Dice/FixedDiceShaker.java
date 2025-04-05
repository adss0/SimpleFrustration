package Dice;

public class FixedDiceShaker implements DiceShaker{
    private static final int [] fixedDiceRoll = {6, 4, 7, 12, 5};
    private static int rollIndex= 0 ;


    @Override
    public int shake() {
        int roll = fixedDiceRoll[rollIndex];
        rollIndex = (rollIndex + 1) % fixedDiceRoll.length;

        return roll;
    }
}
