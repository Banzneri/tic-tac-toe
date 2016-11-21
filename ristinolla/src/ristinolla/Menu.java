package ristinolla;

import java.util.Scanner;

class Menu {
    private Highscore score = new Highscore();


    // Prints the start menu.
    void startMenu() {
        /*String welcome = "Welcome to ";
        System.out.println();
        for (int i = 0; i < welcome.length(); i++) {
            System.out.print(welcome.charAt(i));
            linger(300);
        }
        System.out.println();
        System.out.println(" _____ _     _____         _____          \n" +
                           "|_   _(_) __|_   _|_ _  __|_   _|__   ___ \n" +
                           "  | | | |/ __|| |/ _` |/ __|| |/ _ \\ / _ \\\n" +
                           "  | | | | (__ | | (_| | (__ | | (_) |  __/\n" +
                           "  |_| |_|\\___||_|\\__,_|\\___||_|\\___/ \\___|");
        linger(1000);*/

        while(true) {
            System.out.print("\n[P]lay a game | [S]how highscore | [Q]uit game > ");
            Scanner scan = new Scanner(System.in);
            String str = scan.next();
            if (str.equals("p")) { break; }
            else if (str.equals("q")) { System.exit(0); }
            else if (str.equals("s")) { score.printHighscore(); }
        }
    }

    // Prints the end menu, returns a boolean value depending on the players choice to continue or quit.
    boolean endMenu() {
        while(true) {
            System.out.print("\n[P]lay again | [S]how highscore | [Q]uit game > ");
            Scanner scan = new Scanner(System.in);
            String str = scan.next();
            if (str.equals("p"))
                return true;
            else if (str.equals("q"))
                return false;
            else if (str.equals("s"))
                score.printHighscore();


        }
    }

    // A utility method for Thread.sleep.
    void linger(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) { e.printStackTrace(); }
    }
}
