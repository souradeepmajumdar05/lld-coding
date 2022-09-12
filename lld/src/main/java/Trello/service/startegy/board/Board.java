package Trello.service.startegy.board;

import Trello.dto.BoardDto;
import Trello.enums.Privacy;
import Trello.repository.BoardRepository;
import Trello.service.TrelloStrategy;

import java.util.ArrayList;
import java.util.Arrays;

import static Trello.Utility.Constants.*;

public class Board implements TrelloStrategy {
    private static Board INSTANCE=null;
    private Board(){
        boardRepo= BoardRepository.getInstance();
    }
    public static TrelloStrategy getInstance(){
        if(Board.INSTANCE==null)
        {
            Board.INSTANCE=new Board();
        }
        return Board.INSTANCE;
    }
    BoardRepository boardRepo;
    @Override
    public String doOperation(String input) {
        String[]inputArr=input.split(" ");
        String output = null;
        if(inputArr[1].equals(CREATE))
        {
            output=createBoard(inputArr[2]);
        }
        else if(inputArr[1].equals(DELETE))
        {
            output=deleteBoard(inputArr[2]);
        }
        else if(inputArr[2].equals(ADD_MEMBER))
        {
            output=addMemberToBoard(inputArr[1],inputArr[3]);
        }
        else if(inputArr[2].equals(REMOVE_MEMBER))
        {
            output=removeMemberFromBoard(inputArr[1],inputArr[3]);
        }
        else if(inputArr[2].equals(name))
        {
            output=addNameToBoard(inputArr[1],inputArr[3]);
        }
        else if(inputArr[2].equals(privacy))
        {
            output=addPrivacyToBoard(inputArr[1],inputArr[3]);
        }
        return output;
    }

    private String addPrivacyToBoard(String boardId, String privacy) {
        Trello.models.Board board=boardRepo.getBoard(boardId);
        board.setPrivacy(privacy.equals(Privacy.PRIVATE.name().toString())?Privacy.PRIVATE:Privacy.PUBLIC);
        boardRepo.saveBoard(new BoardDto(board.getId(),board.getName(),board.getPrivacy()));
        return "Privacy updated for Board : "+boardId;
    }

    private String addNameToBoard(String boardId, String name) {
        Trello.models.Board board=boardRepo.getBoard(boardId);
        board.setName(name);
        boardRepo.saveBoard(new BoardDto(board.getId(),board.getName(),board.getPrivacy()));
        return "Name updated for Board : "+boardId;
    }

    private String removeMemberFromBoard(String boardId, String userId) {
        BoardDto boardDto=boardRepo.getBoardDto(boardId);
        boardDto.getMembersUserId().remove(userId);
        return "Remove user :"+userId+" from Board : "+boardId;
    }

    private String addMemberToBoard(String boardId, String userId) {
        BoardDto boardDto=boardRepo.getBoardDto(boardId);
        if(boardDto.getMembersUserId()==null) {
            ArrayList<String> temp=new ArrayList<>();
            temp.add(userId);
            boardDto.setMembersUserId(new ArrayList<>());
            boardDto.getMembersUserId().addAll(temp);
        }
        else{
            boardDto.getMembersUserId().add(userId);
        }
        boardRepo.saveBoard(boardDto);
        return "Added user :"+userId+" to Board : "+boardId;
    }

    private String deleteBoard(String boardId) {
        if(boardRepo.getBoard(boardId)==null)
        {
            return "Board "+boardId+" does not exist";
        }
        boardRepo.deleteBoard(boardId);
        return "";
    }

    private String createBoard(String name) {
        return "Created board: "+boardRepo.saveBoard(new BoardDto(name));
    }
}
