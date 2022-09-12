package LibraryManagementSystem.service.strategy.search;

import LibraryManagementSystem.repository.BookCrudInterface;
import LibraryManagementSystem.repository.impl.BookRepository;
import LibraryManagementSystem.service.LibraryManagementStrategy;
import LibraryManagementSystem.service.strategy.LibraryManagementImpl;

import java.util.stream.Collectors;

public class SearchBook implements LibraryManagementStrategy {
    BookCrudInterface bookRepo;
    private SearchBook(){
        bookRepo= BookRepository.getInstance();
    }
    private static LibraryManagementStrategy INSTANCE=null;
    public static LibraryManagementStrategy getInstance(){
        if(SearchBook.INSTANCE==null)
        {
            SearchBook.INSTANCE=new SearchBook();
        }
        return SearchBook.INSTANCE;
    }
    @Override
    public String doOperation(String input) throws Exception {
        return bookRepo.getByBookId(Integer.valueOf(input))
                .stream()
                .map(s->String.valueOf(s))
                .collect(Collectors.joining("\n"));
    }
}
