package ristinolla;

import java.util.Scanner;

public class Board {
    private int[][] coords;
    private int width;
    private int height;
    private int inRow;
    private boolean firstTurn = true;
    
    public Board(int xMax, int yMax, int rows){
        coords = new int[yMax][xMax];
        width = xMax;
        height = yMax;
        inRow = rows;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    void drawBoard(){
        System.out.print("      ");
        for(int i = 0; i < width; i++){
            if(i > 8)
                System.out.print(i + 1 + " ");
            else
                System.out.print(i + 1 + "  ");
        }
        System.out.println();
        System.out.print("     ");
        for(int i = 0; i < width; i++){
            System.out.print("___");
        }
        System.out.println();

        // Prints the game board and the row numbers
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                int num = coords[y][x];

                switch (num){
                    case 0: if(x == 0 && y >= 9)
                                System.out.print(y + 1 + " |  ");
                            else if(x == 0)
                                System.out.print(y + 1 + "  |  ");
                            System.out.print("-  ");
                            break;
                    case 1: if(x == 0 && y >= 9)
                                System.out.print(y + 1 + " |  ");
                            else if(x == 0)
                                System.out.print(y + 1 + "  |  ");
                            System.out.print("X  ");
                            break;
                    case 2: if(x == 0 && y >= 9)
                                System.out.print(y + 1 + " |  ");
                            else if(x == 0)
                                System.out.print(y + 1 + "  |  ");
                            System.out.print("O  ");
                            break;
                }

            }
            System.out.println("|");
        }
        System.out.print("     ");
        for(int i = 0; i < width; i++){
            System.out.print("---");
        }
        System.out.println();
    }

    // places the X (players glyph) onto the board.
    private void placeX(int y, int x){
            coords[y][x] = 1;
    }

    // places the O (enemies glyph) onto the board.
    void placeO(int y, int x){
            coords[y][x] = 2;
    }
    
    void playerTurn(){
        int row;
        int column;
        
        while(true){
            System.out.println("Your turn!\n");
            row = readInt("Give row number: ");
            column = readInt("Give column number: ");
            System.out.println();
            if (tileAvailable(column - 1, row - 1)) {
                placeX(row - 1, column - 1);
                break;
            } else
                System.out.println("Can't place there!");

        }
    }

    void enemyTurn(){
        EnemyAi ai = new EnemyAi();
        if (firstTurn) {
            ai.start(coords, this);
            firstTurn = false;
        }
        else
            ai.playerInRow(coords, this, inRow);
    }

    // Checks the whole board if a winning move was made. Uses the counter method.
    boolean winningMove(int xo){
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                if(coords[y][x] == xo && counter(y, x, xo)){
                    return true;
                }
            }   
        }
        return false;
    }

    // Counts the consecutive Xs or Os, depending on the given xo parameter, to determine
    // whether a move was a winning move. Used in winningMove method.
    private boolean counter(int y, int x, int xo){
        int vertical = 0;
        int horizontal = 0;
        int diagonal1 = 0;
        int diagonal2 = 0;

        
        for(int i = 0; i < inRow; i++){
            try{
            if(coords[y+i][x+i] == xo)
                diagonal1++;
            } catch (Exception e) { break; }
        }
        
        for(int i = 0; i < inRow; i++){
            try{
            if (coords[y+i][x-i] == xo)
                diagonal2++;
            } catch (Exception e) { break; }
        }
        
        for(int i = 0; i < inRow; i++){
            try{
            if(coords[y+i][x] == xo)
                vertical++;
            } catch (Exception e) { break; }
        }
        
        for(int i = 0; i < inRow; i++){
            try{
            if(coords[y][x+i] == xo)
                horizontal++;
            } catch (Exception e) { break; }
        }
            
        return horizontal == inRow || vertical == inRow || diagonal1 == inRow || diagonal2 == inRow;
    }

    // Reads an integer value given by the player.
    int readInt(String str) {
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.print(str);
            try {
                int i = scan.nextInt();
                return i;
            } catch (Exception e) {
                System.out.println("Illegal move!");
            }
        }
    }


    // Checks is a given tile is available.
    boolean tileAvailable(int x, int y) {
        if (tileOnBoard(x, y))
            return coords[y][x] == 0;
        return false;
    }

    // Checks if a given tile is on the board.
    boolean tileOnBoard(int x, int y) {
        return x < width && x >= 0 && y < height && y >=0;
    }
}
