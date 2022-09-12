package ParkingLot.service.strategy;


import static LibraryManagementSystem.Utility.Constants.EXIT;
import static ParkingLot.Utility.Constants.*;
import ParkingLot.service.strategy.display.Display;
import ParkingLot.service.strategy.*;
import ParkingLot.service.strategy.display.OccupiedSlots;
import ParkingLot.service.strategy.park.ParkVehicle;
import ParkingLot.service.strategy.park.UnparkVehicle;

public class ParkingServiceContext implements ParkingServiceInterface {
    private static ParkingServiceInterface INSTANCE=null;
    public static ParkingServiceInterface getInstance() {
        if(ParkingServiceContext.INSTANCE==null){
            ParkingServiceContext.INSTANCE=new ParkingServiceContext();
        }
        return ParkingServiceContext.INSTANCE;
    }
    private ParkingServiceContext(){}
    private ParkingServiceInterface strategy;
    @Override
    public String doOperation(String input) {
        String[] inputArr = input.split(" ");
        String output = null;
        if(inputArr[0].equals(CREATE_PARKING_LOT))
        {
            strategy= CreateParkingLot.getInstance();
            output=strategy.doOperation(input);
        }
        else if(inputArr[0].equals(DISPLAY))
        {
            strategy= Display.getInstance();
            output=strategy.doOperation(input);
        }
        else if(inputArr[0].equals(PARK_VEHICLE))
        {
            strategy= ParkVehicle.getInstance();
            output=strategy.doOperation(input);
        }
        else if(inputArr[0].equals(UNPARK_VEHICLE))
        {
            strategy= UnparkVehicle.getInstance();
            output=strategy.doOperation(input);
        }
        else if(inputArr[0].equals(EXIT))
        {
            strategy= Exit.getInstace();
            output=strategy.doOperation(input);
        }
        else
        {
            return "Expected funtionality not givens as input : " + input;
        }
        return output;
    }
}
