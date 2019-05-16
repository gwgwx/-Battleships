package battleships.ship;

public enum ShipDirection {

    UNSET("UNSET"),
    HORRIZONTAL("H"),
    VERTICAL("V");

    private String dir;

    private ShipDirection(String s) {
        dir = s;
    }

    public static ShipDirection fromString(String s) {
        if (s != null) {
            for (ShipDirection d : ShipDirection.values()) {
                if (d.toString().equals(s)) {
                    return d;
                }
            }
        }
        return ShipDirection.UNSET;
    }

}
