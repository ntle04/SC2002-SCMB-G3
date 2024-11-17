package main.view;

import java.util.Date;
import java.util.List;

import main.controller.AppointmentOutcomeController;
import main.model.Appointment;
import main.model.AppointmentOutcome;
import main.util.ApptStatus;

public class AppointmentView {

    AppointmentOutcomeController apptOCtrl = new AppointmentOutcomeController();

    // // Print available slots for a specific appointment
    // public void printAvailableSlots(int appointmentId, String doctorName, Date appointmentDate, Date appointmentTime, List<AppointmentSlots> slots) {
    //     System.out.println("Available slots for Appointment ID: " + appointmentId);
    //     System.out.println("Doctor: " + doctorName);
    //     System.out.println("Date: " + appointmentDate);
    //     System.out.println("Time: " + appointmentTime);
    //     System.out.println("Slots:");
    //     for (AppointmentSlots slot : slots) {
    //         System.out.println("- " + slot.toString()); // Assuming AppointmentSlots has a meaningful toString method
    //     }
    // }

    

    // Method to print confirmation of scheduled appointment
    public void printAppointmentDetails(Appointment appointment) {
        System.out.println("Appointment Details:");
        System.out.println("Appointment ID: " + appointment.getAppointmentId());
        System.out.println("Doctor ID: " + appointment.getDoctorId());
        System.out.println("Patient ID: " + appointment.getPatientId());
        System.out.println("Time: " + appointment.getTimeSlot().getTime());
        System.out.println("Status: " + appointment.getStatus());

        if(appointment.getStatus() == ApptStatus.COMPLETED){
            apptOCtrl.printPatientOutcome(appointment.getAppointmentOutcomeId());
        }
    }

    // // Method to print schedule confirmation message
    // public String printScheduleConfirmation() {
    //     return "Appointment successfully scheduled!";
    // }

    // // Method to print reschedule appointment details
    // public void printRescheduleAppointment(int appointmentId, Date newAppointmentDate, Date newAppointmentTime) {
    //     System.out.println("Rescheduling Appointment...");
    //     System.out.println("Appointment ID: " + appointmentId);
    //     System.out.println("New Date: " + newAppointmentDate);
    //     System.out.println("New Time: " + newAppointmentTime);
    // }

    // // Method to print reschedule confirmation message
    // public String printRescheduleConfirmation() {
    //     return "Appointment successfully rescheduled!";
    // }

    // Method to print cancel appointment details
    public void printCanceledAppointmentDetails(Appointment appointment) {
        System.out.println("Canceling Appointment...");
        System.out.println("Appointment ID: " + appointment.getAppointmentId());
        System.out.println("Time: " + appointment.getTimeSlot());
    }

    // // Method to print cancel confirmation message
    // public String printCancelConfirmation() {
    //     return "Appointment successfully canceled!";
    // }


    //maybe need

    // Method to print scheduled appointments
    public void printScheduledAppointments(List<Appointment> appointments) {
        System.out.println("Scheduled Appointments:");
        for (Appointment appointment : appointments) {
            System.out.println("Appointment ID: " + appointment.getAppointmentId());
            System.out.println("Time: " + appointment.getTimeSlot());
            System.out.println("Patient ID: " + appointment.getPatientId());
            System.out.println("Doctor ID: " + appointment.getDoctorId());
            System.out.println("----------------------------");
        }
    }

    // // Method to print past appointment outcomes
    // public void printPastAppointmentOutcome(int appointmentId, List<AppointmentOutcome> outcomes) {
    //     System.out.println("Past Outcomes for Appointment ID: " + appointmentId);
    //     for (AppointmentOutcome outcome : outcomes) {
    //         System.out.println("- " + outcome.toString()); // Assuming AppointmentOutcome has a meaningful toString method
    //     }
    // }
}