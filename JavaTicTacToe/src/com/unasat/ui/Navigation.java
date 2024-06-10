package com.unasat.ui;
import java.util.Scanner;

import com.unasat.repository.GebruikerRepository;
import com.unasat.repository.ScoreRepository;

public class Navigation {

    UserHandler userHandler = new UserHandler();
    ScoreRepository leaderboard = new ScoreRepository();
    GebruikerRepository gebruikerRepository = new GebruikerRepository();

    public void print_navmenu()
    {
        if(UserHandler.Player1 == null){

            System.out.println("|---------------------|");
            System.out.println("|"+"1. Login"+"|");
            System.out.println("|---------------------|");
            System.out.println("|"+"2. Start Game"+"|");
            System.out.println("|---------------------|");
            System.out.println("|"+"3. Register"+"|");
            System.out.println("|---------------------|");
            System.out.println("|"+"4. Leaderboard" +"|");
            System.out.println("|---------------------|");
        }
        else {

            System.out.println("|---------------------|");
            System.out.println("|"+"1. Start Game"+"|");
            System.out.println("|---------------------|");
            System.out.println("|"+"2. Leaderboard" +"|");
            System.out.println("|---------------------|");
            System.out.println("|"+"3. Uitloggen" +"|");
            System.out.println("|---------------------|");
        }


    }

    public void navigation_handler()
    {
        UserHandler.VoltoideGames = gebruikerRepository.GetUserPlayedGames();
        UserHandler.Player2 = null;

        if(userHandler.Player1 != null){
            System.out.println("Welkom " + UserHandler.Player1);
            System.out.println("Aantal Voltoide Games: "+ UserHandler.VoltoideGames);
        }

        print_navmenu();

        Scanner in = new Scanner(System.in);
        int input = in.nextInt();
        if(UserHandler.Player1 == null) {
            switch (input) {
                case 1, 2:
                    userHandler.single_inloggen();
                    break;

                case 3:
                    userHandler.GebruikersRegistratie();
                    break;

                case 4:
                    leaderboard.get_leaderboard();
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
            }
        }
    }

}
