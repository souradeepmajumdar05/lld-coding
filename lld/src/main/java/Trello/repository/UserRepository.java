package Trello.repository;

import Trello.dto.UserDto;
import Trello.models.User;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class UserRepository {
    private static ConcurrentHashMap<String, UserDto> users;
    private static ConcurrentHashMap<String, String> userEmailIndexed;

    private UserRepository() {
        users = new ConcurrentHashMap<>();
        userEmailIndexed = new ConcurrentHashMap<>();
    }

    private static UserRepository INSTANCE;

    public static UserRepository getInstance() {
        if (UserRepository.INSTANCE == null) {
            UserRepository.INSTANCE = new UserRepository();
        }
        return UserRepository.INSTANCE;
    }

    public User getUser(String userId) {
        if(userId==null)
            return null;
        return new User(users.get(userId).getUserId(), users.get(userId).getName(), users.get(userId).getEmail());
    }

    public List<User> getUsers(List<String> listOfUserIds) {
        if (listOfUserIds == null)
            return null;
        return listOfUserIds.stream()
                .map(userId -> {
                    return getUser(userId);
                })
                .collect(Collectors.toList());
    }

    public void saveUser(UserDto userDto) {
        users.put(userDto.getUserId(), userDto);
    }

    //{"id": "user1", "name":"Gaurav Chandak", "email": "gaurav@workat.tech"}, {"id": "user3", "name": "Sagar Jain", "email":"sagar@workat.tech"}
    public void createUsers() {
        users.put("user1", new UserDto("user1", "Gaurav Chandak", "gaurav@workat.tech"));
        users.put("user2", new UserDto("user2", "Sagar Jain", "sagar@workat.tech"));
        users.put("user3", new UserDto("user3", "Sagar Jain copy", "copysagar@workat.tech"));
        users.keySet()
                .stream()
                .forEach(k -> {
                    userEmailIndexed.put(users.get(k).getEmail(), users.get(k).getUserId());
                });
    }

    public String getUserIdByEmail(String emailid) {
        return users.get(userEmailIndexed.get(emailid)).getUserId();
    }
}
