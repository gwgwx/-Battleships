package battleships.location;

import battleships.ship.Ship;

public class Location {

    private int row;
    private int col;
    private String label;
    private Ship ship;


    public Location(int row, int col, String label) {
        this.row = row;
        this.col = col;
        this.label = label;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public String getLabel() {
        return label;
    }

    public Location setLabel(String label) {
        this.label = label;
        return this;
    }

    public Ship getShip() {
        return ship;
    }

    public Location setShip(Ship ship) {
        this.ship = ship;
        return this;
    }

    public boolean isShipInLocation(){
        return ship != null;
    }

    @Override
    public String toString() {
        return "Location{" +
                "row=" + row +
                ", col=" + col +
                ", label='" + label + '\'' +
                ", ship=" + ship +
                '}';
    }
}
