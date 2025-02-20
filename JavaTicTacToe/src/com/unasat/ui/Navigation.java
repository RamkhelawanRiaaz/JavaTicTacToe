package com.unasat.ui;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.unasat.repository.GebruikerRepository;
import com.unasat.repository.ScoreRepository;

public class Navigation {

    UserHandler userHandler = new UserHandler();
    ScoreRepository leaderboard = new ScoreRepository();
    GebruikerRepository gebruikerRepository = new GebruikerRepository();

//Navigatie menu frontend die geprint moet worden - Othniel
    public void print_navmenu()
    {
        if(UserHandler.Player1 == null){

            System.out.println("|---------------------|");
            System.out.println("|"+"2. Inloggen"+"|");
            System.out.println("|---------------------|");
            System.out.println("|"+"2. Registreren"+"|");
            System.out.println("|---------------------|");
            System.out.println("|"+"3. Leaderboard" +"|");
            System.out.println("|---------------------|");
        }
        else {

            System.out.println("|---------------------|");
            System.out.println("|"+"1. Spel starten"+"|");
            System.out.println("|---------------------|");
            System.out.println("|"+"2. Leaderboard" +"|");
            System.out.println("|---------------------|");
            System.out.println("|"+"3. Uitloggen" +"|");
            System.out.println("|---------------------|");
        }


    }

    //De juiste opties wijzen in de navigatie aan de hand van als de gebruiker is ingelogd of niet - Othniel
    public void navigation_handler()
    {
        UserHandler.VoltoideGames = gebruikerRepository.GetUserPlayedGames();
        UserHandler.Player2 = null;

        if(userHandler.Player1 != null){
            System.out.println("Ingelogd Als " + UserHandler.Player1);
            System.out.println("Aantal Voltoide Games: "+ UserHandler.VoltoideGames);
        }

        print_navmenu();
try{
        Scanner in = new Scanner(System.in);
        int input = in.nextInt();
        if(UserHandler.Player1 == null) {
            switch (input) {
                case 1:
                    userHandler.single_inloggen();
                    break;

                case 2:
                    userHandler.GebruikersRegistratie();
                    break;

                case 3:
                    leaderboard.get_leaderboard();
                    break;
                default:
                    System.out.println("Ongeldig Nummer");
                    navigation_handler();
                    break;
            }
        }else {
            switch (input) {
                case 1:
                    userHandler.single_inloggen();
                    break;

                case 2:
                    leaderboard.get_leaderboard();
                    break;
                case 3:
                    userHandler.log_out();
                    break;
                default:
                    System.out.println("Ongeldig Nummer");
                    navigation_handler();
                break;
            }
        }}
        catch (InputMismatchException e) {
        System.out.println("Ongeldige invoer. Voer een getal in.");
        navigation_handler();
    }

    }

}
