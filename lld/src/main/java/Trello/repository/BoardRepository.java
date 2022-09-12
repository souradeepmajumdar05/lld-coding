package Trello.repository;

import Trello.dto.BoardDto;
import Trello.models.Board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class BoardRepository {
    private static ConcurrentHashMap<String, BoardDto> boards;
    private BoardRepository(){
        boards=new ConcurrentHashMap<>();
        userRepository=UserRepository.getInstance();
        listRepository=ListRepository.getInstance();
        init();
    }
    private Map<Integer,String> idMap;
    private void init() {
        idMap=new HashMap<>();
        idMap.put(1,"5da1583ec25d2a7e246b0375");
        idMap.put(2,"5da1586caaaad00d9b2d7aa6");
    }

    private static BoardRepository INSTANCE;
    public static BoardRepository getInstance(){
        if (BoardRepository.INSTANCE==null){
            BoardRepository.INSTANCE=new BoardRepository();
        }
        return BoardRepository.INSTANCE;
    }
    UserRepository userRepository=null;
    ListRepository listRepository=null;
    public String saveBoard(BoardDto boardDto){
        if(boardDto.getId()==null)
        {
            String id=RepositoryUtility.getId(idMap);
            boardDto.setId(id);
            boards.put(id,boardDto);
            return id;
        }
        else {
            boards.put(boardDto.getId(),boardDto);
        }
        return "";
    }
    public BoardDto getBoardDto(String boardId){
        return boards.get(boardId);
    }

    public Board getBoard(String boardId){
        BoardDto boardDto=boards.get(boardId);
        if(boardDto==null)
        {
            return null;
        }
        return new Board(boardDto.getId(),boardDto.getName(),boardDto.getPrivacy(),boardDto.getUrl(),
                userRepository.getUsers(boardDto.getMembersUserId()),
                listRepository.getLists(boardDto.getListsId()));
    }
    public List<Board> getAllBoards(){
        return boards.keySet()
                     .stream()
                     .map(boardId ->{return getBoard(boardId);})
                     .collect(Collectors.toList());
    }

    public void deleteBoard(String boardId) {
        BoardDto boardDto=boards.get(boardId);
        listRepository.deleteLists(boardDto.getListsId());
        boards.remove(boardId);
    }


    //************Utlity section*************//
    public boolean isEmpty(){
        return RepositoryUtility.isEmpty(boards);//boards.size()>0?false:true;
    }
}
