package InMemeoryKeyValueStore.service.dataStoreStrategy.impl;

import InMemeoryKeyValueStore.models.Key;
import InMemeoryKeyValueStore.models.Value;
import InMemeoryKeyValueStore.repository.DSInterface;
import InMemeoryKeyValueStore.repository.impl.DataStore;
import InMemeoryKeyValueStore.service.dataStoreStrategy.DataStoreStrategy;

public class Get implements DataStoreStrategy {
    DSInterface dataStore;
    private Get()
    {
        dataStore=DataStore.getInstace();
    }
    static DataStoreStrategy INSTANCE;
    public static DataStoreStrategy getInstance()
    {
        if(INSTANCE==null)
        {
            INSTANCE=new Get();
        }
        return INSTANCE;
    }
    @Override
    public String doOperation(String input) {
        String[] inputArr = input.split(" ");
        Value listOfPair = dataStore.findOne(new Key(inputArr[1]));
        return listOfPair==null?"No entry found for : "+input: listOfPair.toString();
    }
}
