package main.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.csvUitls.AvailabilitySlotCSVManager;
import main.model.AvailabilitySlot;
import main.util.TimeSlot;
import main.view.AvailabilitySlotView;

public class AvailabilitySlotController {

    private List<AvailabilitySlot> availabilitySlots;
    private AvailabilitySlotCSVManager csvManager = new AvailabilitySlotCSVManager();
    private AvailabilitySlotView view = new AvailabilitySlotView();

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

    public void addAvailabilitySlots(String doctorId, List<TimeSlot> slots) {
        List<AvailabilitySlot> newAvailabilitySlots = new ArrayList<>();
        for (TimeSlot slot : slots) {
            AvailabilitySlot newSlot = new AvailabilitySlot(doctorId, slot, true);
            newAvailabilitySlots.add(newSlot);
        }
        try {
            csvManager.addAvailabilities(newAvailabilitySlots);
            System.out.println("Availability slots added successfully.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
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
            if (slot.getDoctorId().equals(doctorId)) {
                doctorSlots.add(slot);
            }
        }
        return doctorSlots;
    }

    public void printAvailabilitySlotsByDoctor(String doctorId){
        view.printAvailabilitySlots(getAvailabilitySlotsByDoctor(doctorId));
    }
    
}