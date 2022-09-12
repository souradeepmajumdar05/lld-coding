package Splitwise.service.strategy.expense;

import Splitwise.service.SpliwiseServiceInterface;

import java.util.ArrayList;
import java.util.List;

import static Splitwise.Utility.Constants.*;

public class Expense implements SpliwiseServiceInterface {
    private static SpliwiseServiceInterface INSTANCE = null;

    public static SpliwiseServiceInterface getInstance() {
        if (Expense.INSTANCE == null) {
            Expense.INSTANCE = new Expense();
        }
        return Expense.INSTANCE;
    }

    SpliwiseServiceInterface strategy = null;

    private Expense() {
    }

    @Override
    public String doOperation(String input) {
        if (input.contains(EQUAL)) {
            strategy = Equal.getInstance();
        } else if (input.contains(EXACT)) {
            strategy = Exact.getInstance();
        } else if (input.contains(PERCENT)) {
            strategy = Percent.getInstance();
        }
        return strategy.doOperation(input);
    }
}
