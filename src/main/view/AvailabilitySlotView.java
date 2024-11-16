package main.view;

import java.util.List;

import main.model.AvailabilitySlot;

public class AvailabilitySlotView {

    //print all slots that are set by doctor - either available or booked
    public void printAvailabilitySlots(List<AvailabilitySlot> slots) {
        for (AvailabilitySlot slot : slots) {
            printAvailabilitySlot(slot);
        }
    }

    public void printAvailabilitySlot(AvailabilitySlot slot){
        if(slot.isAvailable()){
            System.out.println(slot.getTimeSlot().getTime() + " [AVAILABLE]");
        }
        else{
            System.out.println(slot.getTimeSlot().getTime() + " [BOOKED]");
        }
    }
    
}
