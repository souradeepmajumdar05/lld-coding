package ParkingLot.service.strategy;

import LibraryManagementSystem.service.LibraryManagementStrategy;

public class Exit implements ParkingServiceInterface{
    public static ParkingServiceInterface getInstace() {
        if(Exit.INSTANCE==null)
        {
            Exit.INSTANCE=new Exit();
        }
        return Exit.INSTANCE;
    }
    private Exit(){}
    private static ParkingServiceInterface INSTANCE=null;

    @Override
    public String doOperation(String input) {
        System.exit(0);
        return null;
    }
}
