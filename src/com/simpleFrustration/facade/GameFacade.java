package com.simpleFrustration.facade;

import com.simpleFrustration.board.Commands;
import com.simpleFrustration.board.GameBoard;
import com.simpleFrustration.board.ICommands;

import com.simpleFrustration.dice.IDiceShakerManager;
import com.simpleFrustration.dice.DiceShakerManager;
import com.simpleFrustration.players.PlayerFactory;
import com.simpleFrustration.players.PlayerManager;

import java.util.Scanner;


public class GameFacade implements IFacade {

    @Override
    public void play(int numberOfDice, int numberOfPlayers, int boardSize, boolean disableCollision, boolean disableOvershoot, Scanner scanner) {

        IDiceShakerManager dice = new DiceShakerManager();
        PlayerFactory playerFactory = new PlayerFactory();

        PlayerManager playerManager = new PlayerManager(boardSize, numberOfPlayers, playerFactory);
        // Initialise the Game Board
        GameBoard game = new GameBoard(boardSize, disableCollision, disableOvershoot, playerManager);
        //Initialise the Commands for the Game.Board
        ICommands command = new Commands(game, dice.createDiceShaker(numberOfDice), playerManager, scanner);
        //Run the command
        command.execute();

    }



}
