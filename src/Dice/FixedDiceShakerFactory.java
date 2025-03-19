package Dice;

public class FixedDiceShakerFactory implements DiceShakerFactory {
    @Override
    public DiceShaker create() {
        return new FixedDiceShaker();
    }
}
