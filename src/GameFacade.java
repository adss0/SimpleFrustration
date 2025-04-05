import Board.Commands;
import Board.GameBoard;
import Board.ICommands;

import Dice.DiceShakerFactoryManager;
import Players.PlayerFactory;
import Players.PlayerManager;


public class GameFacade implements IFacade {

    @Override
    public void play(int numberOfDice, int numberOfPlayers, int boardSize, boolean disableHitEvent, boolean disableBounceEvent) {

        Dice.DiceShakerFactory factory = DiceShakerFactoryManager.getFactory(numberOfDice);
        PlayerFactory playerFactory = new PlayerFactory();

        PlayerManager playerManager = new PlayerManager(boardSize, numberOfPlayers, playerFactory);
        // Initialise the Game Board
        GameBoard game = new GameBoard(boardSize, disableHitEvent, disableBounceEvent, playerManager);
        //Initialise the Commnads for the Game Board
        ICommands command = new Commands(game, factory.create(), playerManager);
        //Run the command
        command.execute();

    }



}
