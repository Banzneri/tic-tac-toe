package ristinolla;

import java.util.Comparator;

// Class used for keeping highscores. Implements Comparator so I can sort the list based on percentage.
public class Player implements Comparator<Player> {
    private String playerName;
    private int playerLosses;
    private int playerWins;
    private int percent;

    public Player() { }
    
    public Player(String name, int losses, int wins){
        playerName = name;
        playerWins = wins;
        playerLosses = losses;
    }
    
    String getName(){
        return playerName;
    }

    int getPercent(){
        return percent;
    }

    void setPercent(int score){
        percent = score;
    }

    int getWins() {
        return playerWins;
    }

    int getLosses() {
        return playerLosses;
    }

    public int compare(Player first, Player second) {
        return second.getPercent() - first.getPercent();
    }
}