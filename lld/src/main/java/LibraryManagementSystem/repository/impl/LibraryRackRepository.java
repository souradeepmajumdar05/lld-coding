package LibraryManagementSystem.repository.impl;

import LibraryManagementSystem.models.BookCopy;
import LibraryManagementSystem.repository.RackCrudInterface;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class LibraryRackRepository implements RackCrudInterface {
    //rack can have at max one book
    //rack no : bookCopy Unique id
    private static ConcurrentHashMap<String, String> racks = null;

    private LibraryRackRepository() {
        LibraryRackRepository.racks= new ConcurrentHashMap<String, String>();
    }

    private static RackCrudInterface INSTANCE=null;
    public static RackCrudInterface getInstace() {
        if (LibraryRackRepository.INSTANCE==null)
        {
            LibraryRackRepository.INSTANCE=new LibraryRackRepository();
        }
        return LibraryRackRepository.INSTANCE;
    }
    @Override
    public Integer count() {
        return null;
    }

    @Override
    public Boolean delete(BookCopy entity) {
        return null;
    }

    @Override
    public Boolean deleteAll() {
        return null;
    }

    @Override
    public Boolean deleteById(String id) {
        if (!racks.get(id).equals("")) {
            racks.put(id, "");
            return true;
        }
        return false;
    }

    @Override
    public Boolean existsById(String id) {
        return null;
    }

    @Override
    public <T> List<T> findAll() {
        return null;
    }

    @Override
    public <T> T findById(String id) {
        return null;
    }

    @Override
    public void createRacks(Integer count) {
        LibraryRackRepository.racks = new ConcurrentHashMap<String, String>();
        for (int i = 0; i < count; i++) {
            racks.put(String.valueOf(i+1), new String());
        }

    }

    @Override
    public String save(List<BookCopy> listOfBookCopy) {
        StringBuilder posSavedIn=new StringBuilder();
        int i=0;
        for (String rackPos : LibraryRackRepository.racks.keySet()) {
            String bookCopyUniqueId = LibraryRackRepository.racks.get(rackPos);
            if(bookCopyUniqueId.equals("")){
                if(i<listOfBookCopy.size())
                {
                    posSavedIn.append(rackPos).append(",");
                    listOfBookCopy.get(i).setRackNumber(rackPos);
                    LibraryRackRepository.racks.put(rackPos,listOfBookCopy.get(i++).getUniqueId());
                }
            }
        }
        if(i==0)
        {
            return "Rack not available";
        }
        if(i<listOfBookCopy.size()-1)
        {
            return "Added Book to racks: "+i +" copies added , Rack not available";
        }
        else
        {
            return "Added Book to racks: "+posSavedIn.toString().substring(0,posSavedIn.toString().length()-1);
        }
    }
}
