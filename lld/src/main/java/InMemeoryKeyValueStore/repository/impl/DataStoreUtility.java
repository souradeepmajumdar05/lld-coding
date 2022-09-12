package InMemeoryKeyValueStore.repository.impl;

import InMemeoryKeyValueStore.Utility.Logger;
import InMemeoryKeyValueStore.models.IndexObject;
import InMemeoryKeyValueStore.models.Key;
import InMemeoryKeyValueStore.models.Value;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static InMemeoryKeyValueStore.Utility.Constants.DATA_TYPE_MISMATCH;

public class DataStoreUtility {

    public static void dataTypeChecker(Value listOfAttributePairs) throws Exception {
        for (Pair<String, String> temp : listOfAttributePairs.getValueMap()) {
            temp.getKey();
            String dataType = getDataType(temp.getValue());
            //Logger.printInfo(dataType);
            String dataTypeStored = DataStore.dataTypeMap.getOrDefault(temp.getKey(), null);
            if (dataTypeStored == null) {
                DataStore.dataTypeMap.put(temp.getKey(), dataType);
            } else {
                if (!dataTypeStored.equals(dataType)) {
                    throw new Exception(DATA_TYPE_MISMATCH);
                }
            }
        }
    }

    public static String getDataType(String key) {
        String s = null;
        Double d = null;
        Boolean b = null;
        Boolean flag=true;
        try {
            b = Boolean.parseBoolean(key.trim());
            //Logger.printInfo(key+" : "+b.toString());
        } catch (Exception e) {
            flag=false;
        }
        if (key.trim().toLowerCase().equals("true")||key.trim().toLowerCase().equals("false")) {
            return "Boolean";
        }
        try {
            d = Double.valueOf(key);
        } catch (Exception e) {

        }
        if (d != null) {
            return "Double";
        }
        return "String";

    }

    public static void indexing(Key key, Value listOfAttributePairs) {
        listOfAttributePairs
                .getValueMap()
                .stream()
                .forEach(pair -> {
                    List<String> temp = Arrays.asList(pair.getKey(), pair.getValue());
                    IndexObject indexObject = new IndexObject(temp);
                    if (DataStore.indexMap.containsKey(indexObject)) {
                        List<Key> tempList=DataStore.indexMap.get(indexObject);
                        tempList.add(key);
                        DataStore.indexMap.put(indexObject,tempList);
                    } else {
                        List<Key> tempList= new ArrayList<>();
                        tempList.add(key);
                        DataStore.indexMap.put(indexObject, tempList);
                    }
                });
    }

    public static void indexCleanUp(Key key) {

        for (IndexObject indexObject : DataStore.indexMap.keySet())
        {
            // search  for value
            List<Key> listOfKey = DataStore.indexMap.get(indexObject);
            Key k=new Key(key.getKey());
            if(listOfKey.contains(k))
            {
                listOfKey.remove(k);
                DataStore.indexMap.put(indexObject, listOfKey);
            }
        }
    }
}
