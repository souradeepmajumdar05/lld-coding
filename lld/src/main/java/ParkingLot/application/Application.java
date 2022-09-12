package ParkingLot.application;

import ParkingLot.Utility.Constants;
import ParkingLot.Utility.Logger;
import ParkingLot.service.strategy.ParkingServiceInterface;
import ParkingLot.service.strategy.ParkingServiceContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static ParkingLot.Utility.Constants.FILE_PARSING_ERROR;

public class Application {
    public static void main(String[] args)
    {
        try {
            List<String> lines = Files.readAllLines(Paths.get(Constants.FILE_PATH));
            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).trim().length() > 0) {
                    ParkingServiceInterface strategy = ParkingServiceContext.getInstance();
                    try {
                        System.out.println(strategy.doOperation(lines.get(i)));
                    } catch (Exception e) {
                        Logger.printError("input :"+lines.get(i));
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
