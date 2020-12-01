public class VehicleOwner {
    private int ownerID;
    private int vehicleID;
    private int residencyTime;

    public VehicleOwner(int ownerID, int vehicleID, int residencyTime) {
        this.ownerID = ownerID;
        this.vehicleID = vehicleID;
        this.residencyTime = residencyTime;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int newOwnerID) {
        this.ownerID = newOwnerID;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int newVehicleID) {
        this.vehicleID = newVehicleID;
    }

    public int getResidencyTime() {
        return residencyTime;
    }

    public void setResidencyTime(int newResidencyTime) {
        this.residencyTime = newResidencyTime;
    }
}
