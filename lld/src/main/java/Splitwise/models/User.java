package Splitwise.models;

public class User {
    private String userId;
    private String name;
    private String email;
    private String mobileNumber;

    public User(String userId, String name, String email, String mobileNumber) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
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

    public String getMobileNumber() {
        return mobileNumber;
    }
}
