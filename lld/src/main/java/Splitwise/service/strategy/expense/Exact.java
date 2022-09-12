package Splitwise.service.strategy.expense;

import Splitwise.repository.BorrowRepo;
import Splitwise.repository.UserRepo;
import Splitwise.service.SpliwiseServiceInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static Splitwise.Utility.Constants.EXACT;

public class Exact implements SpliwiseServiceInterface {
    private static SpliwiseServiceInterface INSTANCE=null;
    public static SpliwiseServiceInterface getInstance() {
        if(Exact.INSTANCE==null){
            Exact.INSTANCE=new Exact();
        }
        return Exact.INSTANCE;
    }
    SpliwiseServiceInterface strategy=null;
    private Exact(){
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
        int j = 0;
        for(int i=4;i<inputArr.length-1;i++){
            users.add(inputArr[i]);
            if(inputArr[i+1].contains(EXACT)){
                j=i+1;
                break;
            }
        }
        List<Double> amounts=new ArrayList<>();
        for (int i=j+1;i<inputArr.length;i++){
            amounts.add(Double.valueOf(inputArr[i]));
        }
        return exactExpense(user,amount,count,users,amounts);

    }

    private String exactExpense(String user, Double amount, Integer count, List<String> users, List<Double> amounts) {
        Double totalAmmount = amounts.stream().map(x -> x).mapToDouble(Double::doubleValue).sum();
        if(amount==totalAmmount)
            return "Amount provided and the splits are not adding up to be same, No action taken";
        for(int i=0;i<count;i++){
            borrowRepo.addExpenseForUser(user,users.get(i),amounts.get(i));
        }

        return "Expense added";
    }
}
