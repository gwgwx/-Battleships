package battleships.ship;

import battleships.game.ShipLetters;
import battleships.location.Location;

public class Submarine extends Ship {

    private static final int LENGTH = 1;
    private static final int POINTS = 3;

    public Submarine(ShipDirection dir, Location location) {
        super(POINTS, dir, location, ShipLetters.SUBMARINE, LENGTH);
    }

    public Submarine() {
        super(POINTS, ShipLetters.SUBMARINE, LENGTH);
    }

}
