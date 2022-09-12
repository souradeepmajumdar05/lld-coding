package SnakeAndLadder.models.game.board;
import SnakeAndLadder.models.game.board.*;

public class Board {
    private Laders laders;
    private Snakes snakes;
    private Integer totalCells;
    public Board(Laders laders, Snakes snakes, Integer totalCells) {
        this.laders = laders;
        this.snakes = snakes;
        this.totalCells = totalCells;
    }

    public Laders getLaders() {
        return laders;
    }

    public Snakes getSnakes() {
        return snakes;
    }

    public Integer getTotalCells() {
        return totalCells;
    }

    public Integer moveToNextPosition(Integer playerPosition,Integer dice){
        Integer nextPos;
        nextPos=playerPosition+dice;
        if(snakes.isSnakeStartPresnt(nextPos))
        {
            nextPos=snakes.getEndOfSnake(nextPos);
        }
        else if (laders.isLadderStartPresnt(nextPos))
        {
            nextPos=laders.getEndOfLadder(nextPos);
        }
        return nextPos;
    }
}
