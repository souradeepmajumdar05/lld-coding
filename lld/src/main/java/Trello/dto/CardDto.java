package Trello.dto;

public class CardDto {
    private String id;
    private String name;
    private String description;
    private String assignedUserId;

    public CardDto(String id, String name, String description, String assignedUserId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.assignedUserId = assignedUserId;
    }

    public CardDto(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAssignedUserId() {
        return assignedUserId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAssignedUserId(String assignedUserId) {
        this.assignedUserId = assignedUserId;
    }
}
