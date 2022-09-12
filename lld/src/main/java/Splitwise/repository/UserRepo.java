package Splitwise.repository;

import Splitwise.models.User;
import Splitwise.service.SpliwiseServiceInterface;
import Splitwise.service.strategy.expense.Expense;

import java.util.concurrent.ConcurrentHashMap;

public class UserRepo {
    private static ConcurrentHashMap<String, User> userData=null;
    private static UserRepo INSTANCE=null;
    public static UserRepo getInstance() {
        if(UserRepo.INSTANCE==null){
            UserRepo.INSTANCE=new UserRepo();
        }
        return UserRepo.INSTANCE;
    }
    SpliwiseServiceInterface strategy=null;
    private UserRepo(){
        userData=new ConcurrentHashMap<>();
    }

    public void createUsers() {
        userData.put("u1",new User( "u1", "User1",  "user1@gmail.com",  "8888888888"));
        userData.put("u2",new User( "u2", "User2",  "user2@gmail.com",  "8888888889"));
        userData.put("u3",new User( "u3", "User3",  "user3@gmail.com",  "8888888880"));
        userData.put("u4",new User( "u4", "User4",  "user4@gmail.com",  "8888888881"));
        userData.put("u5",new User( "u5", "User5",  "user5@gmail.com",  "8888888882"));
    }
    public String getUserNameById(String userId){
        return userData.get(userId).getName();
    }

}
