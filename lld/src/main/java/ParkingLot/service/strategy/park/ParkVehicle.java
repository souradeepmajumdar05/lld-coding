package ParkingLot.service.strategy.park;

import ParkingLot.models.ParkingSlot;
import ParkingLot.models.Vehicle;
import ParkingLot.models.VehicleType;
import ParkingLot.repository.ParkingLotRepo;
import ParkingLot.repository.TicketRepo;
import ParkingLot.service.strategy.ParkingServiceContext;
import ParkingLot.service.strategy.ParkingServiceInterface;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ParkVehicle implements ParkingServiceInterface {
    private static ParkingServiceInterface INSTANCE=null;
    public static ParkingServiceInterface getInstance() {
        if(ParkVehicle.INSTANCE==null){
            ParkVehicle.INSTANCE=new ParkVehicle();
        }
        return ParkVehicle.INSTANCE;
    }
    private ParkVehicle(){
        parkingLotRepo= ParkingLotRepo.getInstance();
        ticketRepo=TicketRepo.getInstance();
    }
    ParkingLotRepo parkingLotRepo;
    TicketRepo ticketRepo;
    //park_vehicle BIKE KA-01-DB-1541 black
    @Override
    public String doOperation(String input) {
        String[] inputArr = input.split(" ");
        Map<Integer, List<Integer>> listOfAvailableSlots=null;
        String ticket = null;
        ParkingSlot parkingSlot=parkingLotRepo.getFreeSlot(VehicleType.CAR);
        if(parkingSlot!=null){
            Vehicle vehicle = new Vehicle(VehicleType.CAR,inputArr[2],inputArr[3]);
            parkingSlot.setVehicle(vehicle);
            ticket=parkingLotRepo.parkVehicle(parkingSlot);
            ticketRepo.saveTicketAndPos(ticket,parkingSlot.getFloorNumber(),parkingSlot.getSlotNumber());
        }
        else {
            return "Parking Lot Full";
        }
        return "Parked vehicle. Ticket ID: "+ticket;
    }
}
