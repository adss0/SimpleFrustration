
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        show(new Dice.RandomDoubleDiceShakerFactory());

        Board.ObservedGameBoard game = new Board.ObservedGameBoard(1, 18);
        Board.GameBoardObserver observer = new Board.Observable();
        game.add(observer);
        play(game, new Dice.RandomDoubleDiceShakerFactory());
        game.detach(observer);

    }
    private static void show(Dice.DiceShakerFactory factory){
        Dice.DiceShaker shaker= factory.create();
       System.out.format( "Shake %s%n", shaker.shake());

    }
    private static void play(Board.GameBoard game, Dice.DiceShakerFactory factory)
    {
        Dice.DiceShaker shaker= factory.create();
        Stack<Board.AdvanceCommand> commands = new Stack<>();
        int shak = shaker.shake();
        System.out.println(shak);
        commands.push(new Board.AdvanceCommand(game,shak));
        commands.push(new Board.AdvanceCommand(game,shak));
        commands.push(new Board.AdvanceCommand(game,shak));

        for(Board.Command command: commands)
        {
            command.execute();
        }
        //undo
        while(!commands.empty())
        {
            commands.pop().undo();
        }
    }
}
