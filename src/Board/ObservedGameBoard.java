package Board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@SuppressWarnings("ALL")
public class ObservedGameBoard implements GameBoard {

    private final List<GameBoardObserver> observers = new ArrayList<>();
    private final Map<Players.Player, Integer> playerPositions = new HashMap<>();
    private final Map<Players.Player, Integer> playerHome = new HashMap<>();
    private final Map<Players.Player, Integer> playerEnd = new HashMap<>();
    private List<Players.Player> playerList = new ArrayList<>();
    private Map<Players.Player, Boolean> playerOverflowed = new HashMap<>();

    private final int BOARD_SIZE_WITH_TAILS;
    public boolean gameWon = false; // Track if someone has won
    private final Dice.DiceShaker shaker;
    private static int moves;
    private static int totalMoves;
    private static int numberOfTailPositions;
    private static int BOARD_SIZE;
    private static boolean disableHitEvent;
    private static boolean disableBounceEvent;




    public ObservedGameBoard(int BOARD_SIZE, List<Players.Player> playerList, Dice.DiceShaker shaker, boolean hit, boolean bounce) {
        if(BOARD_SIZE <= 18){
            this.numberOfTailPositions = 3;
        } else{
            this.numberOfTailPositions = 6;
        }
        this.BOARD_SIZE_WITH_TAILS = BOARD_SIZE + this.numberOfTailPositions;
        this.BOARD_SIZE = BOARD_SIZE;
        this.playerList = playerList;
        this.shaker = shaker;
        this.disableHitEvent =hit;
        this.disableBounceEvent =bounce;
        initialize();
    }

    // Get current position of player
    public int getPlayerPosition(Players.Player player) {
        return playerPositions.getOrDefault(player, -1);
    }

    private void calculateHomeAndTailDiversion() {
        int homePosition;
        int tailDiversion;

        for (int i = 0; i < playerList.size(); i++) {
            Players.Player player = playerList.get(i);

            homePosition = (int) Math.round(1 + (i * ((double)(BOARD_SIZE - 1) / playerList.size())));
            tailDiversion = (i == 0) ? BOARD_SIZE : (homePosition - 1 == 0 ? homePosition : homePosition - 1);
         //   System.out.println( "Number of players " + playerList.size() + "HOME Positon " + homePosition + " Tail Diversion " + tailDiversion + " " + player.toString() + " board = " + BOARD_SIZE);

            player.setHOME(homePosition);
            player.setTailDiversion(tailDiversion);
        }
    }



    private void initialize() {
        calculateHomeAndTailDiversion();
        for (Players.Player player : playerList) {
            playerPositions.put(player, player.getHOME());
            playerHome.put(player, player.getHOME());
            playerEnd.put(player, (player.getTailDiversion() + numberOfTailPositions));

        }
    }

    public void removePlayer(Players.Player player) {
        playerList.remove(player);
        playerPositions.remove(player);
        playerHome.remove(player);
        playerEnd.remove(player);
    }

    public void add(GameBoardObserver observer) {
        observers.add(observer);
    }

    public void detach(GameBoardObserver observer) {
        observers.remove(observer);
    }

    @Override
    public int getCurrentPosition(Players.Player player) {
        return playerPositions.getOrDefault(player, -1);

    }

    @Override
    public void setPlayerPosition(Players.Player player, int newPosition) {
        if (newPosition < BOARD_SIZE_WITH_TAILS) {
            playerPositions.put(player, newPosition);
        } else {
            throw new IndexOutOfBoundsException("Invalid position for player: " + newPosition);
        }
    }

    @Override
    public boolean isGameWon() {
        return gameWon;
    }


    @Override
    public void advance() {
        moves++;
        for (Players.Player player : playerList) {
            totalMoves ++;
            advancePlayer(player, shaker.shake());
            if (gameWon) break;  // Stop the game if someone wins

        }
    }

    private void advancePlayer(Players.Player player, int advance) {

        int currentPosition = getPlayerPosition(player);
        int HOME = playerHome.getOrDefault(player, 0);
        int END = playerEnd.getOrDefault(player, 0);
        boolean overflowed = playerOverflowed.getOrDefault(player, false);

        int candidateIndex = currentPosition + advance;


        if (HOME == 1) {
            // Normal range, no wrapping needed
            try {
                if (candidateIndex < END) {
                    onUnderflow(player, advance, candidateIndex);
                } else if (candidateIndex == END) {
                    onHomeEvent(player, advance, currentPosition, candidateIndex );
                } else if(!disableBounceEvent){
                    onBounce(player, advance, currentPosition, candidateIndex);
                } else if (disableBounceEvent){
                    if (candidateIndex >= END) {
                        onHomeEvent(player, advance, currentPosition, candidateIndex);
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        } else if (HOME > 1 && HOME < BOARD_SIZE) {
            // Wrapping case (e.g., 10 to 9 scenario)
            try {
                if (candidateIndex <= BOARD_SIZE) {
                    if (overflowed && candidateIndex > END) {
                        if (!disableBounceEvent) {
                            onBounce(player, advance, currentPosition, candidateIndex);
                        } else {
                            onHomeEvent(player, advance, currentPosition, candidateIndex);
                        }
                    } else if (candidateIndex == END && overflowed) {
                        onHomeEvent(player, advance, currentPosition, candidateIndex );
                    } else {
                        onUnderflow(player, advance, candidateIndex);
                    }
                } else if (candidateIndex == END && overflowed) {
                    onHomeEvent(player, advance, currentPosition, candidateIndex );
                } else if (!overflowed) {
                    onOverflow(player, advance, candidateIndex, HOME, END);
                } else if (candidateIndex > END) {
                    if (!disableBounceEvent) {
                        onBounce(player, advance, currentPosition, candidateIndex);
                    } else {
                        onHomeEvent(player, advance, currentPosition, candidateIndex);
                    }                }
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }

        }
    }

    private void onOverflow(Players.Player player, int advance, int candidateIndex, int HOME, int END) {

        int originalIndex = playerPositions.get(player);

        int newIndex = (candidateIndex > BOARD_SIZE) ? (candidateIndex % BOARD_SIZE) : candidateIndex;
        playerOverflowed.put(player, true);

        if (!disableHitEvent && checkHit(player, advance, originalIndex, newIndex)) {
            return;
        }

        if (newIndex < END) {
            playerPositions.put(player, newIndex);

            OverflowEvent event = new OverflowEvent(player, moves, advance, originalIndex, newIndex);
            notifyObservers(observer -> observer.onEvent(event));
        }
        else if (newIndex == END) {
            onHomeEvent(player, advance, originalIndex, candidateIndex );

        } else if (newIndex > END && newIndex < BOARD_SIZE) {
            onBounce(player, advance, originalIndex, newIndex);
        }
    }

    private void onUnderflow(Players.Player player, int advance, int newIndex) {
        int originalIndex = playerPositions.get(player);
        if (!disableHitEvent && checkHit(player, advance, originalIndex, newIndex)) {
            return;
        }

        playerPositions.put(player, newIndex);
        UnderflowEvent event = new UnderflowEvent(player, moves, advance, originalIndex, newIndex);
        notifyObservers(observer -> observer.onEvent(event));
    }
    private void onHomeEvent(Players.Player player,int advance, int currentPosition, int candidateIndex){
        HomeEvent event = new HomeEvent(player, moves, advance, currentPosition, candidateIndex, totalMoves);
        notifyObservers(observer -> observer.onEvent(event));
        gameWon = true;


    }

    private void onBounce(Players.Player player, int advance, int currentPosition, int candidateIndex) {
        if (disableBounceEvent) {
            return;  // Skip bounce event if it is disabled
        }

        int END = playerEnd.getOrDefault(player, -1);

        int overflowAmount = candidateIndex - END;
        int newIndex = END - overflowAmount;
        if (newIndex < 0) {
            newIndex = 0;
        }

        if (!disableHitEvent && checkHit(player, advance, currentPosition, newIndex)) {
            return; // Stop this player's turn immediately
        }
        playerPositions.put(player, newIndex);
        BounceEvent event = new BounceEvent(player, moves, advance, currentPosition, newIndex);
        notifyObservers(observer -> observer.onEvent(event));
    }

    private boolean checkHit(Players.Player player, int advance, int currentPosition, int candidateIndex) {
        if (disableHitEvent) {
            return false;  // Do nothing if hit event is disabled
        }

        // Iterate over the playerPositions to check for a collision
        for (Map.Entry<Players.Player, Integer> entry : playerPositions.entrySet()) {
            Players.Player otherPlayer = entry.getKey();
            int otherPosition = entry.getValue();

            // Check if the other player is not the current player, and if their position is the candidateIndex
            if (!otherPlayer.equals(player) && otherPosition == candidateIndex && candidateIndex != playerHome.getOrDefault(otherPlayer, 0)) {
                System.out.println("Collision Detected! " + player + " hit " + otherPlayer);
                setPlayerPosition(otherPlayer, playerHome.getOrDefault(otherPlayer, 0));

                playerPositions.put(player, candidateIndex);
                HitEvent event = new HitEvent(player, moves, advance, currentPosition, candidateIndex);
                notifyObservers(observer -> observer.onEvent(event));
                return true;
            }
        }
        return false;

    }



    private void notifyObservers(Consumer<GameBoardObserver> action) {
        observers.forEach(action);
    }
}
