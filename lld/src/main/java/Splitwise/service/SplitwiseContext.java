package Splitwise.service;

import Splitwise.service.strategy.expense.Expense;
import Splitwise.service.strategy.show.Show;

import static Splitwise.Utility.Constants.EXPENSE;
import static Splitwise.Utility.Constants.SHOW;

public class SplitwiseContext implements SpliwiseServiceInterface{
    private static SpliwiseServiceInterface INSTANCE=null;
    public static SpliwiseServiceInterface getInstance() {
        if(SplitwiseContext.INSTANCE==null){
            SplitwiseContext.INSTANCE=new SplitwiseContext();
        }
        return SplitwiseContext.INSTANCE;
    }
    SpliwiseServiceInterface strategy=null;
    private SplitwiseContext(){}
    @Override
    public String doOperation(String input) {
        String[] inputArr = input.split(" ");
        String output = null;
        if(inputArr[0].equals(SHOW))
        {
            strategy= Show.getInstance();
            output=strategy.doOperation(input);
        }
        else if(inputArr[0].equals(EXPENSE))
        {
            strategy= Expense.getInstance();
            output=strategy.doOperation(input);
        }
        else
        {
            return "Expected funtionality not givens as input : " + input;
        }
        return output;
    }
}
