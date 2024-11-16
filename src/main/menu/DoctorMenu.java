package main.menu;

import java.util.List;
import java.util.Scanner;

import main.controller.Authenticate;
import main.controller.AvailabilitySlotController;
import main.util.TimeSlot;

public class DoctorMenu extends Menu{

    AvailabilitySlotController availabilitySlotController = new AvailabilitySlotController();

    public void printMenu(){
        System.out.println("=== Doctor Menu ===");
        System.out.println("1. View Patient Medical Records");
        System.out.println("2. Update Patient Medical Records");
        System.out.println("3. View Personal Schedule");
        System.out.println("4. Set Availability for Appointments");
        System.out.println("5. Accept or Decline Appointment Requests");
        System.out.println("6. View Upcoming Appointments");
        System.out.println("7. Record Appointment Outcome");
        System.out.println("8. Logout");
    }

    public void handleUserInput(){
        int choice = -1;
        Scanner sc = new Scanner(System.in);

        do{
            printMenu();
            System.out.println("Enter your choice: ");
            choice = sc.nextInt();
            
            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    TimeSlot.printAllTimeSlots();

                    Scanner scanner = new Scanner(System.in);
                    System.out.print("Enter the numbers of the time slots you want to add (separate with spaces): ");
                    String input = scanner.nextLine();
                    List<TimeSlot> selectedSlots = TimeSlot.getSlotsByIndices(input);
                    availabilitySlotController.addAvailabilitySlots(Authenticate.getLoggedInUser().getId(), selectedSlots);
                    break;
                case 5:
                    break;
                default:
                    break;
            }

        }while(choice < 8);

    };
    
}
