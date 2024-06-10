import com.unasat.ui.UserHandler;
import com.unasat.ui.Navigation;


import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class Main {

    public static void main(String[] args) {
        Navigation navigation = new Navigation();
        Scanner myObj = new Scanner(System.in);
        System.out.println("welkom bij de TicTacToe game van Othniel, Riaaz, Rishika en Rishika.");
        System.out.println("Voer het getal in naar waar u wilt navigeren");

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.flush();
        navigation.navigation_handler();
    }

}