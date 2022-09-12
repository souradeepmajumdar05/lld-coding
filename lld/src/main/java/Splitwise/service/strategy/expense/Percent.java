package Splitwise.service.strategy.expense;

import Splitwise.repository.BorrowRepo;
import Splitwise.repository.UserRepo;
import Splitwise.service.SpliwiseServiceInterface;

import java.util.ArrayList;
import java.util.List;

import static Splitwise.Utility.Constants.EXACT;
import static Splitwise.Utility.Constants.PERCENT;

public class Percent implements SpliwiseServiceInterface {
    private static SpliwiseServiceInterface INSTANCE=null;
    public static SpliwiseServiceInterface getInstance() {
        if(Percent.INSTANCE==null){
            Percent.INSTANCE=new Percent();
        }
        return Percent.INSTANCE;
    }
    SpliwiseServiceInterface strategy=null;
    private Percent(){
        userRepo=UserRepo.getInstance();
        borrowRepo=BorrowRepo.getInstance();
    }
    UserRepo userRepo;
    BorrowRepo borrowRepo;
    @Override
    public String doOperation(String input) {
        String[] inputArr = input.split(" ");
        String user=inputArr[1];
        Integer amount=Integer.valueOf(inputArr[2]);
        Integer count=Integer.valueOf(inputArr[3]);
        List<String> users=new ArrayList<>();
        int j = 0;
        for(int i=4;i<inputArr.length-1;i++){
            users.add(inputArr[i]);
            if(inputArr[i+1].contains(PERCENT)){
                j=i+1;
                break;
            }
        }
        List<Integer> percentages=new ArrayList<>();
        for (int i=j+1;i<inputArr.length;i++){
            percentages.add(Integer.valueOf(inputArr[i]));
        }
        return percentExpense(user,amount,count,users,percentages);
    }

    private String percentExpense(String user, Integer amount, Integer count, List<String> users, List<Integer> percentages) {

        Integer totalAmmount = percentages.stream().map(x -> x).mapToInt(Integer::intValue).sum();
        if(100!=totalAmmount)
            return "Percentages provided is not adding upt 100";
        for(int i=0;i<count;i++){
            if(!user.equals(users.get(i))) {
                borrowRepo.addExpenseForUser(user, users.get(i), Double.valueOf(amount*percentages.get(i)/100));
            }
        }

        return "Expense added";
    }
}
