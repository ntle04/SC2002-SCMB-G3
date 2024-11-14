package main.view;

import java.util.Date;
import java.util.List;

import main.model.Appointment;
import main.model.AppointmentOutcome;
import main.model.AppointmentSlots;

public class AppointmentView {

    // Print available slots for a specific appointment
    public void printAvailableSlots(int appointmentId, String doctorName, Date appointmentDate, Date appointmentTime, List<AppointmentSlots> slots) {
        System.out.println("Available slots for Appointment ID: " + appointmentId);
        System.out.println("Doctor: " + doctorName);
        System.out.println("Date: " + appointmentDate);
        System.out.println("Time: " + appointmentTime);
        System.out.println("Slots:");
        for (AppointmentSlots slot : slots) {
            System.out.println("- " + slot.toString()); // Assuming AppointmentSlots has a meaningful toString method
        }
    }

    // Method to print confirmation of scheduled appointment
    public void printScheduleAppointment(int appointmentId, int doctorId, Date appointmentDate, Date appointmentTime) {
        System.out.println("Scheduling Appointment...");
        System.out.println("Appointment ID: " + appointmentId);
        System.out.println("Doctor ID: " + doctorId);
        System.out.println("Date: " + appointmentDate);
        System.out.println("Time: " + appointmentTime);
    }

    // Method to print schedule confirmation message
    public String printScheduleConfirmation() {
        return "Appointment successfully scheduled!";
    }

    // Method to print reschedule appointment details
    public void printRescheduleAppointment(int appointmentId, Date newAppointmentDate, Date newAppointmentTime) {
        System.out.println("Rescheduling Appointment...");
        System.out.println("Appointment ID: " + appointmentId);
        System.out.println("New Date: " + newAppointmentDate);
        System.out.println("New Time: " + newAppointmentTime);
    }

    // Method to print reschedule confirmation message
    public String printRescheduleConfirmation() {
        return "Appointment successfully rescheduled!";
    }

    // Method to print cancel appointment details
    public void printCancelAppointment(int appointmentId) {
        System.out.println("Canceling Appointment...");
        System.out.println("Appointment ID: " + appointmentId);
    }

    // Method to print cancel confirmation message
    public String printCancelConfirmation() {
        return "Appointment successfully canceled!";
    }

    // Method to print scheduled appointments
    public void printScheduledAppointment(List<Appointment> appointments) {
        System.out.println("Scheduled Appointments:");
        for (Appointment appointment : appointments) {
            System.out.println("- " + appointment.toString()); // Assuming Appointment has a meaningful toString method
        }
    }

    // Method to print past appointment outcomes
    public void printPastAppointmentOutcome(int appointmentId, List<AppointmentOutcome> outcomes) {
        System.out.println("Past Outcomes for Appointment ID: " + appointmentId);
        for (AppointmentOutcome outcome : outcomes) {
            System.out.println("- " + outcome.toString()); // Assuming AppointmentOutcome has a meaningful toString method
        }
    }
}