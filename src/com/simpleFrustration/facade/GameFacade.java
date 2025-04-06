package com.simpleFrustration.facade;

import com.simpleFrustration.board.Commands;
import com.simpleFrustration.board.GameBoard;
import com.simpleFrustration.board.ICommands;

import com.simpleFrustration.dice.DiceShakerFactoryManager;
import com.simpleFrustration.dice.DiceShakerFactory;
import com.simpleFrustration.players.PlayerFactory;
import com.simpleFrustration.players.PlayerManager;

import java.util.Scanner;


public class GameFacade implements IFacade {

    @Override
    public void play(int numberOfDice, int numberOfPlayers, int boardSize, boolean disableCollision, boolean disableOvershoot, Scanner scanner) {

        DiceShakerFactory factory = DiceShakerFactoryManager.getFactory(numberOfDice);
        PlayerFactory playerFactory = new PlayerFactory();

        PlayerManager playerManager = new PlayerManager(boardSize, numberOfPlayers, playerFactory);
        // Initialise the Game Game.Board
        GameBoard game = new GameBoard(boardSize, disableCollision, disableOvershoot, playerManager);
        //Initialise the Commnads for the Game Game.Board
        ICommands command = new Commands(game, factory.create(), playerManager, scanner);
        //Run the command
        command.execute();

    }



}
