package ristinolla;

import java.util.Scanner;

public class InitGame {
    private int width;
    private int height;
    private int rows;
    private String name;

    // Initializes the game board and player name. These values are then given to a Board object.
    public InitGame() {
        while(true) {
            try {
                Scanner scan = new Scanner(System.in);
                System.out.print("\nWhat is your name? ");
                name = scan.next();
                
                System.out.print("Choose the width of the board: ");
                width = scan.nextInt();
                
                System.out.print("Choose the height of the board: ");
                height = scan.nextInt();
                System.out.println();
                
                System.out.print("How many in row to win: ");
                rows = scan.nextInt();
                System.out.println();
                
            } catch (Exception e){
                System.out.println("\nGive real numbers!\n" + e);
                continue;
            }
            break;
       }
    }
    
    int getWidth() {
        return width;
    }
    
    int getHeight() {
        return height;
    }
    
    int getRows() {
        return rows;
    }
    
    String getName() {
        return name;
    }
}
