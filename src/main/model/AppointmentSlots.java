package main.model;

import java.time.LocalDateTime;

import main.util.ApptStatus;

public class AppointmentSlots {
    private String slotId;          // Unique identifier for the appointment slot
    private LocalDateTime dateTime; // Date and time for the appointment
    private int doctorId;           // Identifier for the doctor
    private ApptStatus apptStatus;
    // Constructor
    public AppointmentSlots(String slotId, LocalDateTime dateTime, int doctorId) {
        this.slotId = slotId;
        this.dateTime = dateTime;
        this.doctorId = doctorId;
        this.apptStatus = ApptStatus.AVAILABLE; // Default status is available
    }

    // Getters
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public ApptStatus getAppointmentStatus() {
        return apptStatus;
    }

    // Method to book the appointment slot
    public void bookSlot(int patientId) {
        if (apptStatus == ApptStatus.AVAILABLE) {
            apptStatus = ApptStatus.CONFIRMED;
            System.out.println("Appointment slot " + slotId + " booked for patient ID: " + patientId);
        } else {
            System.out.println("This slot is already booked or canceled.");
        }
    }

    // Method to reschedule the appointment
    public void rescheduleAppointment(int apptId, AppointmentSlots newSlot) {
        if (this.apptStatus == ApptStatus.CONFIRMED) {
            // Logic to update the appointment with new slot
            System.out.println("Appointment " + apptId + " rescheduled to " + newSlot.getDateTime());
            this.dateTime = newSlot.getDateTime();
            // Additional logic may be needed to handle doctorId and status
        } else {
            System.out.println("Cannot reschedule. The appointment is not booked.");
        }
    }

    // Method to cancel the appointment slot
    public void cancelSlot() {
        if (apptStatus == ApptStatus.CONFIRMED) {
            apptStatus = ApptStatus.CANCELLED;
            System.out.println("Appointment slot " + slotId + " has been canceled.");
        } else {
            System.out.println("This slot is not booked or already canceled.");
        }
    }
}