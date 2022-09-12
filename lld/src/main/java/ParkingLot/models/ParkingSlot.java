package ParkingLot.models;

public class ParkingSlot {
    private Vehicle vehicle;
    private Integer slotNumber;
    private Integer floorNumber;
    private VehicleType allowedVehicleForThisSlot;

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Integer getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(Integer slotNumber) {
        this.slotNumber = slotNumber;
    }

    public VehicleType getAllowedVehicleForThisSlot() {
        return allowedVehicleForThisSlot;
    }

    public void setAllowedVehicleForThisSlot(VehicleType allowedVehicleForThisSlot) {
        this.allowedVehicleForThisSlot = allowedVehicleForThisSlot;
    }

    public Integer getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(Integer floorNumber) {
        this.floorNumber = floorNumber;
    }
}
