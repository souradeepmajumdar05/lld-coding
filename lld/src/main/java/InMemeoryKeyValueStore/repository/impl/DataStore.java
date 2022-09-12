package InMemeoryKeyValueStore.repository.impl;

import InMemeoryKeyValueStore.models.IndexObject;
import InMemeoryKeyValueStore.models.Key;
import InMemeoryKeyValueStore.models.Value;
import InMemeoryKeyValueStore.repository.DSInterface;
import InMemeoryKeyValueStore.service.dataStoreStrategy.DataStoreStrategy;
import InMemeoryKeyValueStore.service.dataStoreStrategy.impl.DataStoreImpl;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
* This class behaves like @Repository what you have in spring or springboot
* */
public class DataStore implements DSInterface {
    static DSInterface INSTANCE=null;
    DataStoreStrategy strategy;
    private DataStore() {
    }
    public static DSInterface getInstace() {
        if(DataStore.INSTANCE==null)
        {
            DataStore.INSTANCE=new DataStore();
        }
        return DataStore.INSTANCE;
    }

    //threadsafe in memory data store
    public static volatile Map<Key, Value> keyValueStore=new ConcurrentHashMap<>();
    public static volatile Map<String, String> dataTypeMap=new ConcurrentHashMap<>();
    public static volatile Map<IndexObject, List<Key>> indexMap=new ConcurrentHashMap<>();

    @Override
    public Value findOne(Key key) {
        return keyValueStore.getOrDefault(key,null);
    }

    @Override
    public List<Key> search(String attributeKey, String attributeValue) {
        IndexObject indexObjects = new IndexObject (Arrays.asList(attributeKey, attributeValue));
        return indexMap.get(indexObjects);
    }

    @Override
    public boolean save(Key key, Value listOfAttributePairs) throws Exception{
        DataStoreUtility.dataTypeChecker(listOfAttributePairs);
        DataStoreUtility.indexing(key, listOfAttributePairs);
        DataStore.keyValueStore.put(key,listOfAttributePairs);
        //need a generic indexing functionality
        return true;
    }

    @Override
    public void delete(Key key) {
        keyValueStore.remove(key);
        DataStoreUtility.indexCleanUp(key);
    }

    @Override
    public Map<Key, Value> findAll() {
        return keyValueStore;
    }
}
