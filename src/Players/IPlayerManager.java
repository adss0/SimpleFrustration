package Players;
import java.util.List;
import java.util.Map;

public interface IPlayerManager {
    List<Player> getPlayerList();
    Map<Player, Integer> getPlayerPositions();
    int getPlayerPosition(Player player);
    int getPlayerHome(Player player);
    int getPlayerEnd(Player player);
    int getCurrentPosition(Player player);
    boolean hasOverflowed(Player player);
    void setPlayerPosition(Player player, int newPosition);
    void setOverflowed(Player player, boolean state);
    void removePlayer(Player player);
    Integer getLastValidPosition(Player player);
    void setLastValidPosition(Player player);
}
