import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class App {
    // Global variables
    private static int[][] parkingStructure = null;
    private static String[] zoneLabels = new String[0];
    private static Owner[][] ownersDetails = new Owner[0][];



    public static void main(String[] args) {
        System.out.println("Welcome to the Parking Lot Reservation App!");
        initialiseZones();
        runMenu();
    }

    public static void initialiseZones() {
        parkingStructure = new int[2][];
        parkingStructure[0] = new int[10]; // Zone A: Compact (10 slots)
        parkingStructure[1] = new int[14]; // Zone B: Standard (14 slots)

        zoneLabels = new String[2];
        zoneLabels[0]="A";
        zoneLabels[1]="B";

        ownersDetails = new Owner[2][];
        ownersDetails[0] = new Owner[10]; // Zone A: Compact (10 slots)
        ownersDetails[1] = new Owner[14];



    }

    public static void runMenu() {
        int option;
        boolean cont = true;
        while (cont) {
            option = getOption();
            switch (option) {
                case 0:
                    cont = false;
                    break;
                case 1:
                    reserveSlot();
                    break;
                case 2:
                    showParkingArea();
                    break;
                case 3:
                    addZone();
                    break;
                case 4:
                    registerOwner();
                    break;
                case 5:
                    saveToFile();
                    break;
                default:
                    System.out.println("Option not available. Please select a valid option:");
            }
        }
    }

    private static int getOption() {
        Scanner input = new Scanner(System.in);
        boolean valid = false;
        int option = -1;
        do {
            System.out.println();
            System.out.println("+---------------------------------------------+");
            System.out.println("|                 MAIN MENU                   |");
            System.out.println("+---------------------------------------------+");
            System.out.println("|  1) Reserve a parking slot                  |");
            System.out.println("|  2) Show parking area                       |");
            System.out.println("|  3) Add new parking zone                    |");
            System.out.println("|  4) Register vehicle owner                  |");
            System.out.println("|  5) Save information to file                |");
            System.out.println("|  0) Quit                                    |");
            System.out.println("+---------------------------------------------+");
            System.out.print("Please select an option: ");
            try {
                option = input.nextInt();
                valid = true;
            } catch (Exception e) {
                System.out.println("This option is not valid.");
                input.next(); // Clear buffer
            }
        } while (!valid);
        return option;
    }

    private static void reserveSlot() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter zone  ");
        String zoneInput = input.next().toUpperCase();
        int zoneIndex =  -1;
        for (int i=0;i<zoneLabels.length;i++){
            if (zoneLabels[i].equals(zoneInput)){
                zoneIndex=i;
                break;
            }
        }

        if (zoneIndex == -1) {
            System.out.println("Invalid zone. Please enter A or B.");
            return;
        }

        System.out.print("Enter slot number: ");
        int slot = input.nextInt() - 1;

        if (slot < 0 || slot >= parkingStructure[zoneIndex].length) {
            System.out.println("Invalid slot number.");
            return;
        }

        if (parkingStructure[zoneIndex][slot] == 0) {
            parkingStructure[zoneIndex][slot] = 1;
            System.out.println("Reservation successful.");
            showParkingArea();
        } else {
            System.out.println("This slot is already reserved.");
        }
    }

    private static void showParkingArea() {
        System.out.println("=".repeat(60));
        System.out.println("                PARKING LOT STATUS             ");
        System.out.println("=".repeat(60));


        for (int i = 0; i < parkingStructure.length; i++) {
            System.out.print(zoneLabels[i] + "  ");
            for (int j = 0; j < parkingStructure[i].length; j++) {
                if (parkingStructure[i][j] == 0) {
                    System.out.print("[O]");
                } else {
                    System.out.print("[X]");
                }
            }
            System.out.println();
        }

        System.out.println("=".repeat(60));
        System.out.println("LEGEND: [O] = Available | [X] = Reserved");
        System.out.println("=".repeat(60));
        System.out.println();
    }

    private static void addZone() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter zone name ");
        String zoneName = input.next().toUpperCase();

        System.out.print("Enter Slots Count ");
        int slots = input.nextInt();

        int[][] newparkingStructure = new int[parkingStructure.length + 1][];
        String[] newzoneLabels = new String[zoneLabels.length + 1];
        Owner[][] newownersDetails=new Owner[ownersDetails.length + 1][];

        for (int i=0; i < parkingStructure.length; i++) {
            newparkingStructure[i] = parkingStructure[i];
            newzoneLabels[i] = zoneLabels[i];
            newownersDetails[i]=ownersDetails[i];
        }
        newparkingStructure[newparkingStructure.length - 1] = new int[slots];
        newzoneLabels[newzoneLabels.length - 1] = zoneName;
        newownersDetails[newownersDetails.length -1]= new Owner[slots];

        parkingStructure = newparkingStructure;
        zoneLabels = newzoneLabels;
        ownersDetails = newownersDetails;

        System.out.println("Zone added successfully");
    }

        // method not implemented


    private static void registerOwner() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter zone  ");
        String zoneInput = input.next().toUpperCase();
        int zoneIndex = -1;
        for (int i = 0; i < zoneLabels.length; i++) {
            if (zoneLabels[i].equals(zoneInput)) {
                zoneIndex = i;
                break;
            }
        }

        if (zoneIndex == -1) {
            System.out.println("Invalid zone. Please enter A or B.");
            return;
        }

        System.out.print("Enter slot number: ");
        int slot = input.nextInt() - 1;

        if (slot < 0 || slot >= parkingStructure[zoneIndex].length) {
            System.out.println("Invalid slot number.");
            return;
        }
        if (parkingStructure[zoneIndex][slot] == 0) {
            System.out.println("Reservation have to be done Still.");
        }
        input.nextLine();
        System.out.println("Enter the owners name");
        String ownername = input.nextLine();
        System.out.println("Enter vehicle Number");
        String vehicleNumber = input.nextLine();
        ownersDetails [zoneIndex][slot] = new Owner (ownername,vehicleNumber);


    }
    private static void saveToFile() {
        try {
            FileWriter file = new FileWriter("ParkingData.txt");
            file.write("Data:\n");
            for (int i = 0; i < parkingStructure.length; i++) {

                file.write("zone" + zoneLabels[i] + " \n ");
                for (int j = 0; j < parkingStructure[i].length; j++) {
                    String status;
                    if (parkingStructure[i][j] == 0) {
                        status = "Available";
                    } else {
                        Owner owner  = ownersDetails[i][j];
                        if (owner != null) {
                            status = "Owner" + owner.getDisplayInfo();
                        } else {
                            status = "Reserved but no owner information available";
                        }
                    }
                    file.write((i + j) + status);
                }
                file.write("\n");
            }
            // Implement save data here based on test task
            file.close();
            System.out.println("Data saved to file.");
            } catch (IOException exception) {
                System.out.println("Error while writing to file.");
        }
    }
}
