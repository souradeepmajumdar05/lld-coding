package SnakeAndLadder.service;

import SnakeAndLadder.models.Player;
import SnakeAndLadder.models.game.board.Board;
import SnakeAndLadder.models.game.dice.Dice;

import java.util.*;

public class Game {
    private final Board board;
    private Dice dice;
    private final Map<Integer, Player> res;
    private int winnerCount;
    private final Queue<Player> playerQueue;
    private boolean playGame =true;
    public Game(List<Player> playerList, Board board, Dice dice) {
        this.board = board;
        this.dice = dice;
        this.res = new HashMap<>();
        this.playerQueue = new ArrayDeque<>(playerList);
    }
    public String play(){
        while(playGame){
            Player player=getPlayer();
            if(!checkPlayerReachedEnd(player))
            {
                int nextPosition=board.moveToNextPosition(player.getPosition(),dice.rollDice());
                statusPrint(player,nextPosition);
                player.setPosition(nextPosition);

                updateGameStatus(player);
            }
            else{
                stopGame();
            }
        }
        return results();
    }

    private void statusPrint(Player player, int nextPosition) {
        System.out.println(player.getPlayerName() +" moves from "+ player.getPosition()+" to "+nextPosition);
    }

    private void updateGameStatus(Player player) {
        if (checkPlayerReachedEnd(player)) {
            res.put(++winnerCount, player);
            if (playerQueue.size() == 1) {
                res.put(++winnerCount, playerQueue.poll());
            }
        } else playerQueue.add(player);
    }

    private boolean checkPlayerReachedEnd(Player player) {
        if(player==null)
            return true;
        return player.getPosition()==board.getTotalCells();
    }

    private Player getPlayer() {
        return playerQueue.poll();
    }

    public void stopGame(){
        playGame=false;
    }

    private String results() {
        return res.get(1).getPlayerName()+" wins";
    }
}
