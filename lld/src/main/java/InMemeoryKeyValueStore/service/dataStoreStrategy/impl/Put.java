package InMemeoryKeyValueStore.service.dataStoreStrategy.impl;

import InMemeoryKeyValueStore.models.Key;
import InMemeoryKeyValueStore.models.Value;
import InMemeoryKeyValueStore.repository.DSInterface;
import InMemeoryKeyValueStore.repository.impl.DataStore;
import InMemeoryKeyValueStore.service.dataStoreStrategy.DataStoreStrategy;
import javafx.util.Pair;

public class Put implements DataStoreStrategy {
    DSInterface dataStore;
    public Put()
    {
        dataStore= DataStore.getInstace();
    }
    static DataStoreStrategy INSTANCE;
    public static DataStoreStrategy getInstance()
    {
        if(INSTANCE==null)
        {
            INSTANCE=new Put();
        }
        return INSTANCE;
    }
    @Override
    public String doOperation(String input) throws Exception {
        String[] inputArr = input.split(" ");
        Key key=new Key(inputArr[1]);
        Value value=new Value();
        for(int i=2;i<inputArr.length-1;i=i+2)
        {
            //check datatype

            Pair<String, String> pair=new Pair<>(inputArr[i], inputArr[i+1]);
            value.getValueMap().add(pair);
        }
        dataStore.save(key,value);
        return "Data added successfully";
    }
}
