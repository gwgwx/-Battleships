package battleships.game;

import battleships.field.Field;

import static battleships.field.Field.NUM_OF_SHIPS;

import battleships.location.Location;
import battleships.players.ComputerPlayer;
import battleships.players.HumanPlayer;
import battleships.players.Player;
import battleships.ship.AircraftCarrier;
import battleships.ship.Destroyer;
import battleships.ship.Ship;
import battleships.ship.ShipDirection;
import battleships.ship.Submarine;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Game {

    private static int row;
    private static int col;
    private final int choice1 = 1;
    private final int choice2 = 2;
    int swValue;
    private Ship ship;
    private String letterCommand;
    private String numberCommand;
    private final int[] start = new int[2];
    Scanner input = new Scanner(System.in);
    int aCounter = 2;
    int sCounter = 2;
    int dCounter = 3;

    private Player player1;
    private Player player2;


    public void init() {

        chooseFieldDimensions();
        player1 = playerSelection(choice1,new Field(row,col),new Field(row,col));
        player2 = playerSelection(choice2,new Field(row,col),new Field(row,col));

        if (player1 instanceof HumanPlayer) {
            gameplayDecision(player1);
        }

        if (player2 instanceof HumanPlayer) {
            gameplayDecision(player2);
        }
    }

    private void gameplayDecision(Player player) {
        if (playerDecision()) {
            humanPlayerSetup(player);
        } else {
            Location[][] gameBoard = player.playerGrid.placeShipRandomly();
            printGameBoard(row, col, gameBoard);
        }
    }

    private void humanPlayerSetup(Player player) {

        while (aCounter + dCounter + sCounter <= NUM_OF_SHIPS) {

            String letter = chooseShipType(aCounter, dCounter, sCounter);
            ShipDirection dir = enterShipDirection();
            Location location = enterBowCoordinates();

            ship = createShip(letter, dir, location);
            boolean checkMarked = player.playerGrid.checkMarked(ship);

            if (!checkMarked) {
                Location[][] gameBoard = player.playerGrid.placeShip(ship);
                printGameBoard(row, col, gameBoard);

                if (ship.getLetter().equals(ShipLetters.AIRCRAFT_CARRIER)) {
                    aCounter = aCounter - 1;

                } else if (ship.getLetter().equals(ShipLetters.DESTROYER)) {
                    dCounter = dCounter - 1;

                } else if (ship.getLetter().equals(ShipLetters.SUBMARINE)) {
                    sCounter = sCounter - 1;
                }

            } else {

                System.out.println("please try again!");
            }
        }
    }

    private void yourTurnMenu(String name) {

        System.out.println("============================");
        System.out.println(" PLAYER " + name + "your turn!");
        System.out.println("============================");

    }

    public void play() {

        boolean gameOver = false;
        Random rnd = new Random();
        int randomPlayer = rnd.nextInt(2);

        while (!gameOver) {
            //track who is going to play first randomly
            if (randomPlayer % 2 == 0) {
                attack(player1, player2);
            }

            if (randomPlayer % 2 == 1) {
                attack(player2, player1);
            }

        }
    }

    private void attack(Player player, Player opp) {

        yourTurnMenu(player.getName());
        Location guessedLoc = guess();
        String result = player.getAttackOutcome(guessedLoc,opp.playerGrid);
        opp.playerGrid.updateBoard(result, guessedLoc);
        player.oppGrid.updateBoard(result, guessedLoc);
        printGameBoard(row, col, player.oppGrid.gameBoard);
        int score = player.calculateScore(guessedLoc, opp.playerGrid);
        player.setScore(score);

    }

    public String showResult() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void playerMenu(int id) {

        System.out.println("============================");
        System.out.println("         PLAYER " + id);
        System.out.println("| 1 Human                  |");
        System.out.println("| 2 Computer:              |");
        System.out.println("| Select option:           |");
        System.out.println("============================");

    }

    private Player playerSelection(int id,Field playerField,Field oppField ) {

        boolean isValid;
        Player player = new Player(id);
        do {
            playerMenu(id);
            isValid = true;
            switch (input.nextInt()) {
                case 1:
                    System.out.println("Human player choose your name");
                    input = new Scanner(System.in);
                    String playerName = input.nextLine();
                    player = new HumanPlayer(playerName, id,playerField,oppField);
                    break;
                case 2:
                    System.out.println("Computer player");
                    player = new ComputerPlayer(id);
                    break;
                default:
                    System.err.println("Unrecognized option");
                    isValid = false;
                    break;
            }
        } while (!isValid);

        return player;
    }

    public void printGameBoard(int row, int col, Location[][] gameBoard) {

        System.out.println("\n" + " Board:");
        System.out.print("     ");
        for (int i = 0; i < 9; i++) {
            System.out.print(i + 1 + "    ");
        }
        for (int i = 0; i < col - 9; i++) {
            System.out.print(i + 10 + "   ");
        }
        System.out.print("\n   ");
        for (int i = 0; i < col; i++) {
            System.out.print("-----");
        }
        System.out.print("\n");
        for (int i = 0; i < row; i++) {
            System.out.print(" " + (char) (65 + i) + " | ");
            for (int j = 0; j < col; j++) {
                System.out.print(gameBoard[i][j].getLabel() + "    ");
            }
            System.out.println("\n");
        }

    }

    private Location guess() {

        int guessedRow = -1;
        int guessedCol = -1;
        Location guessedLocation = null;
        do {
            System.out.println("=================");
            System.out.println(" Enter Location:");
            System.out.println("=================");

            if (input.hasNext()) {
                checkCoordinates();
                guessedRow = start[0];
                guessedCol = start[1];
                guessedLocation = new Location(guessedRow, guessedCol, "");

            } else {
                input.next();
                continue;
            }

        } while ((guessedRow < 0 || guessedRow > row) && (guessedCol < 0 || guessedCol > col));

        return guessedLocation;

    }

    private void chooseFieldDimensions() {

        do {
            System.out.println("=================");
            System.out.println(" Rows of Field:");
            System.out.println("=================");

            if (input.hasNextInt())
                row = input.nextInt();
            else {
                input.next();
                continue;
            }

        } while (row < 10 || row > 15);

        do {
            System.out.println("=================");
            System.out.println("Columns of Field:");
            System.out.println("=================");

            if (input.hasNextInt())
                col = input.nextInt();
            else {
                input.next();
                continue;
            }

        } while (col < 10 || col > 15);

    }

    private void desicionMenu() {

        System.out.println("=================================");
        System.out.println("  Choose how to place the ships  ");
        System.out.println("| 1 Manually:                   |");
        System.out.println("| 2 Automatically:              |");
        System.out.println("| Select option:                |");
        System.out.println("=================================");

    }

    public boolean playerDecision() {

        boolean isValid = true;
        boolean manually = true;
        do {
            desicionMenu();
            switch (input.nextInt()) {
                case 1:
                    System.out.println("Manually it is!");
                    break;
                case 2:
                    System.out.println("Let's see what computer does!");
                    manually = false;
                    break;
                default:
                    System.err.println("Unrecognized option");
                    isValid = false;
                    break;
            }
        } while (!isValid);

        return manually;
    }

    private void shipsMenu(int aCounter, int dCounter, int sCounter) {

        System.out.println("===================================");
        System.out.println("           Ships available            ");
        System.out.println("| AircraftCarrier A: " + aCounter + "|");
        System.out.println("| Destroyer D: " + dCounter + "|");
        System.out.println("| Submarine S: " + sCounter + "|");
        System.out.println("===================================");

    }

    private String chooseShipType(int aCounter, int dCounter, int sCounter) {

        boolean isValid;
        String shipLetter = "";

        do {
            shipsMenu(aCounter, dCounter, sCounter);
            isValid = true;
            switch (input.next()) {
                case "A":
                    if (aCounter == 0) {
                        System.out.println("You placed all your AircraftCarriers");
                        isValid = false;
                        break;
                    }
                    shipLetter = ShipLetters.AIRCRAFT_CARRIER;
                    break;

                case "D":
                    if (dCounter == 0) {
                        System.out.println("You placed all your Destroyers");
                        isValid = false;
                        break;
                    }
                    shipLetter = ShipLetters.DESTROYER;
                    break;
                case "S":
                    if (sCounter == 0) {
                        System.out.println("You placed all your Submarines");
                        isValid = false;
                        break;
                    }
                    shipLetter = ShipLetters.SUBMARINE;
                    break;
                default:
                    System.err.println("Unrecognized option");
                    isValid = false;
                    break;
            }
        } while (!isValid);

        return shipLetter;
    }

    public ShipDirection enterShipDirection() {

        boolean isValid;
        ShipDirection shipDirection = ShipDirection.UNSET;

        do {
            directionMenu();
            isValid = true;
            switch (input.next()) {
                case "V":
                    shipDirection = ShipDirection.VERTICAL;
                    break;
                case "H":
                    shipDirection = ShipDirection.HORRIZONTAL;
                    break;
                default:
                    System.err.println("Unrecognized option");
                    isValid = false;
                    break;
            }
        } while (!isValid);

        return shipDirection;
    }

    private void directionMenu() {

        System.out.println("=================================");
        System.out.println("  Choose direction of the ships  ");
        System.out.println("| 1 Vertical    V:              |");
        System.out.println("| 2 Horrizontal H:              |");
        System.out.println("| Select option:                |");
        System.out.println("=================================");

    }

    private void coOrdinatesMenu() {

        System.out.println("=================================");
        System.out.println("Enter the bow start");
        System.out.println("For example A1");
        System.out.println("=================================");
    }

    public Location enterBowCoordinates() {

        Location location;
        do {
            coOrdinatesMenu();
            checkCoordinates();
            location = new Location(start[0], start[1], "");

        } while (location.isShipInLocation());

        return location;
    }

    private void checkCoordinates() {
        String bow = input.next();
        letterCommand = bow.substring(0, 1).toUpperCase();
        numberCommand = bow.substring(1);
        start[0] = convertLetterToInt(letterCommand);
        start[1] = convertStringToNumber(numberCommand);
    }

    public Ship createShip(String letter, ShipDirection dir, Location location) {

        if (letter.equals(ShipLetters.AIRCRAFT_CARRIER)) {

            ship = new AircraftCarrier(dir, location);

        } else if (letter.equals(ShipLetters.DESTROYER)) {

            ship = new Destroyer(dir, location);

        } else {
            ship = new Submarine(dir, location);
        }

        return ship;
    }

    public static int convertLetterToInt(String val) {
        boolean isValid;
        int toReturn = 26;
        do {
            isValid = true;
            switch (val) {
                case "A":
                    toReturn = 0;
                    break;
                case "B":
                    toReturn = 1;
                    break;
                case "C":
                    toReturn = 2;
                    break;
                case "D":
                    toReturn = 3;
                    break;
                case "E":
                    toReturn = 4;
                    break;
                case "F":
                    toReturn = 5;
                    break;
                case "G":
                    toReturn = 6;
                    break;
                case "H":
                    toReturn = 7;
                    break;
                case "I":
                    toReturn = 8;
                    break;
                case "J":
                    toReturn = 9;
                    break;
                case "K":
                    toReturn = 10;
                    break;
                case "L":
                    toReturn = 11;
                    break;
                case "M":
                    toReturn = 12;
                    break;
                case "N":
                    toReturn = 13;
                    break;
                case "O":
                    toReturn = 14;
                    break;
                default:
                    isValid = false;
                    break;
            }
        } while (!isValid);

        return toReturn;
    }

    public static int convertStringToNumber(String s) {
        int rowConvertion = 0;
        try {
            rowConvertion = Integer.parseInt(s);
            rowConvertion--;
            while (rowConvertion > row) {
                System.out.println("=================================");
                System.out.println("Not valid position");
                System.out.println("=================================");
            }
        } catch (NumberFormatException n) {
            System.out.println(n.toString());
        }

        return rowConvertion;
    }

    private void displayOptions() {
        System.out.println("============================");
        System.out.println("|      MENU SELECTION      |");
        System.out.println("============================");
        System.out.println("| Options:                 |");
        System.out.println("|        1. Open   1       |");
        System.out.println("|        2. Save   2       |");
        System.out.println("|        3. Help   3       |");
        System.out.println("|        4. Exit           |");
        System.out.println("============================");
        swValue = KeyInput.inInt(" Select option: ");

    }

    private void DisplaySelection() {

        boolean isValid;
        do {
            displayOptions();
            isValid = true;
            switch (input.nextInt()) {
                case 1:
                    System.out.println("You picked option 1");
                    break;
                case 2:
                    System.out.println("You picked option 2");
                    break;
                case 3:
                    System.out.println("You picked option 3");
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.err.println("Unrecognized option");
                    checkCoordinates();
                    break;
            }
        } while (!isValid);
    }
}
