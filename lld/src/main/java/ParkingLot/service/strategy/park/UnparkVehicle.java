package ParkingLot.service.strategy.park;

import ParkingLot.models.ParkingSlot;
import ParkingLot.repository.ParkingLotRepo;
import ParkingLot.repository.TicketRepo;
import ParkingLot.service.strategy.ParkingServiceContext;
import ParkingLot.service.strategy.ParkingServiceInterface;
import javafx.util.Pair;

public class UnparkVehicle implements ParkingServiceInterface {
    private static ParkingServiceInterface INSTANCE=null;
    public static ParkingServiceInterface getInstance() {
        if(UnparkVehicle.INSTANCE==null){
            UnparkVehicle.INSTANCE=new UnparkVehicle();
        }
        return UnparkVehicle.INSTANCE;
    }
    private UnparkVehicle(){
        parkingLotRepo=ParkingLotRepo.getInstance();
        ticketRepo=TicketRepo.getInstance();
    }
    ParkingLotRepo parkingLotRepo;
    TicketRepo ticketRepo;
    @Override
    public String doOperation(String input) {
        String[] inputArr = input.split(" ");
        Pair<Integer,Integer> pos =ticketRepo.get(inputArr[1]);
        if (pos==null){
            return "Invalid Ticket";
        }
        ticketRepo.deleteTicket(inputArr[1]);
        ParkingSlot parkingSlot=parkingLotRepo.get(pos);
        String output="Unparked vehicle with Registration Number: "+parkingSlot.getVehicle().getRegistrationNumber()+" and Color: "+parkingSlot.getVehicle().getColor();
        parkingLotRepo.unpark(pos);
        return output;
    }
}
