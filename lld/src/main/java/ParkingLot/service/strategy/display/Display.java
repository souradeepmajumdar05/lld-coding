package ParkingLot.service.strategy.display;

import ParkingLot.service.strategy.ParkingServiceContext;
import ParkingLot.service.strategy.ParkingServiceInterface;

import static ParkingLot.Utility.Constants.*;

public class Display implements ParkingServiceInterface {
    private static ParkingServiceInterface INSTANCE=null;
    public static ParkingServiceInterface getInstance() {
        if(Display.INSTANCE==null){
            Display.INSTANCE=new Display();
        }
        return Display.INSTANCE;
    }
    private Display(){}
    private ParkingServiceInterface strategy;
    @Override
    public String doOperation(String input) {
        String[] inputArr = input.split(" ");
        if(inputArr[1].equals(FREE_COUNT))
        {
            strategy= FreeCount.getInstance();
            return strategy.doOperation(input);
        }
        else if(inputArr[1].equals(FREE_SLOTS))
        {
            strategy= FreeSlots.getInstance();
            return strategy.doOperation(input);
        }
        else if(inputArr[1].equals(OCCUPIED_SLOTS))
        {
            strategy= OccupiedSlots.getInstance();
            return strategy.doOperation(input);
        }
        return null;
    }
}
