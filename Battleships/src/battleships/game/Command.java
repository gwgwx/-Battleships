package battleships.game;

public class Command {

    private String commandString;
    private String helpText;

    public Command(String commandString, String helpText) {
        this.commandString = commandString;
        this.helpText = helpText;
    }

    public String getCommandString() {
        return commandString;
    }

    public void setCommandString(String commandString) {
        this.commandString = commandString;
    }

    public String getHelpText() {
        return helpText;
    }

    public void setHelpText(String helpText) {
        this.helpText = helpText;
    }

}
