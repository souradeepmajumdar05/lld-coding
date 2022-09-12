package LibraryManagementSystem.service.strategy.books;

import LibraryManagementSystem.models.BookCopy;
import LibraryManagementSystem.repository.BookCrudInterface;
import LibraryManagementSystem.repository.RackCrudInterface;
import LibraryManagementSystem.repository.impl.BookRepository;
import LibraryManagementSystem.repository.impl.LibraryRackRepository;
import LibraryManagementSystem.service.LibraryManagementStrategy;
import LibraryManagementSystem.service.strategy.LibraryManagementImpl;

import java.util.List;

public class RemoveBookCopy implements LibraryManagementStrategy {
    private RemoveBookCopy(){
        bookRepo= BookRepository.getInstance();
        libraryRackRepo= LibraryRackRepository.getInstace();
    }
    BookCrudInterface bookRepo;
    RackCrudInterface libraryRackRepo;
    private static LibraryManagementStrategy INSTANCE=null;
    public static LibraryManagementStrategy getInstance() {
        if(RemoveBookCopy.INSTANCE==null)
        {
            RemoveBookCopy.INSTANCE=new RemoveBookCopy();
        }
        return RemoveBookCopy.INSTANCE;
    }

    @Override
    public String doOperation(String input) throws Exception {
        String[] inputArr = input.split(" ");
        List<Integer> listOfBookID = bookRepo.getBookIdByBookCopyId(inputArr[1]);
        if(listOfBookID==null||listOfBookID.size()==0)
        {
            return "Invalid Book Copy ID";
        }
        List<BookCopy> listOfBookCoopy = bookRepo.getListOfBookCopy(listOfBookID.get(0));
        BookCopy tempBookCopy = null;
        String rackNumber = null;
        for (BookCopy bookCopy : listOfBookCoopy) {
            if (bookCopy.getUniqueId().equals(inputArr[1])) {
                //update rack
                if (libraryRackRepo.deleteById(bookCopy.getRackNumber())) {
                    rackNumber = bookCopy.getRackNumber();
                    tempBookCopy = bookCopy;
                    break;
                }
            }
        }
        //update bookcopy
        if (tempBookCopy != null) {
            listOfBookCoopy.remove(tempBookCopy);
            if(bookRepo.updateBookCopy(listOfBookID.get(0), listOfBookCoopy))
            {
                return "Removed book copy: " + inputArr[1] + " from rack: " + rackNumber;
            }
        }
        return "Nothing done to remove";
    }
}
