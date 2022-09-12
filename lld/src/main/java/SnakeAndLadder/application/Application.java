package SnakeAndLadder.application;

import SnakeAndLadder.Utility.Constants;
import SnakeAndLadder.Utility.Logger;
import SnakeAndLadder.models.Player;
import SnakeAndLadder.models.game.board.Board;
import SnakeAndLadder.service.Game;
import SnakeAndLadder.models.game.dice.NormalDice;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static ParkingLot.Utility.Constants.FILE_PARSING_ERROR;

public class Application {
    public static void main(String[] args) {
        HashMap<Integer, Integer> snakes = null;
        HashMap<Integer, Integer> laders = null;
        try {
            List<String> lines = Files.readAllLines(Paths.get(Constants.FILE_PATH));
            for (int i = 0; i < lines.size() - 2; i++) {
                if (snakes == null) {
                    snakes = new HashMap<>();
                    i = fillSnakes(i, snakes, lines);
                }
                if (laders == null) {
                    laders = new HashMap<>();
                    i = fillLadders(i, laders, lines);
                }

            }
            List<Player> playerList = new ArrayList<>();
            playerList.add(new Player(0, lines.get(lines.size() - 2)));
            playerList.add(new Player(0, lines.get(lines.size() - 1)));
            Game game = new Game(playerList, new Board(new SnakeAndLadder.models.game.board.Laders(laders), new SnakeAndLadder.models.game.board.Snakes(snakes), 100), new NormalDice());
            System.out.println(game.play());

        } catch (IOException e) {
            Logger.printError(FILE_PARSING_ERROR);
            e.printStackTrace();
        }
    }

    private static int fillLadders(int i, HashMap<Integer, Integer> laders, List<String> lines) {
        int totalLength = Integer.valueOf(lines.get(i));
        for (int j = i + 1; j <= totalLength; j++) {
            String[] lineArr = lines.get(j).split(" ");
            laders.put(Integer.valueOf(lineArr[0]), Integer.valueOf(lineArr[1]));
        }
        return i + totalLength+1;
    }

    private static int fillSnakes(int i, HashMap<Integer, Integer> snakes, List<String> lines) {
        int totalLength = Integer.valueOf(lines.get(i));
        for (int j = i + 1; j <= totalLength; j++) {
            String[] lineArr = lines.get(j).split(" ");
            snakes.put(Integer.valueOf(lineArr[0]), Integer.valueOf(lineArr[1]));
        }
        return i + totalLength+1;
    }
}
