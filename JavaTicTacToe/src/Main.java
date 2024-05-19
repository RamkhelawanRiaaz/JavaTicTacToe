import com.unasat.ui.UserHandler;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class Main {

    public static void main(String[] args) {
        UserHandler userHandler = new UserHandler();
        Scanner myObj = new Scanner(System.in);
        System.out.println("welkom bij de 3x3 TicTacToe game van Othniel, Riaaz, Rishika en Rishika.");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.flush();
        userHandler.accountCheck();
    }

}