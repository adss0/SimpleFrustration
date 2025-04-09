package com.simpleFrustration.dice;

public class DiceShakerManager implements IDiceShakerManager {

    public DiceShaker createDiceShaker(int numberOfDice){
        return switch (numberOfDice) {
            case 1 ->  new RandomSingleDiceShaker();
            case 2 ->  new RandomDoubleDiceShaker();
            default -> new FixedDiceShaker();
        };
    }
}
