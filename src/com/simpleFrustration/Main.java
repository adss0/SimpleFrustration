import com.simpleFrustration.config.GameConfiguration;
import com.simpleFrustration.facade.GameFacade;
import com.simpleFrustration.facade.IFacade;

import java.util.Scanner;

public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    // Pass it to configuration
    GameConfiguration config = new GameConfiguration(scanner);

    // Create and start game with configured parameters
    IFacade game = new GameFacade();
    game.play(
            config.getNumberOfDice(),
            config.getNumberOfPlayers(),
            config.getBoardSize(),
            config.isDisableCollision(),
            config.isDisableOvershoot(),
            scanner
    );

    scanner.close();
}
