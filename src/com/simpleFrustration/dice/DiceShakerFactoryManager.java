package com.simpleFrustration.dice;

public class DiceShakerFactoryManager {

    public static DiceShakerFactory getFactory(int numberOfDice){

        return switch (numberOfDice) {
            case 1 -> new RandomSingleDiceShakerFactory();
            case 2 -> new RandomDoubleDiceShakerFactory();
            default -> new FixedDiceShakerFactory();
        };
    }
}
