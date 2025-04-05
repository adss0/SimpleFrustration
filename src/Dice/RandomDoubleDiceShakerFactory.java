package Dice;

public class RandomDoubleDiceShakerFactory implements DiceShakerFactory{

    @Override
    public DiceShaker create() {
        return new RandomDoubleDiceShaker();
    }
}
