package ParkingLot.service.strategy.display;

import ParkingLot.models.VehicleType;
import ParkingLot.repository.ParkingLotRepo;
import ParkingLot.service.strategy.ParkingServiceInterface;

import java.util.Map;

public class FreeCount implements ParkingServiceInterface {
    private static ParkingServiceInterface INSTANCE=null;
    public static ParkingServiceInterface getInstance() {
        if(FreeCount.INSTANCE==null){
            FreeCount.INSTANCE=new FreeCount();
        }
        return FreeCount.INSTANCE;
    }
    private FreeCount(){
        parkingLotRepo=ParkingLotRepo.getInstance();
    }
    ParkingLotRepo parkingLotRepo;
    @Override
    public String doOperation(String input) {
        String[] inputArr = input.split(" ");
        Map<Integer,Integer> count=null;
        if(inputArr[2].equals(VehicleType.CAR.name().toString()))
        {
            count = parkingLotRepo.getAvailableSlotsCount(VehicleType.CAR);
        }
        else if(inputArr[2].equals(VehicleType.TRUCK.name().toString()))
        {
            count = parkingLotRepo.getAvailableSlotsCount(VehicleType.TRUCK);
        }
        else if(inputArr[2].equals(VehicleType.BIKE.name().toString()))
        {
            count = parkingLotRepo.getAvailableSlotsCount(VehicleType.BIKE);
        }
        else{
            return "";
        }
        StringBuilder output=new StringBuilder();
        int i=0;
        for(Integer key:count.keySet()){
            output.append("No. of free slots for ").append(inputArr[2]).append(" on Floor ").append(key).append(": ").append(count.get(key));
            i++;
            if(i<count.size()){
                output.append("\n");
            }
        }

        return output.toString();
    }

}
