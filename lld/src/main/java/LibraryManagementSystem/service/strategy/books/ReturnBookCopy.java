package LibraryManagementSystem.service.strategy.books;

import LibraryManagementSystem.models.BookCopy;
import LibraryManagementSystem.repository.BookCrudInterface;
import LibraryManagementSystem.repository.BorrowInterface;
import LibraryManagementSystem.repository.RackCrudInterface;
import LibraryManagementSystem.repository.impl.BookRepository;
import LibraryManagementSystem.repository.impl.BorrowRepository;
import LibraryManagementSystem.repository.impl.LibraryRackRepository;
import LibraryManagementSystem.repository.impl.UserRepository;
import LibraryManagementSystem.service.LibraryManagementStrategy;
import LibraryManagementSystem.service.strategy.LibraryManagementImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReturnBookCopy implements LibraryManagementStrategy {
    BorrowInterface borrowRepo = null;
    BookCrudInterface bookRepo = null;
    RackCrudInterface rackRepo = null;
    UserRepository userRepo = null;
    private ReturnBookCopy(){
        borrowRepo = BorrowRepository.getInstance();
        rackRepo = LibraryRackRepository.getInstace();
        bookRepo = BookRepository.getInstance();
        userRepo = UserRepository.getIstance();
    }
    private static LibraryManagementStrategy INSTANCE=null;
    public static LibraryManagementStrategy getInstance() {
        if(ReturnBookCopy.INSTANCE==null)
        {
            ReturnBookCopy.INSTANCE=new ReturnBookCopy();
        }
        return ReturnBookCopy.INSTANCE;
    }

    @Override
    public String doOperation(String input) throws Exception {
        String[] inputArr = input.split(" ");
        //;
        List<Integer> listOfBookCopyid=bookRepo.getBookIdByBookCopyId(inputArr[1]);
        if(listOfBookCopyid!=null)
        {
            List<BookCopy>listOfBookCopy= bookRepo.getListOfBookCopy(listOfBookCopyid.get(0));
            BookCopy tempBookCopy=null;
            BookCopy addBookCopy=null;
            for (BookCopy bookCopy:listOfBookCopy){
                if(bookCopy.getUniqueId().equals(inputArr[1])){

                    tempBookCopy=bookCopy;
                    bookCopy.setDueDate("");
                    bookCopy.setBorrowerUserId("");
                    rackRepo.save(new ArrayList<>(Arrays.asList(bookCopy)));
                    addBookCopy=bookCopy;
                    break;
                }
            }
            if (tempBookCopy!=null){
                listOfBookCopy.remove(tempBookCopy);
                listOfBookCopy.add(addBookCopy);
                bookRepo.save(bookRepo.getBookByBookId(addBookCopy.getBookId()),listOfBookCopy);
                return "Returned book copy " +addBookCopy.getUniqueId()+ " and added to rack: "+addBookCopy.getRackNumber();
            }
        }
        else{
            return "Book not registered to be returened";
        }

        return "";
    }
}
