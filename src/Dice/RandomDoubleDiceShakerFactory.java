package Dice;

public class RandomDoubleDiceShakerFactory implements DiceShakerFactory{
    DiceShakerFactory factory = new RandomSingleDiceShakerFactory();


    @Override
    public DiceShaker create() {
        return new DiceShakerDecorator(factory.create());
    }
}
