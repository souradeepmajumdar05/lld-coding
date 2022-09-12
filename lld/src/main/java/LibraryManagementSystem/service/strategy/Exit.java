package LibraryManagementSystem.service.strategy;

import InMemeoryKeyValueStore.service.dataStoreStrategy.DataStoreStrategy;
import LibraryManagementSystem.service.LibraryManagementStrategy;

public class Exit implements LibraryManagementStrategy {
    private Exit(){}
    private static LibraryManagementStrategy INSTANCE=null;
    public static LibraryManagementStrategy getInstace() {
        if(Exit.INSTANCE==null)
        {
            Exit.INSTANCE=new Exit();
        }
        return Exit.INSTANCE;
    }

    @Override
    public String doOperation(String input) {
         System.exit(0);
        return "exiting";
    }
}
