package battleships.ship;

import battleships.game.ShipLetters;
import battleships.location.Location;

public class Destroyer extends Ship {

    private static final int LENGTH = 2;
    private static final int POINTS = 3;

    public Destroyer(ShipDirection dir, Location location) {
        super(POINTS, dir, location, ShipLetters.DESTROYER, LENGTH);
    }

    public Destroyer() {
        super(POINTS, ShipLetters.DESTROYER, LENGTH);
    }

}
