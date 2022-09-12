package Trello.models;

import java.util.List;
import java.util.stream.Collectors;

public class TLists {
    private String id;
    private String name;
    private List<Card> cards;

    public TLists(String id, String name, List<Card> cards) {
        this.id = id;
        this.name = name;
        this.cards = cards;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
    // "lists"": [{"id":"5da1583547c78c15a1408df2", "name": "Mock Interviews - Applied"}


    public List<Card> getCards() {
        return cards;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{").append("id:'").append(id).append(", name:'").append(name);
        if (cards != null) {
            sb.append(", cards:").append(cards.stream().map(s -> String.valueOf(s)).collect(Collectors.joining(",")));
        }
        sb.append("}");
        return sb.toString();
    }
}
