package InMemeoryKeyValueStore.service.dataStoreStrategy.impl;

import InMemeoryKeyValueStore.models.Key;
import InMemeoryKeyValueStore.repository.DSInterface;
import InMemeoryKeyValueStore.repository.impl.DataStore;
import InMemeoryKeyValueStore.service.dataStoreStrategy.DataStoreStrategy;

public class Delete implements DataStoreStrategy {
    DSInterface dataStore;
    public Delete()
    {
        dataStore= DataStore.getInstace();
    }
    static DataStoreStrategy INSTANCE;
    public static DataStoreStrategy getInstance()
    {
        if(INSTANCE==null)
        {
            INSTANCE=new Delete();
        }
        return INSTANCE;
    }
    @Override
    public String doOperation(String input) {
        String[] inputArr = input.split(" ");
        dataStore.delete(new Key(inputArr[1]));
        return "key:"+inputArr[1]+" , removed successfully ";
    }
}
