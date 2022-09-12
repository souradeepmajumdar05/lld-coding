package Splitwise.service.strategy.expense;

import Splitwise.repository.BorrowRepo;
import Splitwise.repository.UserRepo;
import Splitwise.service.SpliwiseServiceInterface;

import java.util.ArrayList;
import java.util.List;

public class Equal implements SpliwiseServiceInterface {
    private static SpliwiseServiceInterface INSTANCE=null;
    public static SpliwiseServiceInterface getInstance() {
        if(Equal.INSTANCE==null){
            Equal.INSTANCE=new Equal();
        }
        return Equal.INSTANCE;
    }
    SpliwiseServiceInterface strategy=null;
    private Equal(){
        userRepo= UserRepo.getInstance();
        borrowRepo= BorrowRepo.getInstance();
    }
    UserRepo userRepo;
    BorrowRepo borrowRepo;

    @Override
    public String doOperation(String input) {
        String[] inputArr = input.split(" ");
        String user=inputArr[1];
        Double amount=Double.valueOf(inputArr[2]);
        Integer count=Integer.valueOf(inputArr[3]);
        List<String> users=new ArrayList<>();
        for(int j=4;j<inputArr.length-1;j++){
            users.add(inputArr[j]);
        }
        equalExpense(user,amount,count,users);
        return "Expense added";
    }
    void equalExpense(String userId, Double amount, Integer count, List<String> users){
        Double splitAmount=Double.valueOf(amount/count);
        users.stream().forEach(u->{
            if (!userId.equals(u)) {
                borrowRepo.addExpenseForUser(userId, u, splitAmount);
            }
        });

    }
}
