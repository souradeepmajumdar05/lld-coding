package SnakeAndLadder.models.game.board;

import java.util.HashMap;
import java.util.Map;

public class Laders {
    private final Map<Integer, Integer> ladderStartEndPos;
    public Laders(Map<Integer, Integer> ladderStartEndPosip){
        ladderStartEndPos=new HashMap<Integer,Integer>();
        ladderStartEndPosip.putAll(ladderStartEndPosip);
    }
//    private static Laders INSTANCE=null;
//    public static Laders getInstance(Map<Integer, Integer> ladderStartEndPosInput){
//        if(Laders.INSTANCE==null)        {
//            Laders.INSTANCE=new Laders(ladderStartEndPosInput);
//        }
//        return Laders.INSTANCE;
//    }
    public boolean isLadderStartPresnt(int start){
        return ladderStartEndPos.get(start)!=null;
    }
    public int getEndOfLadder(int start){
        return ladderStartEndPos.get(start);
    }
}
