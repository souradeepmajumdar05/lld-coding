package LibraryManagementSystem.models;

import LibraryManagementSystem.Utility.Constants;

public class User {
    private String userId;
    private String name;
    private Integer maxAllowedBooks= Constants.maxAllowedBooks;

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public Integer getMaxAllowedBooks() {
        return maxAllowedBooks;
    }
}
