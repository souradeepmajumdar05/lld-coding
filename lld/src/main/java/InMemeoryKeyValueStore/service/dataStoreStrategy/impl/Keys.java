package InMemeoryKeyValueStore.service.dataStoreStrategy.impl;

import InMemeoryKeyValueStore.repository.DSInterface;
import InMemeoryKeyValueStore.repository.impl.DataStore;
import InMemeoryKeyValueStore.service.dataStoreStrategy.DataStoreStrategy;

import java.util.stream.Collectors;

public class Keys implements DataStoreStrategy {
    DSInterface dataStore;
    public Keys()
    {
        dataStore= DataStore.getInstace();
    }
    static DataStoreStrategy INSTANCE;
    public static DataStoreStrategy getInstance()
    {
        if(INSTANCE==null)
        {
            INSTANCE=new Keys();
        }
        return INSTANCE;
    }
    @Override
    public String doOperation(String input) {
        String[] inputArr = input.split(" ");
        return dataStore.findAll()
                 .keySet()
                 .stream()
                 .sorted((o1, o2)->o1.getKey().compareTo(o2.getKey()))
                 .map(a -> String.valueOf(a.getKey()))
                 .collect(Collectors.joining(","));
    }
}
