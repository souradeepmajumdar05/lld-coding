package Trello.service.startegy;

import Trello.service.startegy.board.Board;
import Trello.service.startegy.card.Card;
import Trello.service.startegy.list.List;
import Trello.service.startegy.show.Show;
import Trello.service.TrelloStrategy;

import static Splitwise.Utility.Constants.EXPENSE;
import static Trello.Utility.Constants.*;

public class TrelloContextDriver implements TrelloStrategy {
    private static TrelloStrategy INSTANCE = null;

    private TrelloContextDriver() {
    }

    public static TrelloStrategy getInstance() {
        if (TrelloContextDriver.INSTANCE == null) {
            TrelloContextDriver.INSTANCE = new TrelloContextDriver();
        }
        return TrelloContextDriver.INSTANCE;
    }

    TrelloStrategy strategy;

    @Override
    public String doOperation(String input) {
        String[] inputArr = input.split(" ");
        String output = null;
        if (inputArr[0].equals(SHOW)) {
            strategy = Show.getInstance();
            output = strategy.doOperation(input);
        } else if (inputArr[0].equals(BOARD)) {
            strategy = Board.getInstance();
            output = strategy.doOperation(input);
        } else if (inputArr[0].equals(LIST)) {
            strategy = List.getInstance();
            output = strategy.doOperation(input);
        } else if (inputArr[0].equals(CARD)) {
            strategy = Card.getInstance();
            output = strategy.doOperation(input);
        } else {
            return "Expected funtionality not givens as input : " + input;
        }
        return output;
    }
}
