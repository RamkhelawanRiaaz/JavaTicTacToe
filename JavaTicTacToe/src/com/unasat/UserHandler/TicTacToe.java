package com.unasat.UserHandler;
import java.util.*;

public class TicTacToe {

    static String [] ticTacToeBord;
    static String beurt;

    static String checkWinner(){

        for(int a = 0; a < 8; a++){
            String line = null;

            switch (a) {
                case 0:
                    line = ticTacToeBord[0] + ticTacToeBord[1] + ticTacToeBord[2];
                    break;
                case 1:
                    line = ticTacToeBord[3] + ticTacToeBord[4] + ticTacToeBord[5];
                    break;
                case 2:
                    line = ticTacToeBord[6] + ticTacToeBord[7] + ticTacToeBord[8];
                    break;
                case 3:
                    line = ticTacToeBord[0] + ticTacToeBord[3] + ticTacToeBord[6];
                    break;
                case 4:
                    line = ticTacToeBord[1] + ticTacToeBord[4] + ticTacToeBord[7];
                    break;
                case 5:
                    line = ticTacToeBord[2] + ticTacToeBord[5] + ticTacToeBord[8];
                    break;
                case 6:
                    line = ticTacToeBord[0] + ticTacToeBord[4] + ticTacToeBord[8];
                    break;
                case 7:
                    line = ticTacToeBord[2] + ticTacToeBord[4] + ticTacToeBord[6];
                    break;
            }

            if(line.equals("XXX")){
                return " X";
            }else if(line.equals("OOO")){
                return  "O";
            }
        }

        for (int a = 0; a < 9; a++) {
            if (Arrays.asList(ticTacToeBord).contains(
                    String.valueOf(a + 1))) {
                break;
            }
            else if (a == 8) {
                return "draw";
            }
        }

        System.out.println(
                beurt + "'s turn; enter a slot number to place "
                        + beurt + " in:");
        return null;
    }

    static void printBoard()
    {
        System.out.println("|---|---|---|");
        System.out.println("| " + ticTacToeBord[0] + " | "
                + ticTacToeBord[1] + " | " + ticTacToeBord[2]
                + " |");
        System.out.println("|-----------|");
        System.out.println("| " + ticTacToeBord[3] + " | "
                + ticTacToeBord[4] + " | " + ticTacToeBord[5]
                + " |");
        System.out.println("|-----------|");
        System.out.println("| " + ticTacToeBord[6] + " | "
                + ticTacToeBord[7] + " | " + ticTacToeBord[8]
                + " |");
        System.out.println("|---|---|---|");
    }

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        ticTacToeBord = new String[9];
        beurt = "X";
        String winner = null;

        for (int a = 0; a < 9; a++) {
            ticTacToeBord[a] = String.valueOf(a + 1);
        }

        System.out.println("Welcome to 3x3 Tic Tac Toe.");
        printBoard();

        System.out.println(
                "X will play first. Enter a slot number to place X in:");

        while (winner == null) {
            int numInput;

            try {
                numInput = in.nextInt();
                if (!(numInput > 0 && numInput <= 9)) {
                    System.out.println(
                            "Invalid input; re-enter slot number:");
                    continue;
                }
            }
            catch (InputMismatchException e) {
                System.out.println(
                        "Invalid input; re-enter slot number:");
                continue;
            }

            if (ticTacToeBord[numInput - 1].equals(
                    String.valueOf(numInput))) {
                ticTacToeBord[numInput - 1] = beurt;

                if (beurt.equals("X")) {
                    beurt = "O";
                }
                else {
                    beurt = "X";
                }

                printBoard();
                winner = checkWinner();
            }
            else {
                System.out.println(
                        "Slot already taken; re-enter slot number:");
            }
        }

        if (winner.equalsIgnoreCase("draw")) {
            System.out.println(
                    "It's a draw! Thanks for playing.");
        }

        else {
            System.out.println(
                    "Congratulations! " + winner
                            + "'s have won! Thanks for playing.");
        }
        in.close();
    }
}
