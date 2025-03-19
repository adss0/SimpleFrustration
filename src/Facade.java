

import Players.Colors;
import Players.Player;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

public class Facade implements FacadeInterface {

    @Override
    public void play(int numberOfDice, int numberOfPlayers, int boardSize, boolean disableHitEvent, boolean disableBounceEvent) {
        List<Players.Player> playerList = new ArrayList<>();

        Players.Colors [] colors= {Players.Colors.Red, Players.Colors.Blue, Players.Colors.Green, Colors.Yellow};

        for(int i=0; i<numberOfPlayers; i++){
            playerList.add((new Player(colors[i])));
        }


        Dice.DiceShakerFactory factory = null;

        if(numberOfDice == 1){
            factory = new Dice.RandomSingleDiceShakerFactory();
        }else if (numberOfDice ==2){
            factory = new Dice.RandomDoubleDiceShakerFactory();
        }

//        factory = new Dice.FixedDiceShakerFactory();


        Board.ObservedGameBoard game = new Board.ObservedGameBoard(boardSize, playerList, factory.create(), disableHitEvent, disableBounceEvent);
        Board.Command command = new Board.AdvanceCommand(game);

        Board.Observable observer = new Board.Observable(); // Create Observer
        game.add(observer);



        while (!game.isGameWon()) {
//            command.execute();
            game.advance();
        }
        game.detach(observer);

    }

    @Override
    public void undo(Players.Player player) {
////        Board.ObservedGameBoard game = new Board.ObservedGameBoard(player);
//        int previousPosition = game.getCurrentPosition(player);
//
//        game.setPosition(player, previousPosition);
    }
//    private void calculateHomePosition(int noOfPlayers, int boardSize){
//        if(noOfPlayers ==4)
//
//    }

}
