package battleships.players;

import battleships.field.Field;
import battleships.game.ShipLetters;
import battleships.location.Location;
import battleships.ship.Ship;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private int score = 0;
    public Field playerGrid;
    public Field oppGrid;

    List<ShipLetters> shipLetters = new ArrayList<>(3);

    public Player(int id) {

    }

    public Player(String name, int id, Field pField, Field oppField) {
        this.name = name;
        this.playerGrid = pField;
        this.oppGrid = oppField;
    }


    public boolean hasWon() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Location selectMove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Field getPlayerGrid() {
        return playerGrid;
    }

    public Player setPlayerGrid(Field playerGrid) {
        this.playerGrid = playerGrid;
        return this;
    }

    public Field getOppGrid() {
        return oppGrid;
    }

    public Player setOppGrid(Field oppGrid) {
        this.oppGrid = oppGrid;
        return this;
    }

    public String getAttackOutcome(Location location, Field oppGrid) {

        int row = location.getRow();
        int col = location.getCol();

        if (!checkHitMiss(oppGrid.gameBoard[row][col].getLabel())) {
            return "O";
        } else {
            return "X" + oppGrid.gameBoard[row][col].getLabel();
        }

    }

    // Was this location a miss?
    public boolean checkHitMiss(String gameBoard) {
        return shipLetters.contains(gameBoard) ? true : false;
    }

    public int calculateScore(Location guessedLoc, Field oppGrid) {

        int row = guessedLoc.getRow();
        int col = guessedLoc.getCol();
        if (playerGrid.gameBoard[row][col].isShipInLocation()) {
            int shipPoints = oppGrid.gameBoard[row][col].getShip().getPoints();
            score = score - shipPoints;
        }
        return score;
    }
}
