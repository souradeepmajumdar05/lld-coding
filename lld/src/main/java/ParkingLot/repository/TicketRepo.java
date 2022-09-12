package ParkingLot.repository;

import javafx.util.Pair;

import java.util.concurrent.ConcurrentHashMap;

public class TicketRepo {
    private static TicketRepo INSTANCE=null;
    public static TicketRepo getInstance() {
        if(TicketRepo.INSTANCE==null){
            TicketRepo.INSTANCE=new TicketRepo();
        }
        return TicketRepo.INSTANCE;
    }
    private TicketRepo(){
        ticketToLocationMapping=new ConcurrentHashMap<>();
    }

    //ticket:<Pair<floor,slot>>
    private static ConcurrentHashMap<String, Pair<Integer,Integer>> ticketToLocationMapping;
    public boolean saveTicketAndPos(String ticketNumber,Integer floor,Integer slot)
    {
        ticketToLocationMapping.put(ticketNumber,new Pair<>(floor,slot));
        return false;
    }
    public boolean deleteTicket(String ticketNumber)
    {
        ticketToLocationMapping.remove(ticketNumber);
        return false;
    }
    public  Pair<Integer,Integer> get(String ticketNumber)    {
        return ticketToLocationMapping.get(ticketNumber);
    }



}
