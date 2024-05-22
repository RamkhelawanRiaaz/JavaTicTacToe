package com.unasat.ui;

import java.util.Scanner;

import com.unasat.repository.ScoreRepository;
import com.unasat.ui.UserHandler;

public class Navigation {
    UserHandler userHandler = new UserHandler();
    ScoreRepository leaderboard = new ScoreRepository();


    public void print_navmenu()
    {
        System.out.println("|---------------------|");
        System.out.println("|"+"1. Start Game"+"|");
        System.out.println("|---------------------|");
        System.out.println("|"+"2. Register"+"|");
        System.out.println("|---------------------|");
        System.out.println("|"+"3. Leaderboard" +"|");
        System.out.println("|---------------------|");
    }

    public void draw_leaderboard(){

    }

    public void navigation_handler()
    {

        print_navmenu();

        Scanner in = new Scanner(System.in);
        int input = in.nextInt();

        switch (input){
            case 1:
               userHandler.Inloggen();
            break;

            case 2:
                userHandler.GebruikersRegistratie();
            break;

            case 3:
                leaderboard.get_leaderboard();
            break;
    }


    }

}
