package ristinolla;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.FileHandler;

class Highscore {

    // Writes the highscore values to a .txt file.
    void writeToFile(String name, boolean win) {
        String line;
        String newLine = null;
        List<String> arrayList = new ArrayList<>();
        
        try {
            FileReader fileReader = new FileReader("highscores.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                if (line.contains(name)){
                    String[] list = line.split(" ");
                    int wins = Integer.parseInt(list[1]);
                    int losses = Integer.parseInt(list[2]);
                    int newWins = win ? wins + 1 : wins;
                    int newLosses = !win ? losses + 1 : losses;
                    newLine = name + " " + newWins + " " + newLosses;
                    arrayList.add(newLine);
                }
                else arrayList.add(line);
            }
            bufferedReader.close();
            
            if (newLine == null)
                arrayList.add(name + " " + (win ? 1 : 0) + " " + (!win ? 1 : 0));

            FileWriter fileWriter = new FileWriter("highscores.txt");
            fileWriter = new FileWriter("highscores.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (String str : arrayList) {
                bufferedWriter.append(str);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Prints the highscore list and sorts it in descending order based on the percent of games won.
    void printHighscore() {
        String line;
        List<Player> playerList = new ArrayList<>();

        try {
            FileReader fr = new FileReader("highscores.txt");
            BufferedReader br = new BufferedReader(fr);

            while((line = br.readLine()) != null) {
                String[] list = line.split(" ");
                String name = list[0];
                int wins = Integer.parseInt(list[1]);
                int losses = Integer.parseInt(list[2]);
                int games = wins + losses;
                double percent = (double)wins/games*100;
                Player player = new Player(name, losses, wins);
                player.setPercent((int)percent);
                playerList.add(player);
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        Collections.sort(playerList, new Player());

        System.out.println();

        for (Player pl : playerList) {
            String winsLosses = " (" + pl.getWins() + "/" + pl.getLosses() + ")";
            System.out.println(pl.getName() + " " + pl.getPercent() + "%" + winsLosses);
        }
    }
}
