package Players;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerManager implements IPlayerManager{

    private final Map<Players.Player, Integer> playerPositions = new HashMap<>();
    private final Map<Players.Player, Integer> playerHome = new HashMap<>();
    private final Map<Players.Player, Integer> playerEnd = new HashMap<>();
    private  List<Players.Player> playerList;
    private Map<Player, Integer> lastValidPositions = new HashMap<>();
    private Map<Players.Player, Boolean> playerOverflowed = new HashMap<>();
    private int BOARD_SIZE_WITH_TAILS;


   public PlayerManager(List<Players.Player> playerList, int BOARD_SIZE, int numberOfTailPositions ){
       this.playerList =playerList;
       this.BOARD_SIZE_WITH_TAILS = BOARD_SIZE + numberOfTailPositions;
       initializePlayerPositions(numberOfTailPositions);

    }
    @Override
    public List<Player> getPlayerList() {
        return playerList;
    }

    @Override
    public Map<Player, Integer> getPlayerPositions() {
        return playerPositions;
    }

    public void initializePlayerPositions(int numberOfTailPositions) {
            for (Players.Player player : playerList) {
            playerPositions.put(player, player.getHOME());
            playerHome.put(player, player.getHOME());
            playerEnd.put(player, (player.getTailDiversion() + numberOfTailPositions));
            playerOverflowed.put(player, false);
            lastValidPositions.put(player, player.getHOME());  // Store the initial valid position as 0

        }
    }

    @Override
    public int getPlayerPosition(Players.Player player) {
        return playerPositions.getOrDefault(player, -1);
    }

    @Override
    public void removePlayer(Players.Player player) {
        playerList.remove(player);
        playerPositions.remove(player);
        playerHome.remove(player);
        playerEnd.remove(player);
        lastValidPositions.remove(player);
    }

    @Override
    public int getCurrentPosition(Players.Player player) {
        return playerPositions.getOrDefault(player, -1);

    }

    @Override
    public void setPlayerPosition(Player player, int newPosition) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        if (newPosition < 0 || newPosition >= BOARD_SIZE_WITH_TAILS) {
            throw new IndexOutOfBoundsException("Invalid position");
        }
        lastValidPositions.put(player, playerPositions.get(player)); // Save previous
        playerPositions.put(player, newPosition);
    }

    @Override
    public int getPlayerHome(Players.Player player) {
        return playerHome.getOrDefault(player, -1);
    }

    @Override
    public int getPlayerEnd(Players.Player player) {
        return playerEnd.getOrDefault(player, -1);
    }

    @Override
    public boolean hasOverflowed(Players.Player player) {
        return playerOverflowed.getOrDefault(player, false);
    }

    @Override
    public void setOverflowed(Players.Player player, boolean state) {
        playerOverflowed.put(player, state);
    }

    @Override
    public Integer getLastValidPosition(Player player){
        return lastValidPositions.getOrDefault(player, 0);
    }
    public void setLastValidPosition(Player player) {
        lastValidPositions.put(player, playerPositions.get(player));  // Store the current position as the last valid position
    }
}

