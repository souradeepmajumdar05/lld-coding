package Trello.repository;

import Trello.dto.TListsDto;
import Trello.models.TLists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class ListRepository {
    private static ConcurrentHashMap<String, TListsDto> lists;

    private ListRepository() {
        lists = new ConcurrentHashMap<>();
        cardRepo = CardRepository.getInstance();
        init();
    }

    private Map<Integer, String> idMap;

    private void init() {
        idMap = new HashMap<>();
        idMap.put(1, "5da1583547c78c15a1408df2");
        idMap.put(2, "5da1583547c78c15a143hj34");
    }

    private static ListRepository INSTANCE;

    public static ListRepository getInstance() {
        if (ListRepository.INSTANCE == null) {
            ListRepository.INSTANCE = new ListRepository();
        }
        return ListRepository.INSTANCE;
    }

    CardRepository cardRepo = null;

    public String saveList(TListsDto tListsDto) {
        if (tListsDto.getId() == null) {
            String id = RepositoryUtility.getId(idMap);
            tListsDto.setId(id);
            lists.put(id, tListsDto);
            return id;
        } else {
            lists.put(tListsDto.getId(), tListsDto);
        }
        return "";
    }

    public TListsDto getListDto(String listId) {
        return lists.get(listId);
    }

    public TLists getList(String listId) {
        TListsDto tListsDto = lists.get(listId);
        return new TLists(tListsDto.getId(), tListsDto.getName(), cardRepo.getCards(tListsDto.getCardsId()));
    }

    public List<TLists> getLists(List<String> listOfListIds) {
        if (listOfListIds == null)
            return null;
        return listOfListIds.stream()
                .map(lidtId -> {
                    return getList(lidtId);
                })
                .collect(Collectors.toList());
    }

    public void deleteLists(List<String> listOfListId) {
        if (listOfListId == null || getLists(listOfListId) == null)
            return;
        getLists(listOfListId).stream()
                .forEach(listObj -> {
                    cardRepo.deleteCards(listObj.getCards());
                });
        listOfListId.stream()
                .forEach(listId -> {
                    lists.remove(listId);
                });
    }
}
