package Trello.service.startegy.card;

import Trello.Utility.Logger;
import Trello.dto.CardDto;
import Trello.dto.TListsDto;
import Trello.repository.CardRepository;
import Trello.repository.ListRepository;
import Trello.repository.UserRepository;
import Trello.service.TrelloStrategy;

import java.util.ArrayList;

import static Trello.Utility.Constants.*;

public class Card implements TrelloStrategy {
    private static Card INSTANCE=null;
    private Card(){
        cardRepository=CardRepository.getInstance();
        listRepository=ListRepository.getInstance();
        userRepository=UserRepository.getInstance();
    }
    public static TrelloStrategy getInstance(){
        if(Card.INSTANCE==null)
        {
            Card.INSTANCE=new Card();
        }
        return Card.INSTANCE;
    }
    CardRepository cardRepository;
    ListRepository listRepository;
    UserRepository userRepository;
    @Override
    public String doOperation(String input) {
        String[] inputArr = input.split(" ");
        String output = null;
        if (inputArr[1].equals(CREATE)) {
            output = createCard(inputArr[2],inputArr[3]);
        } else if (inputArr[2].equals(name)) {
            output = addNameToCart(inputArr[1], inputArr[3]);
        }
        else if (inputArr[2].equals(description)) {
            output = addDescriptionToCard(inputArr[1], input.substring(input.lastIndexOf(inputArr[2])+inputArr[2].length(),input.length()));
        }
        else if (inputArr[2].equals(ASSIGN)) {
            output = assignCard(inputArr[1], inputArr[3]);
        }
        else if (inputArr[2].equals(MOVE)) {
            output = moveCard(inputArr[1],  inputArr[3]);
        }
        else if (inputArr[2].equals(UNASSIGN)) {
            output = unAssignCard(inputArr[1]);
        }
        return output;
    }

    private String unAssignCard(String cardID) {
        CardDto cardDto=cardRepository.getCardDto(cardID);
        cardDto.setAssignedUserId(null);
        cardRepository.saveCard(cardDto);
        return "";
    }

    private String moveCard(String cardid, String listId) {
        TListsDto tListsDto=listRepository.getListDto(listId);
        if(tListsDto.getCardsId()==null){
            ArrayList<String> temp=new ArrayList<>();
            temp.add(cardid);
            tListsDto.setCardsId(new ArrayList<>());
            tListsDto.getCardsId().addAll(temp);
        }
        else{
            tListsDto.getCardsId().add(cardid);
        }
        listRepository.saveList(tListsDto);
        return "";
    }

    private String assignCard(String cardID, String emailId) {
        CardDto cardDto=cardRepository.getCardDto(cardID);
        cardDto.setAssignedUserId(userRepository.getUserIdByEmail(emailId));
        cardRepository.saveCard(cardDto);
        //Logger.printInfo("************** user:"+cardDto.getAssignedUserId()+"card :"+cardDto.getId());
        return "";
    }

    private String addDescriptionToCard(String cardID, String description) {
        CardDto cardDto=cardRepository.getCardDto(cardID);
        cardDto.setDescription(description);
        cardRepository.saveCard(cardDto);
        return "";
    }

    private String addNameToCart(String cardID, String cardName) {
        CardDto cardDto=cardRepository.getCardDto(cardID);
        cardDto.setName(cardName);
        cardRepository.saveCard(cardDto);
        return "";
    }

    private String createCard(String listId, String cardName) {
        String cardid = cardRepository.saveCard(new CardDto(cardName));
        TListsDto tListsDto=listRepository.getListDto(listId);
        if(tListsDto.getCardsId()==null){
            ArrayList<String> temp=new ArrayList<>();
            temp.add(cardid);
            tListsDto.setCardsId(new ArrayList<>());
            tListsDto.getCardsId().addAll(temp);

        }
        else{
            tListsDto.getCardsId().add(cardid);
        }
        listRepository.saveList(tListsDto);
        return "Created card: "+cardid;
    }
}
