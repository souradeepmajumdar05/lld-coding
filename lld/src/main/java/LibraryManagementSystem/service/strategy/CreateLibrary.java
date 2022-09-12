package LibraryManagementSystem.service.strategy;

import LibraryManagementSystem.repository.RackCrudInterface;
import LibraryManagementSystem.repository.impl.LibraryRackRepository;
import LibraryManagementSystem.service.LibraryManagementStrategy;

public class CreateLibrary implements LibraryManagementStrategy {
    RackCrudInterface libraryRackRepository;
    private CreateLibrary(){
        libraryRackRepository=LibraryRackRepository.getInstace();
    }
    private static LibraryManagementStrategy INSTANCE=null;
    public static LibraryManagementStrategy getInstance() {
        if(CreateLibrary.INSTANCE==null)
        {
            CreateLibrary.INSTANCE=new CreateLibrary();
        }
        return CreateLibrary.INSTANCE;
    }

    @Override
    public String doOperation(String input) throws Exception {
        String[] inputArr = input.split(" ");
        libraryRackRepository.createRacks(Integer.valueOf(inputArr[1]));
        return "Created library with "+ inputArr[1]+" racks";
    }
}
