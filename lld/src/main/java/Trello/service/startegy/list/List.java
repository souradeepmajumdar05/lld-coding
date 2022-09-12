package Trello.service.startegy.list;

import Trello.dto.BoardDto;
import Trello.dto.TListsDto;
import Trello.repository.BoardRepository;
import Trello.repository.ListRepository;
import Trello.service.TrelloStrategy;

import java.util.ArrayList;

import static Trello.Utility.Constants.*;

public class List implements TrelloStrategy {
    private static List INSTANCE = null;

    private List() {
        listRepository = ListRepository.getInstance();
        boardRepository = BoardRepository.getInstance();
    }

    public static TrelloStrategy getInstance() {
        if (List.INSTANCE == null) {
            List.INSTANCE = new List();
        }
        return List.INSTANCE;
    }

    ListRepository listRepository = null;
    BoardRepository boardRepository = null;

    @Override
    public String doOperation(String input) {
        String[] inputArr = input.split(" ");
        String output = null;
        if (inputArr[1].equals(CREATE)) {
            output = createList(inputArr[2], input.substring(input.lastIndexOf(inputArr[2])+inputArr[2].length(), input.length() - 1).trim());
        } else if (inputArr[2].equals(name)) {
            output = addNameToList(inputArr[1], input.substring(input.lastIndexOf(inputArr[2])+4, input.length() - 1).trim());
        }
        return output;
    }

    private String addNameToList(String listId, String listName) {
        TListsDto tListsDto=listRepository.getListDto(listId);
        if (tListsDto == null) {
            return "List: " + listId + " , doesnot exist";
        }
        tListsDto.setName(listName);
        listRepository.saveList(tListsDto);
        return "";
    }

    private String createList(String boardId, String listName) {
        BoardDto boardDto = boardRepository.getBoardDto(boardId);
        if (boardDto == null) {
            return "Board: " + boardId + " , doesnot exist";
        }
        String listId=listRepository.saveList(new TListsDto(listName));
        if (boardDto.getListsId() == null) {
            java.util.List<String> temp = new ArrayList<String>();
            temp.add(listId);
            boardDto.setListsId(temp);
            return "Created list : "+listId;
        } else {
            boardDto.getListsId().add(listId);
        }
        boardRepository.saveBoard(boardDto);
        return "";
    }
}
