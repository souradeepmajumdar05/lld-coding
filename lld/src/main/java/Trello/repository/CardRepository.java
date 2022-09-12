package Trello.repository;

import Trello.dto.CardDto;
import Trello.models.Card;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class CardRepository {
    private static ConcurrentHashMap<String, CardDto> cards;
    private CardRepository(){
        cards=new ConcurrentHashMap<>();
        userRepo=UserRepository.getInstance();
        init();
    }
    private Map<Integer,String> idMap;
    private void init() {
        idMap=new HashMap<>();
        idMap.put(1,"5da1583547c78c15a14kjsd8");
        //idMap.put(1,"5da1583547c78c15a1408df2");
        idMap.put(2,"5da1583547c78c15a14kj78g");
    }
    private static CardRepository INSTANCE;
    public static CardRepository getInstance(){
        if (CardRepository.INSTANCE==null){
            CardRepository.INSTANCE=new CardRepository();
        }
        return CardRepository.INSTANCE;
    }
    private UserRepository userRepo;
    public CardDto getCardDto(String cardId){
        return cards.get(cardId);
    }

    public Card getCard(String cardId){
        CardDto cardDto=cards.get(cardId);
        return new Card(cardDto.getId(),cardDto.getName(),cardDto.getDescription(),userRepo.getUser(cardDto.getAssignedUserId()));
    }
    public List<Card> getCards(List<String> listOfCardIds){
        if(listOfCardIds==null)
            return null;
        return listOfCardIds.stream()
                            .map(cardId->{return getCard(cardId);})
                            .collect(Collectors.toList());
    }

    public void deleteCards(List<Card> listOfCards) {
        listOfCards.stream()
                .forEach(card->{
                    cards.remove(card.getId());
                });
    }

    public String saveCard(CardDto cardDto) {
        if(cardDto.getId()==null){
            String id=RepositoryUtility.getId(idMap);
            cardDto.setId(id);
            cards.put(id,cardDto);
            return id;
        }
        else{
            cards.put(cardDto.getId(),cardDto);
        }
        return "";
    }
}
