package main.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.csvUitls.AppointmentSlotCSVManager;
import main.model.AppointmentSlot;
import main.model.AvailabilitySlot;
import main.util.ApptStatus;
import main.util.TimeSlot;

public class AppointmentSlotController {
    private List<AppointmentSlot> appointmentSlots;
    private AppointmentSlotCSVManager csvManager = new AppointmentSlotCSVManager();

    public AppointmentSlotController() {
        this.appointmentSlots = new ArrayList<>();
        loadAppointmentSlots();
    }

    private void loadAppointmentSlots() {
        try {
            appointmentSlots = csvManager.loadAppointmentSlots();
        } catch (IOException e) {
            System.out.println("Error loading appointment slots: " + e.getMessage());
        }

        System.out.println("Finish loading data");
    }

    public void bookAppointment(String patientId, AvailabilitySlot availabilitySlot) {
        try {
            AppointmentSlot newSlot = new AppointmentSlot(availabilitySlot.getDoctorId(), patientId, availabilitySlot.getTimeSlot(), ApptStatus.PENDING);
            newSlot.bookSlot(patientId, availabilitySlot);
            System.out.println("Booking appt");
            csvManager.addAppointmentSlot(newSlot);
        } catch (IOException e) {
            System.out.println("Error booking appointment: " + e.getMessage());
        }
    }

    public void cancelAppointment(String doctorId, String patientId, TimeSlot timeSlot) {

        for (AppointmentSlot slot : appointmentSlots) {
            // Check if the slot matches both doctorId, patientId and timeSlot
            if (slot.getAvailabilitySlot().getDoctorId().equals(doctorId) && slot.getPatientId().equals(patientId) && slot.getAvailabilitySlot().getTimeSlot() == timeSlot) {
                try {
                    csvManager.updateAppointmentSlot(slot);
                    slot.cancelAppointment();
                    System.out.println("Appointment cancelled.");
                } catch (IOException e) {
                    System.out.println("Error cancelling appointment: " + e.getMessage());
                }

                break;
            }
        }
        
    }
    
}
