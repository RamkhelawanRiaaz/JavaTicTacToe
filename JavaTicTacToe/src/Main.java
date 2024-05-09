import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.io.Console;
public class Main {

    public static final String HackerGreen = "#149414";


    public static void main(String[] args) {

        Scanner myObj = new Scanner(System.in);
        System.out.println("Welkom bij TikTacToe, made by Rammy Group");
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.flush();
        AccountCheck();
    }


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

        System.out.println("Login");

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