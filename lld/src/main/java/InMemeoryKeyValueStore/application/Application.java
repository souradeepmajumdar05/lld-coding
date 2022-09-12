package InMemeoryKeyValueStore.application;

import InMemeoryKeyValueStore.Utility.Constants;
import InMemeoryKeyValueStore.Utility.Logger;
import InMemeoryKeyValueStore.service.dataStoreStrategy.DataStoreStrategy;
import InMemeoryKeyValueStore.service.dataStoreStrategy.impl.DataStoreImpl;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static InMemeoryKeyValueStore.Utility.Constants.FILE_PARSING_ERROR;

public class Application {

    public static void main(String[] args) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(Constants.FILE_PATH));
            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).trim().length() > 0) {
                    DataStoreStrategy dataStoreStrategy = DataStoreImpl.getInstace();
                    try {
                        System.out.println(dataStoreStrategy.doOperation(lines.get(i)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            Logger.printError(FILE_PARSING_ERROR);
            e.printStackTrace();
        }
    }
}
