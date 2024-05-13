import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import static com.unasat.UserHandler.UserHandler.*;
import static com.unasat.TicTacToe.TicTacToe.*;


public class Main {

    public static final String HackerGreen = "#149414";


    public static void main(String[] args) {

        Scanner myObj = new Scanner(System.in);
        System.out.println("welkom bij de 3x3 TicTacToe game van Othniel, Riaaz, Rishika en Rishika.");
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.flush();
        AccountCheck();
    }

}