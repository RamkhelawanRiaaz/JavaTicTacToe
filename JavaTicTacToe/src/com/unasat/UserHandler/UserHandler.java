package com.unasat.UserHandler;
import java.util.Scanner;
import static com.unasat.UserHandler.Backend.*;
import static com.unasat.UserHandler.Backend.SqlRegister;

public class UserHandler {


    public static void AccountCheck(){
        DbConnection();

        Scanner myObj = new Scanner(System.in);

        System.out.println("Heeft u nog geen account type Registreren | heeft u wel type Inloggen");
        String Isregistered = myObj.nextLine();

        if(Isregistered.equalsIgnoreCase("registreren")){
            GebruikersRegistratie();
        }
        else if(Isregistered.equalsIgnoreCase("inloggen")) {
            Inloggen();

        }
        else{
            System.out.println("Ongeldige invoer probeer opnieuw");
            System.out.flush();
            AccountCheck();
        }

    }


    public static void Inloggen(){
        Scanner myObj = new Scanner(System.in);
        System.out.println("Player 1 Login");

        System.out.println("Username");
        String userName = myObj.nextLine();

        System.out.println("Password");
        String Password = myObj.nextLine();

        String result = SqlLogin( userName , Password);

        if (result != "Failed"){
            String player1 = result;

            System.out.println("Player 2 Login");

            System.out.println("Username");
            String userName2 = myObj.nextLine();

            System.out.println("Password");
            String Password2 = myObj.nextLine();

            String resultuser2 = SqlLogin( userName2 , Password2);

            if (resultuser2 != "Failed"){
                String player2 = resultuser2;
            }
            else {
                Inloggen();
            }



        }else {
            Inloggen();
        }



    }



    public static void GebruikersRegistratie(){
        System.out.flush();
        Scanner myObj = new Scanner(System.in);

        System.out.println("Registreer je nu!");

        System.out.println("Voornaam");
        String Voornaam = myObj.nextLine();

        System.out.println("Achternaam");
        String Achternaam = myObj.nextLine();

        System.out.println("Gebruikersnaam");
        String Gebruikersnaam = myObj.nextLine();

        System.out.println("Password");
        String Password = myObj.nextLine();

        System.out.println("GeboorteDatum - YYY-MM-DD");
        String GeboorteDatum = myObj.nextLine();

        System.out.println("Gaat u akkoord met onze community guidelines? (Ja/Nee)");
        String Guidelines = myObj.nextLine();

        var InsertQuery = SqlRegister( Voornaam , Achternaam , Gebruikersnaam , Password , GeboorteDatum);

        if(InsertQuery){
            Inloggen();
        }else {
            System.out.println("Er was een issue bij inserten please try again");
            GebruikersRegistratie();

        }
    }


}