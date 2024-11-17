package main.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.csvUitls.AvailabilitySlotCSVManager;
import main.csvUitls.Config;
import main.csvUitls.IdGenerator;
import main.model.AppointmentSlot;
import main.model.AvailabilitySlot;
import main.util.ApptStatus;
import main.util.TimeSlot;
import main.view.AvailabilitySlotView;

public class AvailabilitySlotController {

    private List<AvailabilitySlot> availabilitySlots;
    private AvailabilitySlotCSVManager csvManager = new AvailabilitySlotCSVManager();
    private AvailabilitySlotView view = new AvailabilitySlotView();

        private static final String FILE_PATH = Config.AVAILABILITY_SLOTS_FILE_PATH;

    public AvailabilitySlotController() {
        this.availabilitySlots = new ArrayList<>();
        loadAvailabilities();
    }

    private void loadAvailabilities() {
        try {
            availabilitySlots = csvManager.loadAvailabilities();
        } catch (IOException e) {
            System.out.println("Error loading availabilities: " + e.getMessage());
        }
    }

    public List<AvailabilitySlot> getAvailabilitylist(){
        try {
            availabilitySlots = csvManager.loadAvailabilities();
            return availabilitySlots;
        } catch (IOException e) {
            System.out.println("Error loading availabilities: " + e.getMessage());
        }
                return availabilitySlots;
    }

    public void addAvailabilitySlots(String doctorId, List<TimeSlot> slots) {
        
        for (TimeSlot slot : slots) {
            List<AvailabilitySlot> newAvailabilitySlots = new ArrayList<>();
            AvailabilitySlot newSlot = new AvailabilitySlot(doctorId, slot);
            newAvailabilitySlots.add(newSlot);
        
        try {
            csvManager.addAvailabilities(newAvailabilitySlots);
            System.out.println("Availability slots added successfully.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        }
    }
    

    public void updateAvailability(String doctorId, TimeSlot timeSlot, boolean isAvailable) {
        for (AvailabilitySlot slot : availabilitySlots) {
            // Check if the slot matches both doctorId and timeSlot
            if (slot.getDoctorId().equals(doctorId) && slot.getTimeSlot() == timeSlot) {
                // Update the isAvailable status
                slot.setAvailability(isAvailable);
                break;
            }
        }
        
        try {
            csvManager.updateAvailabilitySlots(availabilitySlots);
            System.out.println("Availability slot updated successfully.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void cancelAvailabilitySlot(String availabilitySlotId) {
        try {
            AvailabilitySlot availabilitySlot = getAvailabilitySlotById(availabilitySlotId);
            //update appt slot status
            availabilitySlot.setStatus(true);
            //update appt csv
            csvManager.updateAvailabilitySlot(availabilitySlot);

            System.out.println("Availability slot canceled successfully.");
            return;
        } catch (IOException e) {
            System.out.println("Error canceling appointment: " + e.getMessage());
        }
    }

    public void removeAvailabilitySlot(String doctorId, TimeSlot timeSlot) {
        availabilitySlots.removeIf(slot -> slot.getDoctorId() == doctorId && slot.getTimeSlot() == timeSlot);
        
        try {
            csvManager.updateAvailabilitySlots(availabilitySlots);
            System.out.println("Availability slots updated successfully.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public List<AvailabilitySlot> getAvailabilitySlotsByDoctor(String doctorId) {
        List<AvailabilitySlot> doctorSlots = new ArrayList<>();
        loadAvailabilities(); //get latest data
        for (AvailabilitySlot slot : availabilitySlots) {
            if (slot.getDoctorId().equals(doctorId) & slot.isAvailable()) {
                doctorSlots.add(slot);
            }
        }
        return doctorSlots;
    }

    public AvailabilitySlot getAvailabilitySlotById(String slotId) {
        loadAvailabilities(); //get latest data
        for (AvailabilitySlot slot : availabilitySlots) {
            if (slot.getAvailabilitySlotId().equals(slotId)) {
                return slot;
            }
        }
        return null;
    }

    public void printAvailabilitySlotsByDoctor(String doctorId){
        view.printAvailabilitySlots(getAvailabilitySlotsByDoctor(doctorId));
    }
    
}
