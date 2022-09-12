package LibraryManagementSystem.repository.impl;

import LibraryManagementSystem.models.User;

import java.util.concurrent.ConcurrentHashMap;

public class UserRepository {
    public static ConcurrentHashMap<String, User> users;
    private static UserRepository INSTANCE = null;

    public static UserRepository getIstance() {
        if (UserRepository.INSTANCE == null) {
            UserRepository.INSTANCE = new UserRepository();
        }
        return UserRepository.INSTANCE;
    }
    private UserRepository(){
        users=new ConcurrentHashMap<>();
    }

    public void createUser() {
        users.put("user1", new User("user1", "user1"));
        users.put("user2", new User("user2", "user1"));
        users.put("user3", new User("user3", "user1"));
    }

    public Boolean containsUser(String userid) {
        if (users.get(userid) != null) {
            return true;
        }
        return false;
    }

}
