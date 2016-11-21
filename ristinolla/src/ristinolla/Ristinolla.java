package ristinolla;

public class Ristinolla {
    
    public static void main(String[] args) {
        boolean jatkuu = true;
        Menu menu = new Menu();
        menu.startMenu();
        
        while(jatkuu){
            InitGame init = new InitGame();
            Board board = new Board(init.getWidth(), init.getHeight(), init.getRows());
            Highscore score = new Highscore();
            board.drawBoard();

            while(true){
                board.playerTurn();
                if(board.winningMove(1)){
                    board.drawBoard();
                    System.out.println("You have won the game! Congratulations!");
                    score.writeToFile(init.getName(), true);
                    jatkuu = menu.endMenu();
                    break;
                } else {
                    board.enemyTurn();
                    if(board.winningMove(2)){
                        board.drawBoard();
                        System.out.println("You have lost!");
                        score.writeToFile(init.getName(), false);
                        jatkuu = menu.endMenu();
                        break;
                    }
                    board.drawBoard();
                }
            }
        }
    }
}
