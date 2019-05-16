package battleships.exceptions;

public class InvalidLocationException extends Exception {

    public InvalidLocationException(String message) {
        super(message);
    }

    public InvalidLocationException() {
        super("Oups.Wrong position!!!");
    }
}
