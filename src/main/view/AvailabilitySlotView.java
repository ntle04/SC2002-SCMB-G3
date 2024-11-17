package main.view;

import java.util.List;

import main.controller.Authenticate;
import main.model.AvailabilitySlot;
import main.util.Role;

public class AvailabilitySlotView {

    public void printAvailabilitySlots(List<AvailabilitySlot> slots){
        if(Authenticate.getLoggedInUser().getRole() == Role.DOCTOR){
            System.out.println("Doctor Availability:");
            for (AvailabilitySlot slot : slots) {
                System.out.println(slot.getTimeSlot().getTime() + (slot.isAvailable() ? "[AVAILABLE]" : "[BOOKED]"));
            }
        }
        else if(Authenticate.getLoggedInUser().getRole() == Role.PATIENT){
            int index = 1;
            System.out.println("Available Slots:");
            System.out.println("Doctor: " + slots.get(0).getDoctorId());
            for (AvailabilitySlot slot : slots) {	
                System.out.println(index++ + "." + slot.getTimeSlot().getTime());
            }
        }
        else{
            System.out.println("Access denied.");
        }
    }
    
}
