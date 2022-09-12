package InMemeoryKeyValueStore.service.dataStoreStrategy.impl;

import InMemeoryKeyValueStore.repository.DSInterface;
import InMemeoryKeyValueStore.repository.impl.DataStore;
import InMemeoryKeyValueStore.service.dataStoreStrategy.DataStoreStrategy;

import java.util.stream.Collectors;

public class Search implements DataStoreStrategy {
    DSInterface dataStore;
    public Search()
    {
        dataStore= DataStore.getInstace();
    }
    static DataStoreStrategy INSTANCE;
    public static DataStoreStrategy getInstance()
    {
        if(INSTANCE==null)
        {
            INSTANCE=new Search();
        }
        return INSTANCE;
    }

    @Override
    public String doOperation(String input) {
        String[] inputArr = input.split(" ");
        return dataStore
                .search(inputArr[1], inputArr[2])
                .stream()
                .map(a -> String.valueOf(a.getKey()))
                .collect(Collectors.joining(","));
    }
}
