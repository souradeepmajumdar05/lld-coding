package SnakeAndLadder.models.game.board;

import java.util.HashMap;
import java.util.Map;

public class Snakes {
    private final Map<Integer, Integer> snakeStartEndPos;
    public Snakes(Map<Integer, Integer> snakeStartEndPosInput){
        snakeStartEndPos=new HashMap<Integer,Integer>();
        snakeStartEndPos.putAll(snakeStartEndPosInput);
    }
//    private static Snakes INSTANCE=null;
//    public static Snakes getInstance(Map<Integer, Integer> snakeStartEndPosInput){
//        if(Snakes.INSTANCE==null)        {
//            Snakes.INSTANCE=new Snakes(snakeStartEndPosInput);
//        }
//        return Snakes.INSTANCE;
//    }
    public boolean isSnakeStartPresnt(int start){
        return snakeStartEndPos.get(start)!=null;
    }
    public int getEndOfSnake(int start){
        return snakeStartEndPos.get(start);
    }
}
