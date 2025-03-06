package Dice;

public class RandomSingleDiceShakerFactory implements DiceShakerFactory{

    @Override
    public DiceShaker create() {
        return new RandomSingleDiceShaker();
    }
}
