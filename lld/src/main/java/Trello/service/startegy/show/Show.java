package Trello.service.startegy.show;

import Trello.models.Board;
import Trello.repository.BoardRepository;
import Trello.repository.CardRepository;
import Trello.repository.ListRepository;
import Trello.service.TrelloStrategy;
import Trello.service.startegy.TrelloContextDriver;

import java.util.stream.Collectors;

import static Trello.Utility.Constants.*;

public class Show implements TrelloStrategy {
    private static Show INSTANCE=null;
    private Show(){
        boardRepository=BoardRepository.getInstance();
        listRepository=ListRepository.getInstance();
        cardRepository=CardRepository.getInstance();
    }
    public static TrelloStrategy getInstance(){
        if(Show.INSTANCE==null)
        {
            Show.INSTANCE=new Show();
        }
        return Show.INSTANCE;
    }
    BoardRepository boardRepository;
    ListRepository listRepository;
    CardRepository cardRepository;
    @Override
    public String doOperation(String input) {
        String[]inputArr=input.split(" ");
        if(inputArr.length==1){
            return getAllBoard();
        }
        else if(inputArr[1].equals(BOARD)){
            return getBoardById(inputArr[2]);
        }
        else if(inputArr[1].equals(LIST)){
            return getListById(inputArr[2]);
        }
        else if(inputArr[1].equals(CARD)){
            return getCardById(inputArr[2]);
        }
        return null;
    }

    private String getCardById(String cardId) {
        return cardRepository.getCard(cardId).toString();
    }

    private String getListById(String listId) {
        return listRepository.getList(listId).toString();
    }

    private String getBoardById(String boardId) {
        Board board=boardRepository.getBoard(boardId);
        if(board==null){
            return "Board "+boardId+" does not exist";
        }
        return  board.toString();
    }

    private String getAllBoard() {
        if (boardRepository.isEmpty())
            return "No Boards";
        StringBuilder output = new StringBuilder();
        output.append("[")
                .append(boardRepository.getAllBoards()
                        .stream().map(b -> String.valueOf(b))
                        .collect(Collectors.joining(",")))
                .append("]");
        return output.toString();
    }
}
