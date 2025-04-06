package com.simpleFrustration.dice;

public class FixedDiceShaker implements DiceShaker{
    private static final int [] fixedDiceRoll = {12,5,2,12,7,4,2,12,3,5,2,12,5,4,4,8,2,2,12,3};
    private static int rollIndex= 0 ;


    @Override
    public int shake() {
        int roll = fixedDiceRoll[rollIndex];
        rollIndex = (rollIndex + 1) % fixedDiceRoll.length;

        return roll;
    }
}
