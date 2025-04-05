package Dice;

public class RandomDoubleDiceShaker implements DiceShaker{
    private final RandomSingleDiceShaker singleDiceShaker = new RandomSingleDiceShaker();

    @Override
    public int shake() {
        return (singleDiceShaker.shake() + singleDiceShaker.shake());
    }
}
