package com.simpleFrustration.facade;

import java.util.Scanner;

public interface IFacade {
    void play(int numberOfDice, int numberOfPlayers, int boardSize, boolean disableCollision, boolean disableOvershoot, Scanner scanner);


}
