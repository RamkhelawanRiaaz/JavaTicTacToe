package com.unasat.ui;
import java.util.Scanner;
import com.unasat.repository.*;
import static com.unasat.repository.dbconnection.DBConnection.*;




public class UserHandler {

    public static String Player1 = null;
    public static String Player2 = null;
    public static int VoltoideGames = 0;



    private final GebruikerRepository gebruikerRepository;

    public UserHandler() {
        this.gebruikerRepository = new GebruikerRepository();
    }

//Dit logt de ingelogte player uit en reset de players en voltoide games zodat de navigatie de juiste opties wijst - Othniel
    public void log_out(){
        Player1 = null;
        Player2 = null;
        VoltoideGames = 0;
        Navigation navigation = new Navigation();
        navigation.navigation_handler();
    }

//Dit is de login logica die nagaat als player 1 al is ingelogd en aan de hand van wie is ingelogd bij het starten vraagt het maar 1 of beide plauers om in te loggen. - Othniel

    public void single_inloggen(){

        //Player one login - Othniel
        if(Player1 == null){

        Scanner myObj = new Scanner(System.in);
        System.out.println("Player 1 Login");

        System.out.println("Username");
        String userName = myObj.nextLine();

        System.out.println("Password");
        String Password = myObj.nextLine();

        String result = gebruikerRepository.SqlLogin( userName , Password);

            if (result != "Failed") {
                Player1 = result;
                VoltoideGames = gebruikerRepository.GetUserPlayedGames();
                Navigation navigation = new Navigation();
                navigation.navigation_handler();

            } else {
                Navigation navigation = new Navigation();
                navigation.navigation_handler();
            }

        }

        //Player 2 Inloggen - Othniel
        if(Player2 == null){
            Scanner myObj = new Scanner(System.in);
            System.out.println("Player 2 Login");

            System.out.println("Username");
            String userName = myObj.nextLine();

            System.out.println("Password");
            String Password = myObj.nextLine();

            String result2 = gebruikerRepository.SqlLogin( userName , Password);

            if (result2 != "Failed") {
                Player2 = result2;
            } else {
                Navigation navigation = new Navigation();
                navigation.navigation_handler();
            }
        }
if (Player1 != null && Player2 != null){
    TicTacToe.Gamestart(Player1, Player2);
}
    }

    //Gebruiker registreren frontend - Othniel
    public void GebruikersRegistratie(){
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

        System.out.println("GeboorteDatum - *De nummers vast Aan elkaar in de volgorde JaarMaandDag");
        String GeboorteDatum = myObj.nextLine();

        var InsertQuery = gebruikerRepository.sqlRegister( Voornaam , Achternaam , Gebruikersnaam , Password , GeboorteDatum);

        if(InsertQuery){
            Navigation navigation = new Navigation();
            navigation.navigation_handler();

        }else {
            System.out.println("Er was een issue bij het toevoegen van de gebruiker");
            Navigation navigation = new Navigation();
            navigation.navigation_handler();

        }
    }


}
