package Trello.models;

public class Card {
    private String id;
    private String name;
    private String description;
    private User user;

    public Card(String id, String name, String description, User user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    //{"id": "5da1583547c78c15a14kjsd8","name": "abcd@gmail.com"}
    @Override
    public String toString() {
        if (this.id == null) {
            return "";
        }
        StringBuilder sb=new StringBuilder();
        sb.append("{").append("id: ").append(id).append(", name:").append(name);

        if (description!=null)
            sb.append(", description : ").append(description);
        if (user!=null)
            sb.append(", user : ").append(user);
        sb.append("}");
        return sb.toString();
    }
}
