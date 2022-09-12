package LibraryManagementSystem.service.strategy.search;

import LibraryManagementSystem.repository.BookCrudInterface;
import LibraryManagementSystem.repository.impl.BookRepository;
import LibraryManagementSystem.service.LibraryManagementStrategy;
import LibraryManagementSystem.service.strategy.LibraryManagementImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchAuthor implements LibraryManagementStrategy {
    BookCrudInterface bookRepo;
    private SearchAuthor(){
        bookRepo= BookRepository.getInstance();
    }
    private static LibraryManagementStrategy INSTANCE=null;
    public static LibraryManagementStrategy getInstance(){
        if(SearchAuthor.INSTANCE==null)
        {
            SearchAuthor.INSTANCE=new SearchAuthor();
        }
        return SearchAuthor.INSTANCE;
    }
    @Override
    public String doOperation(String input) throws Exception {
        List<Integer> listOfBookId=bookRepo.getByAuthorName(input);
        List<String> output=new ArrayList<>();
        for(int i=0;i<listOfBookId.size();i++){
            output.addAll(bookRepo.getByBookId(listOfBookId.get(i)));
        }
        return output.stream().map(s -> String.valueOf(s)).collect(Collectors.joining("\n"));
    }
}
