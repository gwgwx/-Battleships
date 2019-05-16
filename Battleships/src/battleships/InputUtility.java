/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleships;

import java.util.Scanner;

public class InputUtility {

    static Scanner input = new Scanner(System.in);

    private InputUtility() {
    }

    public static String getString() {
        String userString = input.nextLine();
        return userString;
    }

    public static int getNumber() {
        int userNumber = input.nextInt();
        return userNumber;
    }
}
