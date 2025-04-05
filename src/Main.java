import java.util.Scanner;

public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    int numberOfDice = getInput(scanner, "How many dice do you want to play with? ", 1, 2, 1);
    int numberOfPlayers = getInput(scanner, "Select number of players: ", 2, 4, 4);
    int boardSize = getInput(scanner, "Select Board size: ", 18, 36, 36);
    boolean disableHitEvent = getInputBoolean(scanner, "Do you want to disable the Overshoot functionality? (true/false)", false);
    boolean disableBounceEvent = getInputBoolean(scanner, "Do you want to disable the bounce functionality? (true/false):", false);

    IFacade game = new GameFacade();
    game.play(numberOfDice, numberOfPlayers, boardSize, disableHitEvent, disableBounceEvent);

    scanner.close();
}


private static int getInput(Scanner scanner, String prompt, int minValue, int maxValue, int defaultValue) {
    System.out.println(prompt + minValue + " or " + maxValue);
    String input = scanner.nextLine().trim();
    if (input.isEmpty()) {
        System.out.println("No input provided. Using default value: " + defaultValue);
        return defaultValue;
    }

    if (scanner.hasNextInt()) {
        int value = scanner.nextInt();
        if (value >= minValue && value <= maxValue) {
            return value;
        } else {
            System.out.println("Invalid input. Using default value: " + defaultValue);
        }
    } else {
        System.out.println("Invalid input. Using default value: " + defaultValue);
        scanner.next(); // for invalid input
    }
    return defaultValue;
}

private static boolean getInputBoolean(Scanner scanner, String prompt, boolean defaultValue) {
    System.out.println(prompt);
    String input = scanner.nextLine().trim();
    if (input.isEmpty()) {
        System.out.println("No input provided. Using default value: " + defaultValue);
        return defaultValue;
    }
    if (scanner.hasNextBoolean()) {
        return scanner.nextBoolean();
    } else {
        System.out.println("Invalid input. Using default value: " + defaultValue);
        scanner.next();
    }
    return defaultValue;
}
