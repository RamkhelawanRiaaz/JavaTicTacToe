package com.unasat.ui;

import java.util.*;
import com.unasat.repository.GameResultRepository;

public class TicTacToe {

    static String[] ticTacToeBord;
    static String beurt;
    static String speler;
    static GameResultRepository gameResultRepository = new GameResultRepository(); // Initialize GameResultRepository

    static String[] checkWinner(String player1, String player2) {
        for (int a = 0; a < 8; a++) {
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
            if (line.equals("XXX")) {
                return new String[]{player1, player2}; // Winner, Loser
            } else if (line.equals("OOO")) {
                return new String[]{player2, player1}; // Winner, Loser
            }
        }

        for (int a = 0; a < 9; a++) {
            if (Arrays.asList(ticTacToeBord).contains(String.valueOf(a + 1))) {
                break;
            } else if (a == 8) {
                return new String[]{"draw", null}; // No loser in case of draw
            }
        }

        System.out.println(speler + " (" + beurt + ") is aan de beurt, voer een nummer voor een beschikbaar cel in:");
        return null;
    }

    static void printBoard() {
        System.out.println("|---|---|---|");
        System.out.println("| " + ticTacToeBord[0] + " | " + ticTacToeBord[1] + " | " + ticTacToeBord[2] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + ticTacToeBord[3] + " | " + ticTacToeBord[4] + " | " + ticTacToeBord[5] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + ticTacToeBord[6] + " | " + ticTacToeBord[7] + " | " + ticTacToeBord[8] + " |");
        System.out.println("|---|---|---|");
    }

    public static void Gamestart(String player1, String player2) {
        Navigation navigation = new Navigation();

        Scanner in = new Scanner(System.in);
        ticTacToeBord = new String[9];
        beurt = "X";
        speler = player1;
        String[] result = null;

        for (int a = 0; a < 9; a++) {
            ticTacToeBord[a] = String.valueOf(a + 1);
        }
        printBoard();

        System.out.println(player1 + " (X) speelt eerst. Voer een nummer op een cel te selecteren:");

        while (result == null) {
            int numInput = -1;
            boolean validInput = false;

            while (!validInput) {
                System.out.print("Voer een nummer in (1-9): ");
                try {
                    String input = in.next();
                    numInput = Integer.parseInt(input);
                    if (numInput > 0 && numInput <= 9) {
                        validInput = true;
                    } else {
                        System.out.println("Verkeerde invoer; voer een nummer tussen 1 en 9 in.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Verkeerde invoer; voer een nummer in (geen letters).");
                }
            }

            if (ticTacToeBord[numInput - 1].equals(String.valueOf(numInput))) {
                ticTacToeBord[numInput - 1] = beurt;
                if (beurt.equals("X")) {
                    beurt = "O";
                    speler = player2;
                } else {
                    beurt = "X";
                    speler = player1;
                }
                printBoard();
                result = checkWinner(player1, player2);
            } else {
                System.out.println("Deze cel is al bezet; voer een ander nummer in:");
            }
        }

        String winner = result[0];
        String loser = result[1];

        if (winner.equalsIgnoreCase("draw")) {
            System.out.println("Het is een draw! Dank u wel voor het spelen.");
        } else {
            System.out.println("Gefeliciteerd! " + winner + " heeft gewonnen.");
            System.out.println("De verliezer is: " + loser);
            boolean isInserted = gameResultRepository.storeGameResult(winner, loser);
            if (!isInserted) {
                System.out.println("Fout bij het opslaan van het spelresultaat.");
            }
            navigation.navigation_handler();

        }
        in.close();
    }
}


