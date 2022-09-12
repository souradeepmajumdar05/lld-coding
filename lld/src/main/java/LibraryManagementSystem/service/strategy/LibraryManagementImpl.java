package LibraryManagementSystem.service.strategy;

import LibraryManagementSystem.service.LibraryManagementStrategy;
import LibraryManagementSystem.service.strategy.books.*;
import LibraryManagementSystem.service.strategy.search.SearchStartegy;

import static InMemeoryKeyValueStore.Utility.Constants.INPUTERROR;
import static LibraryManagementSystem.Utility.Constants.*;

public class LibraryManagementImpl implements LibraryManagementStrategy {

    private LibraryManagementImpl(){}
    private static LibraryManagementStrategy INSTANCE=null;
    public static LibraryManagementStrategy getInstace() {
        if(LibraryManagementImpl.INSTANCE==null)
        {
            LibraryManagementImpl.INSTANCE=new LibraryManagementImpl();
        }
        return LibraryManagementImpl.INSTANCE;
    }
    LibraryManagementStrategy strategy;

    @Override
    public String doOperation(String input) throws Exception {
        String[] inputArr = input.split(" ");
        String output = null;
        if(inputArr[0].equals(CREATE_LIBRARY))
        {
            strategy=CreateLibrary.getInstance();
            output=strategy.doOperation(input);
        }
        else if(inputArr[0].equals(ADD_BOOK))
        {
            strategy= AddBook.getInstace();
            output=strategy.doOperation(input);
        }
        else if(inputArr[0].equals(SEARCH))
        {
            strategy= SearchStartegy.getInstance();
            output=strategy.doOperation(input);
        }
        else if(inputArr[0].equals(REMOVE_BOOK_COPY))
        {
            strategy= RemoveBookCopy.getInstance();
            output=strategy.doOperation(input);
        }
        else if(inputArr[0].equals(PRINT_BORROWED))
        {
            strategy=PrintBorrowed.getInstance();
            output=strategy.doOperation(input);
        }
        else if(inputArr[0].equals(BORROW_BOOK))
        {
            strategy= BorrowBook.getInstance();
            output=strategy.doOperation(input);
        }
        else if(inputArr[0].equals(RETURN_BOOK_COPY))
        {
            strategy= ReturnBookCopy.getInstance();
            output=strategy.doOperation(input);
        }
        else if(inputArr[0].equals(EXIT))
        {
            strategy=Exit.getInstace();
            output=strategy.doOperation(input);
        }
        else
        {
            return "Expected funtionality not givens as input : " + input;
        }
        return output;
    }
}
