package Dice;

public class DiceShakerDecorator implements DiceShaker {
    private final DiceShaker component;
    private final DiceShaker shaker = new RandomSingleDiceShaker();


    public DiceShakerDecorator(DiceShaker component){
        this.component = component;
    }

    @Override
    public int shake() {
        return component.shake()+ shaker.shake();
    }
}
