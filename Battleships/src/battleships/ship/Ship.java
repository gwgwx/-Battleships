package battleships.ship;

import battleships.field.Field;
import battleships.game.ShipLetters;
import battleships.location.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Ship {

    private int points;
    private Field field;
    private ShipDirection dir;
    private Location location;
    private String letter;
    private int length;
    private boolean isHit;

    List<Location> shipLocations = new ArrayList<>();

    public Ship(int points, ShipDirection dir, Location location, String letter, int length) {
        this.points = points;
        this.dir = dir;
        this.location = location;
        this.letter = letter;
        this.length = length;
    }

    public Ship(int points, String letter, int length) {
        this.points = points;
        this.letter = letter;
        this.length = length;
    }

    public Location getLocation() {
        return location;
    }

    public List<Location> getShipLocations() {
        return shipLocations;
    }

    public Ship setShipLocations(List<Location> shipLocations) {
        this.shipLocations = shipLocations;
        return this;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public ShipDirection getDir() {
        return dir;
    }

    public void setDir(ShipDirection dir) {
        this.dir = dir;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void hit(Location position) {

        shipLocations.remove(position);
    }

    public boolean isHit() {
        return isHit;
    }

    public boolean isSinking() {

        if (shipLocations.isEmpty()) {

            return true;
        } else {

            return false;
        }

    }

    public void threaten() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
