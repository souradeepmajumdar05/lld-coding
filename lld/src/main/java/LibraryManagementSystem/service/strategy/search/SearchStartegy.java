package LibraryManagementSystem.service.strategy.search;

import LibraryManagementSystem.service.LibraryManagementStrategy;

public class SearchStartegy implements LibraryManagementStrategy {
    private LibraryManagementStrategy searchBook;
    private LibraryManagementStrategy searchAuthor;
    private SearchStartegy(){
        searchBook=SearchBook.getInstance();
        searchAuthor=SearchAuthor.getInstance();
    }
    private static LibraryManagementStrategy INSTANCE=null;
    public static LibraryManagementStrategy getInstance() {
        if(SearchStartegy.INSTANCE==null)
        {
            SearchStartegy.INSTANCE=new SearchStartegy();
        }
        return SearchStartegy.INSTANCE;
    }

    @Override
    public String doOperation(String input) throws Exception {
        String[] inputArr = input.split(" ");
        if(inputArr[1].equals("book_id"))
        {
            return searchBook.doOperation(inputArr[2]);
        }
        else if(inputArr[1].equals("author_id"))
        {
            return searchAuthor.doOperation(inputArr[2]);
        }
        return null;
    }
}
