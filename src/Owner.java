public class Owner {
    private String name;
    private String vehicleNumber;

    public Owner(String name,String vehicleNumber){
        this.name =name;
        this.vehicleNumber=vehicleNumber;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
    public String getDisplayInfo(){
        return name+vehicleNumber;
    }
}
