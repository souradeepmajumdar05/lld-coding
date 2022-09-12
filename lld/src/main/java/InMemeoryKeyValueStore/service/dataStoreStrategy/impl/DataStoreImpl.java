package InMemeoryKeyValueStore.service.dataStoreStrategy.impl;

import InMemeoryKeyValueStore.service.dataStoreStrategy.DataStoreStrategy;

import static InMemeoryKeyValueStore.Utility.Constants.*;

/*
* This class acts as the main driver
* But it has no clue on internal implementation
* Acts like a factory for sub servie call based on input function
* */
public class DataStoreImpl implements DataStoreStrategy {
    static DataStoreStrategy INSTANCE=null;
    DataStoreStrategy strategy;
    private DataStoreImpl() {
    }

    public static DataStoreStrategy getInstace() {
        if(DataStoreImpl.INSTANCE==null)
        {
            DataStoreImpl.INSTANCE=new DataStoreImpl();
        }
        return DataStoreImpl.INSTANCE;
    }

    @Override
    public String doOperation(String input) throws Exception{
        String[] inputArr = input.split(" ");
        String output;
        if(inputArr[0].equals(GET))
        {
            strategy = Get.getInstance();
            output=strategy.doOperation(input);
        }
        else if(inputArr[0].equals(PUT))
        {
            strategy = Put.getInstance();
            output=strategy.doOperation(input);
        }
        else if(inputArr[0].equals(DELETE))
        {
            strategy = Delete.getInstance();
            output=strategy.doOperation(input);
        }
        else if(inputArr[0].equals(SEARCH))
        {
            strategy = Search.getInstance();
            output=strategy.doOperation(input);
        }
        else if(inputArr[0].equals(KEYS))
        {
            strategy = Keys.getInstance();
            output=strategy.doOperation(input);
        }
        else if(inputArr[0].equals(EXIT))
        {
            strategy = Exit.getInstance();
            output=strategy.doOperation(input);
        }
        else {
            return INPUTERROR + input;
        }
        return output;
    }
}
