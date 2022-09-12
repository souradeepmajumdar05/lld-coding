package Trello.dto;

import java.util.List;

public class TListsDto {
    private String id;
    private String name;
    private List<String> cardsId;

    public TListsDto(String id, String name, List<String> cardsId) {
        this.id = id;
        this.name = name;
        this.cardsId = cardsId;
    }

    public TListsDto(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getCardsId() {
        return cardsId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCardsId(List<String> cardsId) {
        this.cardsId = cardsId;
    }
}
