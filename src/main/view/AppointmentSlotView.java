package main.view;

import java.util.List;

import main.model.AppointmentSlot;

public class AppointmentSlotView {

    public void printPendingAppointmentSlots(List<AppointmentSlot> filteredSlots){
        int index = 1;

        System.out.println("Pending Appointment Slots:");
        for (AppointmentSlot slot : filteredSlots) {
            System.out.println(index++ + ". Patient ID: " + slot.getPatientId() + ", Time: " + slot.getAvailabilitySlot().getTimeSlot().getTime() + "availability slot id: " + slot.getAvailabilitySlot().getAvailabilitySlotId());
        }
    }
    
}
