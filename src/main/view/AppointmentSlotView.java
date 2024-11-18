package main.view;

import java.util.List;

import main.model.AppointmentSlot;

public class AppointmentSlotView {

    
    /** 
     * @param filteredSlots
     */
    public void printAppointmentSlots(List<AppointmentSlot> filteredSlots){
        int index = 1;
        for (AppointmentSlot slot : filteredSlots) {
            System.out.println(index++ + ". Patient ID: " + slot.getPatientId() + ", Time: " + slot.getAvailabilitySlot().getTimeSlot().getTime() + " APPT ID: " + slot.getAppointmentSlotId());
        }
    }
    
}
