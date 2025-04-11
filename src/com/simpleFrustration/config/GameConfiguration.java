package com.simpleFrustration.config;

import java.util.Scanner;

public class GameConfiguration {
    private final int numberOfDice;
    private final int numberOfPlayers;
    private final int boardSize;
    private final boolean disableCollision;
    private final boolean disableOvershoot;

    public GameConfiguration(Scanner scanner) {  // Take scanner as parameter

        this.numberOfDice = getInput(scanner, "How many dice do you want to play with? ", 1, 2, 0);
        this.numberOfPlayers = getInput(scanner, "Select number of players: ", 2, 4, 2);
        this.boardSize = getInput(scanner, "Select Game.Board size: ", 18, 36, 18);
        this.disableCollision = getInputBoolean(scanner, "Do you want to disable the Overshoot functionality? (true/false)", false);
        this.disableOvershoot = getInputBoolean(scanner, "Do you want to disable the bounce functionality? (true/false):", false);
    }

    // Getters
    public int getNumberOfDice() { return numberOfDice; }
    public int getNumberOfPlayers() { return numberOfPlayers; }
    public int getBoardSize() { return boardSize; }
    public boolean isDisableCollision() { return disableCollision; }
    public boolean isDisableOvershoot() { return disableOvershoot; }

    private static int getInput(Scanner scanner, String prompt, int minValue, int maxValue, int defaultValue) {
        System.out.print(prompt + " (" + minValue + " or " + maxValue + ", default: " + defaultValue + "): ");
        try {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Using default value: " + defaultValue);
                return defaultValue;
            }

            int value = Integer.parseInt(input);
            if (value >= minValue && value <= maxValue) {
                return value;
            } else {
                System.out.println("Value out of range. Using default: " + defaultValue);
                return defaultValue;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Using default value: " + defaultValue);
            return defaultValue;
        }
    }

    private static boolean getInputBoolean(Scanner scanner, String prompt, boolean defaultValue) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            System.out.println("Using default settings: " + defaultValue);
            return defaultValue;
        }

        if (input.equalsIgnoreCase("true")) return true;
        if (input.equalsIgnoreCase("false")) return false;

        System.out.println("Invalid input. Using default settings: " + defaultValue);
        return defaultValue;
    }
}