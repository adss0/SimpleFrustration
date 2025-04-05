package Players;

import java.util.ArrayList;
import java.util.List;

public class PlayerManager{
    private final PlayerFactory playerFactory;
    private final List<Players.Player> playerList = new ArrayList<>();;
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
        int homePosition;
        int tailDiversion;
        int numberOfTailPositions = (boardSize <= 18) ? 3 : 6;
        Players.Colors[] colors = {Players.Colors.Red, Players.Colors.Blue, Players.Colors.Green, Players.Colors.Yellow};

        for (int i = 0; i < numberOfPlayers; i++) {
            homePosition = (int) Math.round(1 + (i * ((double)(boardSize - 1) / numberOfPlayers)));
            tailDiversion = (i == 0) ? boardSize : (homePosition - 1 == 0 ? homePosition : homePosition - 1);
            Player player = playerFactory.createPlayer(colors[i], homePosition, tailDiversion, numberOfTailPositions,boardSize);
            playerList.add(player);
            player.setPlayerPosition(homePosition);
            player.setLastValidPosition(homePosition);
        }
    }

}

