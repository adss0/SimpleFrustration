package com.simpleFrustration.dice;

public class FixedDiceShakerFactory implements DiceShakerFactory {
    @Override
    public DiceShaker create() {
        return new FixedDiceShaker();
    }
}
