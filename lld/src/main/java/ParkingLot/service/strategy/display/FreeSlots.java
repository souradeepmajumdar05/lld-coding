package ParkingLot.service.strategy.display;

import ParkingLot.models.VehicleType;
import ParkingLot.repository.ParkingLotRepo;
import ParkingLot.service.strategy.ParkingServiceContext;
import ParkingLot.service.strategy.ParkingServiceInterface;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FreeSlots implements ParkingServiceInterface {
    private static ParkingServiceInterface INSTANCE=null;
    public static ParkingServiceInterface getInstance() {
        if(FreeSlots.INSTANCE==null){
            FreeSlots.INSTANCE=new FreeSlots();
        }
        return FreeSlots.INSTANCE;
    }
    private FreeSlots(){
        parkingLotRepo=ParkingLotRepo.getInstance();
    }
    ParkingLotRepo parkingLotRepo;
    @Override
    public String doOperation(String input) {
        String[] inputArr = input.split(" ");
        Map<Integer, List<Integer>> listOfAvailableSlots=null;
        if(inputArr[2].equals(VehicleType.CAR.name().toString()))
        {
            listOfAvailableSlots = parkingLotRepo.getFreeSlots(VehicleType.CAR);
        }
        else if(inputArr[2].equals(VehicleType.TRUCK.name().toString()))
        {
            listOfAvailableSlots = parkingLotRepo.getFreeSlots(VehicleType.TRUCK);
        }
        else if(inputArr[2].equals(VehicleType.BIKE.name().toString()))
        {
            listOfAvailableSlots = parkingLotRepo.getFreeSlots(VehicleType.BIKE);
        }
        else{
            return "";
        }
        StringBuilder output=new StringBuilder();
        int i=0;
        for(Integer key:listOfAvailableSlots.keySet()){
            output.append("Free slots for ").append(inputArr[2]).append(" on Floor ").append(key).append(": ").append(listOfAvailableSlots.get(key).stream().sorted().map(s->String.valueOf(s)).collect(Collectors.joining(",")));
            i++;
            if(i<listOfAvailableSlots.size()){
                output.append("\n");
            }
        }

        return output.toString();
    }
}
