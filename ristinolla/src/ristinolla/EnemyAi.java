package ristinolla;

import java.awt.*;
import java.util.*;
import java.util.List;

public class EnemyAi {


    // Checks how many the player has in a row, and makes a defensive move according to the max value.
    void playerInRow(int[][] coords, Board board, int inRow) {
        int max = 0;
        Coord bestMove = new Coord();


        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                int horizontal = 0;
                int vertical = 0;
                int diagonal = 0;
                int antiDiagonal = 0;
                if (coords[y][x] == 1) {

                    // Checks horizontal
                    for (int i = 0; i < inRow-1; i++) {
                        int xi = x + i;
                        int yi = y;
                        if (!board.tileOnBoard(xi, yi))
                            break;
                        if (coords[yi][xi] != 1)
                            break;

                        horizontal++;
                        if (horizontal >= max) {

                            if (board.tileAvailable(x - 1, y)) {
                                bestMove.x = x - 1;
                                bestMove.y = y;
                                max = horizontal;
                            } else if (board.tileAvailable(xi + 1, yi)) {
                                bestMove.x = xi + 1;
                                bestMove.y = yi;
                                max = horizontal;
                            }
                        }
                    }

                    // Checks vertical
                    for (int i = 0; i < inRow-1; i++) {
                        int xi = x;
                        int yi = y + i;
                        if (!board.tileOnBoard(xi, yi))
                            break;
                        if (coords[yi][xi] != 1)
                            break;

                        vertical++;
                        if (vertical >= max) {

                            if (board.tileAvailable(x, y - 1)) {
                                bestMove.y = y - 1;
                                bestMove.x = x;
                                max = vertical;
                            } else if (board.tileAvailable(xi, yi+1)) {
                                bestMove.y = yi + 1;
                                bestMove.x = xi;
                                max = vertical;
                            }
                        }
                    }

                    // Checks diagonal
                    for (int i = 0; i < inRow-1; i++) {
                        int xi = x + i;
                        int yi = y + i;
                        if (!board.tileOnBoard(xi, yi))
                            break;
                        if (coords[yi][xi] != 1)
                            break;

                        diagonal++;
                        if (diagonal >= max) {

                            if (board.tileAvailable(x - 1, y - 1)) {
                                bestMove.y = y - 1;
                                bestMove.x = x - 1;
                                max = diagonal;
                            } else if (board.tileAvailable(xi + 1, yi + 1)) {
                                bestMove.y = yi + 1;
                                bestMove.x = xi + 1;
                                max = diagonal;
                            }
                        }
                    }

                    // Checks antiDiagonal
                    for (int i = 0; i < inRow-1; i++) {
                        int xi = x - i;
                        int yi = y + i;
                        if (!board.tileOnBoard(xi, yi))
                            break;
                        if (coords[yi][xi] != 1)
                            break;

                        antiDiagonal++;
                        if (antiDiagonal >= max) {

                            if (board.tileAvailable(x + 1, y - 1)) {
                                bestMove.y = y - 1;
                                bestMove.x = x + 1;
                                max = antiDiagonal;
                            } else if (board.tileAvailable(xi - 1, yi + 1)) {
                                bestMove.y = yi + 1;
                                bestMove.x = xi - 1;
                                max = antiDiagonal;
                            }
                        }
                    }
                }
            }
        }
        board.placeO(bestMove.y, bestMove.x);
    }

    void enemyInRow(int[][] coords, Board board) {

    }


    // The start move of the enemy, checks where the player placed his mark, and randomly places his mark
    // somewhere next to it. Uses the placeNextRandom method.
    void start(int[][] coords, Board board) {
        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                if (coords[y][x] == 1) {
                    Coord coord = placeNextRandom(x, y, board);
                    board.placeO(coord.y, coord.x);
                }
            }
        }
    }

    // Returns the coordinates randomly chosen around the players first mark. Used in start method.
    private Coord placeNextRandom(int x, int y, Board board) {
        List<Coord> list = new ArrayList<>();
        Random rng = new Random();

        for (int dy = -1; dy < 2; dy++) {
            for (int dx = -1; dx < 2; dx++) {
                if (dy == 0 && dx == 0)
                    continue;
                if (x+dx < 0 || x+dx > board.getWidth() || y+dy < 0 || y+dy > board.getHeight())
                    continue;
                Coord coord = new Coord(x+dx, y+dy);
                list.add(coord);
            }
        }

        int index = rng.nextInt(list.size());
        return list.get(index);
    }
}
