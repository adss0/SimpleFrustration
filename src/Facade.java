import Board.Commands;
import Board.GameBoard;
import Board.ICommands;
import Players.Player;
import Players.PlayerFactory;
import Players.PlayerManager;

import java.util.ArrayList;
import java.util.List;

public class Facade implements IFacade {

    @Override
    public void play(int numberOfDice, int numberOfPlayers, int boardSize, boolean disableHitEvent, boolean disableBounceEvent) {
        PlayerFactory playerFactory = new PlayerFactory();
        List<Player> playerList = new ArrayList<>();
        int numberOfTailPositions = (boardSize <= 18) ? 3 : 6;
        Players.Colors[] colors = {Players.Colors.Red, Players.Colors.Blue, Players.Colors.Green, Players.Colors.Yellow};
        int homePosition;
        int tailDiversion;

        for (int i = 0; i < numberOfPlayers; i++) {
             homePosition = (int) Math.round(1 + (i * ((double)(boardSize - 1) / numberOfPlayers)));
             tailDiversion = (i == 0) ? boardSize : (homePosition - 1 == 0 ? homePosition : homePosition - 1);
            playerList.add(playerFactory.createPlayer(colors[i], homePosition, tailDiversion, numberOfTailPositions));
        }

        Dice.DiceShakerFactory factory = null;

        if(numberOfDice == 1){
            factory = new Dice.RandomSingleDiceShakerFactory();
        }else if (numberOfDice ==2){
            factory = new Dice.RandomDoubleDiceShakerFactory();
        }

        factory = new Dice.FixedDiceShakerFactory();

        PlayerManager playerManager = new PlayerManager(playerList, boardSize, numberOfTailPositions);

        GameBoard game = new GameBoard(boardSize, disableHitEvent, disableBounceEvent, playerManager);

        ICommands command = new Commands(game, factory.create(), playerManager);



        while (!game.isGameWon()) {
            command.execute();
        }

    }


}
