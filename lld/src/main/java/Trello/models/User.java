package Trello.models;

import Trello.dto.UserDto;

public class User {
    private String userId;
    private String name;
    private String email;

    public User(String userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
    //{"id": "user1", "name":"Gaurav Chandak", "email": "gaurav@workat.tech"}


    @Override
    public String toString() {
        if(userId==null)
            return "";
        return "{" +
                "userId:'" + userId + '\'' +
                ", name:'" + name + '\'' +
                ", email:'" + email + '\'' +
                '}';
    }
}
