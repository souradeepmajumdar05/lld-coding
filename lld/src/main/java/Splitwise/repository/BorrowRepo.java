package Splitwise.repository;

import Splitwise.models.BorrowData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BorrowRepo {
    //userid:List<BorrowData>
    private static ConcurrentHashMap<String, List<BorrowData>> borrowData =null;
    private static ConcurrentHashMap<String, List<BorrowData>> oweData =null;

    private static BorrowRepo INSTANCE=null;
    public static BorrowRepo getInstance() {
        if(BorrowRepo.INSTANCE==null){
            BorrowRepo.INSTANCE=new BorrowRepo();
        }
        return BorrowRepo.INSTANCE;
    }
    private BorrowRepo(){
        borrowData =new ConcurrentHashMap<>();
        oweData =new ConcurrentHashMap<>();
    }

    public Map<String, List<BorrowData>> getAll() {
        return borrowData;
    }

    public List<BorrowData> getForUserID(String userId) {
        return borrowData.get(userId);
    }

    public void addExpenseForUser(String lender, String borrower, Double splitAmount) {
        boolean contains=false;
        if(borrowData.get(lender)!=null){
            for(BorrowData borrowData:borrowData.get(lender)){
                if(borrowData.getUserId().contains(borrower)){
                    contains=true;
                    borrowData.setAmount(borrowData.getAmount()+splitAmount);
                    break;
                }
            }
            if(!contains){
                BorrowData borrow=new BorrowData(borrower,splitAmount);
                borrowData.get(lender).add(borrow);
            }
        }
        else{
            BorrowData borrow=new BorrowData(borrower,splitAmount);
            borrowData.put(lender,new ArrayList<BorrowData>(Arrays.asList(borrow)));
        }

    }
}
