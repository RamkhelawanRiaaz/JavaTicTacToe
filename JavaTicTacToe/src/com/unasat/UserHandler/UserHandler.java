package com.unasat.UserHandler;

import java.util.Scanner;

public class UserHandler {


    public static void AccountCheck(){
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

        System.out.println("UserHandler");

        System.out.println("Username");
        String userName = myObj.nextLine();

        System.out.println("Password");
        String Password = myObj.nextLine();
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

        System.out.println("GeboorteDatum");
        String GeboorteDatum = myObj.nextLine();

        System.out.println("Gaat u akkoord met onze community guidelines? (Ja/Nee)");
        String Guidelines = myObj.nextLine();
    }


}
