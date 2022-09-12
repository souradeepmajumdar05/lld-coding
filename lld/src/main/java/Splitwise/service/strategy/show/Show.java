package Splitwise.service.strategy.show;

import Splitwise.models.BorrowData;
import Splitwise.repository.BorrowRepo;
import Splitwise.repository.UserRepo;
import Splitwise.service.SpliwiseServiceInterface;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Show implements SpliwiseServiceInterface {
    private static SpliwiseServiceInterface INSTANCE = null;

    public static SpliwiseServiceInterface getInstance() {
        if (Show.INSTANCE == null) {
            Show.INSTANCE = new Show();
        }
        return Show.INSTANCE;
    }

    private Show() {
        borrowRepo = BorrowRepo.getInstance();
        userRepo = UserRepo.getInstance();
    }

    BorrowRepo borrowRepo;
    UserRepo userRepo;

    @Override
    public String doOperation(String input) {
        String[] inputArr = input.split(" ");
        if (inputArr.length == 1) {
            return printAllUser();
        } else if (inputArr.length == 2) {
            return printGivenUserDetails(inputArr[1]);
        }
        return null;
    }

    //User4 owes User1: 250
    public String printAllUser() {
        Map<String, List<BorrowData>> allData = borrowRepo.getAll();
        if (allData.size() == 0)
            return "No balances";
        StringBuilder output = new StringBuilder();
        AtomicInteger i= new AtomicInteger();
        allData.forEach((k, v) -> {
            output.append(
                    v.stream()
                            .map(s -> String.valueOf(userRepo.getUserNameById(s.getUserId()) + " owes " + userRepo.getUserNameById(k) + " : " + s.getAmount()))
                            .collect(Collectors.joining("\n")));
            if(i.getAndIncrement()<allData.size()-1)
            {
                output.append("\n");
            }
        });

        return output.toString();
    }

    public String printGivenUserDetails(String userId) {
        List<BorrowData> listOfBorro = borrowRepo.getForUserID(userId);
        if (listOfBorro == null)
            return "No balances";
        StringBuilder output = new StringBuilder();
        output.append(
                listOfBorro.stream()
                        .map(s -> String.valueOf(userRepo.getUserNameById(s.getUserId()) + " owes " + userRepo.getUserNameById(userId) + " : " + s.getAmount()))
                        .collect(Collectors.joining("\n")));
        return output.toString();
    }

}
