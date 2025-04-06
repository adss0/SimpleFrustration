package com.simpleFrustration.players;

import java.util.ArrayList;
import java.util.List;

public class PlayerManager{
    private final PlayerFactory playerFactory;
    private final List<Player> playerList = new ArrayList<>();;
    private final int boardSize;
    private final int numberOfPlayers;

    public PlayerManager( int boardSize, int numberOfPlayers, PlayerFactory playerFactory){
       this.boardSize = boardSize;
       this.numberOfPlayers = numberOfPlayers;
       this.playerFactory = playerFactory;
        initializePlayers();
    }
    public List<Player> getPlayerList() {
        return playerList;
    }

    public void addPlayer(Player player) {
        playerList.add(player);
    }
    public void removePlayer(Player player){
        playerList.remove(player);
    }

    public void initializePlayers(){
        int homePosition = 1;
        int tailDiversion;
        int numberOfTailPositions = (boardSize <= 18) ? 3 : 6;
        Colors[] colors = {Colors.Red, Colors.Blue, Colors.Green, Colors.Yellow};

        for (int i = 0; i < numberOfPlayers; i++) {
            if (numberOfPlayers == 4  && boardSize == 36) {
                homePosition = 1 + i * 9; // The spacing between home positions is 9
            }else {
                homePosition = (int) Math.round(1 + (i * ((double) (boardSize - 1) / numberOfPlayers)));
            }
            tailDiversion = (i == 0) ? boardSize : (homePosition - 1 == 0 ? homePosition : homePosition - 1);
            Player player = playerFactory.createPlayer(colors[i], homePosition, tailDiversion, numberOfTailPositions,boardSize);

            playerList.add(player);
            player.setPlayerPosition(homePosition);
            player.setLastValidPosition(homePosition);
        }
    }

}

