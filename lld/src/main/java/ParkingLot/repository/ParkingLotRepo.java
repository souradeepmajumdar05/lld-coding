package ParkingLot.repository;

import ParkingLot.models.ParkingSlot;
import ParkingLot.models.VehicleType;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLotRepo {
    private static ParkingLotRepo INSTANCE = null;

    public static ParkingLotRepo getInstance() {
        if (ParkingLotRepo.INSTANCE == null) {
            ParkingLotRepo.INSTANCE = new ParkingLotRepo();
        }
        return ParkingLotRepo.INSTANCE;
    }

    private ParkingLotRepo() {
        parkingLot = new ConcurrentHashMap<>();
        parkingAttributes = new ConcurrentHashMap<>();
    }


    //floor number : listOfSlot
    private static ConcurrentHashMap<Integer, List<ParkingSlot>> parkingLot;
    //name:name
    private static ConcurrentHashMap<String, String> parkingAttributes;


    public boolean addParkingLotName(String name) {
        parkingAttributes.put("name", name);
        return true;
    }

    public boolean createParkingLot(ConcurrentHashMap<Integer, List<ParkingSlot>> newParkingLot) {
        parkingLot.putAll(newParkingLot);
        return true;
    }

    public Map<Integer, Integer> getAvailableSlotsCount(VehicleType vehicleType) {
        Map<Integer, Integer> availableSlots = new HashMap<>();
        for (Integer floorNumber : parkingLot.keySet()) {
            Integer count = 0;
            for (ParkingSlot parkingSlot : parkingLot.get(floorNumber)) {
                if (parkingSlot.getAllowedVehicleForThisSlot().name().toString().equals(vehicleType.toString()) &&
                        parkingSlot.getVehicle() == null) {
                    count++;
                }
            }
            availableSlots.put(floorNumber, count);
        }
        return availableSlots;
    }

    public Map<Integer, List<Integer>> getFreeSlots(VehicleType vehicleType) {
        Map<Integer, List<Integer>> availableSlots = new HashMap<>();
        for (Integer floorNumber : parkingLot.keySet()) {
            List<Integer> listOfSlots = new ArrayList<>();
            for (ParkingSlot parkingSlot : parkingLot.get(floorNumber)) {
                if (parkingSlot.getAllowedVehicleForThisSlot().name().toString().equals(vehicleType.toString()) &&
                        parkingSlot.getVehicle() == null) {
                    listOfSlots.add(parkingSlot.getSlotNumber());
                }
            }
            availableSlots.put(floorNumber, listOfSlots);
        }
        return availableSlots;
    }

    public Map<Integer, List<Integer>> getOccupiedSlots(VehicleType vehicleType) {
        Map<Integer, List<Integer>> occupiedSlots = new HashMap<>();
        for (Integer floorNumber : parkingLot.keySet()) {
            List<Integer> listOfSlots = new ArrayList<>();
            for (ParkingSlot parkingSlot : parkingLot.get(floorNumber)) {
                if (parkingSlot.getAllowedVehicleForThisSlot().name().toString().equals(vehicleType.toString()) &&
                        parkingSlot.getVehicle() != null) {
                    listOfSlots.add(parkingSlot.getSlotNumber());
                }
            }
            occupiedSlots.put(floorNumber, listOfSlots);
        }
        return occupiedSlots;
    }

    public ParkingSlot getFreeSlot(VehicleType vehicleType) {
        for (Integer floorNumber : parkingLot.keySet()) {
            for (ParkingSlot parkingSlot : parkingLot.get(floorNumber)) {
                if (parkingSlot.getAllowedVehicleForThisSlot().name().toString().equals(vehicleType.toString()) &&
                        parkingSlot.getVehicle() == null) {
                    return parkingSlot;
                }
            }

        }
        return null;
    }

    public String parkVehicle(ParkingSlot parkingSlot) {
        StringBuilder ticket = new StringBuilder();
        //park vehicle
        List<ParkingSlot> listOfparkingSlot = parkingLot.get(parkingSlot.getFloorNumber());
        for (ParkingSlot tempParkingSlot : listOfparkingSlot) {
            if (tempParkingSlot.getAllowedVehicleForThisSlot().name().toString()
                    .equals(parkingSlot.getVehicle().getType().name().toString()) &&
                    tempParkingSlot.getVehicle() == null) {
                //tempParkingSlot.setVehicle(parkingSlot.getVehicle());
                break;
            }
        }
        parkingLot.put(parkingSlot.getFloorNumber(), listOfparkingSlot);
        ticket.append(parkingAttributes.get("name"))
                .append("_").append(parkingSlot.getFloorNumber())
                .append("_").append(parkingSlot.getSlotNumber());
        return ticket.toString();
    }

    public ParkingSlot unpark(Pair<Integer,Integer> pos){
        ParkingSlot parkingSlot=parkingLot.get(pos.getKey()).get(pos.getValue()-1);
        parkingLot.get(pos.getKey()).get(pos.getValue()).setVehicle(null);
        return parkingSlot;
    }

    public ParkingSlot get(Pair<Integer, Integer> pos) {
        return parkingLot.get(pos.getKey()).get(pos.getValue()-1);
    }
}
