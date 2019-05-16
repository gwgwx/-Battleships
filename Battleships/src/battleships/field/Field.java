package battleships.field;

import battleships.game.Randomizer;
import battleships.game.ShipLetters;
import battleships.location.Location;
import battleships.players.Player;
import battleships.ship.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Field {

    private int r;
    private int c;
    public Location[][] gameBoard;

    public static int NUM_OF_SHIPS = 7;
    List<String> ships = Arrays.asList(new String[]{"A", "A", "D", "D", "D", "S", "S"});

    public Field(int r, int c) {
        this.r = r;
        this.c = c;

        gameBoard = new Location[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                gameBoard[i][j] = new Location(i, j, ".");
            }
        }
    }


    public Location[][] placeShipRandomly() {

        for (String l : ships) {

            gameBoard = placeShip(createRandomShip(l));
        }
        return gameBoard;
    }

    public Ship createRandomShip(String letter) {
        Ship ship;
        do {
            int randomRow = Randomizer.nextInt(0, r);
            int randomCol = Randomizer.nextInt(0, c);
            ShipDirection randomDir = Randomizer.nextDirection();
            Location location = new Location(randomRow, randomCol, "");
            ship = createShip(letter, randomDir, location);
        } while (checkMarked(ship));

        return ship;
    }

    public Location[][] placeShip(Ship s) {

        int row = s.getLocation().getRow();
        int col = s.getLocation().getCol();
        int length = s.getLength();
        ShipDirection dir = s.getDir();

        if (dir.equals(ShipDirection.HORRIZONTAL)) {
            for (int i = col; i < col + length; i++) {
                try {
                    setShipLocation(s, gameBoard[row][i]);
                } catch (ArrayIndexOutOfBoundsException ar) {
                }
            }
        }

        if (dir.equals(ShipDirection.VERTICAL)) {
            for (int i = row; i < row + length; i++) {
                try {
                    setShipLocation(s, gameBoard[i][col]);
                } catch (ArrayIndexOutOfBoundsException ar) {

                }
            }
        }

        return gameBoard;
    }

    public void updateBoard(String result, Location location) {

        int row = location.getRow();
        int col = location.getCol();
        gameBoard[row][col].setLabel(result);
    }

    private void setShipLocation(Ship s, Location location) {
        try {
            location.setLabel(s.getLetter());
            s.getShipLocations().add(location);
        } catch (ArrayIndexOutOfBoundsException ar) {
            System.out.println("Your ship is too long!");
        }
    }

    public int getRow() {
        return r;
    }

    public int getCol() {
        return c;
    }

    public boolean equals(Field other) {
        return (this.getRow() == other.getRow()
                && this.getCol() == other.getCol());
    }


    @Override
    public String toString() {
        return ("Row = " + r + " Column = " + c);
    }


    public Ship createShip(String letter, ShipDirection dir, Location location) {

        Ship ship;

        if (letter.equals(ShipLetters.AIRCRAFT_CARRIER)) {

            ship = new AircraftCarrier(dir, location);

        } else if (letter.equals(ShipLetters.DESTROYER)) {

            ship = new Destroyer(dir, location);

        } else {
            ship = new Submarine(dir, location);
        }

        return ship;
    }


    public boolean checkMarked(Ship ship) {

        ShipDirection dir = ship.getDir();
        int length = ship.getLength();
        int col = ship.getLocation().getCol();
        int row = ship.getLocation().getRow();

        // Check if off grid - Horizontal
        if (dir.equals(ShipDirection.HORRIZONTAL)) {
            int checker = length + col;
            if (checker > 10) {
                return true;
            }
        }

        // Check if off grid - Vertical
        if (dir.equals(ShipDirection.VERTICAL)) {
            int checker = length + row;
            if (checker > 10) {
                return true;
            }
        }

        // Check if overlapping with another ship
        if (dir.equals(ShipDirection.HORRIZONTAL)) {
            // For each location a ship occupies, check if ship is already there
            for (int i = col; i < col + length; i++) {
                try {
                    if (hasShip(row, i)) {
                        return true;
                    }
                } catch (ArrayIndexOutOfBoundsException ex) {
                    return false;
                }
            }
        } else if (dir.equals(ShipDirection.VERTICAL)) {
            for (int i = row; i < row + length; i++) {
                try {
                    if (hasShip(i, col)) {
                        return true;
                    }
                } catch (ArrayIndexOutOfBoundsException ex) {
                    return false;
                }
            }
        }

        return false;
    }

    public boolean hasShip(int row, int col) {
        return gameBoard[row][col].isShipInLocation();
    }


}
