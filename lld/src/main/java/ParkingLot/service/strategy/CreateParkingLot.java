package ParkingLot.service.strategy;

import ParkingLot.models.ParkingSlot;
import ParkingLot.models.VehicleType;
import ParkingLot.repository.ParkingLotRepo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class CreateParkingLot implements ParkingServiceInterface {
    private static ParkingServiceInterface INSTANCE=null;
    public static ParkingServiceInterface getInstance() {
        if(CreateParkingLot.INSTANCE==null){
            CreateParkingLot.INSTANCE=new CreateParkingLot();
        }
        return CreateParkingLot.INSTANCE;
    }
    private CreateParkingLot(){
        parkingLotRepo = ParkingLotRepo.getInstance();
    }
    ParkingLotRepo parkingLotRepo;
    //create_parking_lot PR1234 2 6
    @Override
    public String doOperation(String input) {
        String[] inputArr = input.split(" ");
        parkingLotRepo.addParkingLotName(inputArr[1]);
        ConcurrentHashMap<Integer, List<ParkingSlot>> newParkingLot=new ConcurrentHashMap<Integer, List<ParkingSlot>>();
        for (int i=1;i<=Integer.valueOf(inputArr[2]);i++){
            List<ParkingSlot> floor = new ArrayList<>();
            for(int j=1;j<=Integer.valueOf(inputArr[3]);j++){
                ParkingSlot parkingSlot=new ParkingSlot();
                if(j==1)
                {
                    parkingSlot.setSlotNumber(j);
                    parkingSlot.setAllowedVehicleForThisSlot(VehicleType.TRUCK);
                    parkingSlot.setFloorNumber(i);
                }
                else if(j==2||j==3)
                {
                    parkingSlot.setSlotNumber(j);
                    parkingSlot.setAllowedVehicleForThisSlot(VehicleType.BIKE);
                    parkingSlot.setFloorNumber(i);
                }
                else{
                    parkingSlot.setSlotNumber(j);
                    parkingSlot.setAllowedVehicleForThisSlot(VehicleType.CAR);
                    parkingSlot.setFloorNumber(i);
                }
                floor.add(parkingSlot);
            }
            Collections.sort(floor, new Comparator<ParkingSlot>() {
                @Override
                public int compare(ParkingSlot lhs, ParkingSlot rhs) {
                    return lhs.getSlotNumber() < rhs.getSlotNumber()  ? -1 : (lhs.getSlotNumber()  < rhs.getSlotNumber() ) ? 1 : 0;
                }
            });
            newParkingLot.put(i,floor);
        }
        if(parkingLotRepo.createParkingLot(newParkingLot)){
            return "Created parking lot with "+ inputArr[2]+" floors and "+ inputArr[3]+" slots per floor";
        }
        return "Could not create parkinglot";
    }

    //We will assume that the first slot on each floor will be for a truck, the next 2 for bikes, and all the other slots for cars.
}
