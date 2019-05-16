package battleships.exceptions;

import battleships.game.Command;

public class MoveIsCommandException extends InvalidLocationException {

    private Command cmd;

    public MoveIsCommandException(String message) {
        super(message);
    }

    public MoveIsCommandException() {
        super("Be careful that's not a move!");
    }

    public void MoveIsCommandException(Command cmd) {
        this.cmd = cmd;
    }

    public Command getCommand() {
        return cmd;
    }

}
