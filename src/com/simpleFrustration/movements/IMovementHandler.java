package com.simpleFrustration.movements;

import com.simpleFrustration.players.Player;

public interface IMovementHandler {
    void movementHandler(Player player, int advance, int originalIndex, int newIndex, int moves);
}
