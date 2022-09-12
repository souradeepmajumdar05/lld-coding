package InMemeoryKeyValueStore.service.dataStoreStrategy.impl;

import InMemeoryKeyValueStore.service.dataStoreStrategy.DataStoreStrategy;

public class Exit implements DataStoreStrategy {
    static DataStoreStrategy INSTANCE;
    public static DataStoreStrategy getInstance()
    {
        if(INSTANCE==null)
        {
            INSTANCE=new Exit();
        }
        return INSTANCE;
    }
    @Override
    public String doOperation(String input) {
         System.exit(0);
        return "exiting";
    }
}
