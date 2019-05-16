package battleships.ship;

import battleships.game.ShipLetters;
import battleships.location.Location;

public class AircraftCarrier extends Ship {

    public static final int LENGTH = 5;
    private static final int POINTS = 5;
    
    

//    public AircraftCarrier(ShipDirection bearing, Location position) {
//        super(AircraftCarrier.NAME, bearing, position);
//
//        if (bearing == ShipDirection.HORRIZONTAL) {
//            for (int r = 0; r < LENGTH; r++) {
//                position.getLocation(position.getRow() + r, position.getCol());
//            }
//        } else if (bearing == ShipDirection.VERTICAL) {
//            for (int c = 0; c < LENGTH; c++) {
//                position.getLocation(position.getRow(), position.getCol() + c);
//            }
//        } else {
//            throw new InputMismatchException("Oups! Invalid bearing");
//        }
//    }

    public AircraftCarrier(ShipDirection dir, Location location) {
        super(POINTS, dir, location, ShipLetters.AIRCRAFT_CARRIER, LENGTH);
    }

    public AircraftCarrier() {
        super(POINTS,ShipLetters.AIRCRAFT_CARRIER, LENGTH);
    }
}
