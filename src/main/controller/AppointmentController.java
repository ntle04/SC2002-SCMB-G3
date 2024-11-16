package main.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import main.csvUitls.AppointmentCSVManager;
import main.model.Appointment;
import main.model.AppointmentOutcome;
import main.util.ApptStatus;
import main.util.TimeSlot;
import main.view.AppointmentView;

public class AppointmentController {
    // private Appointment model;
    // private AppointmentView view;
    private AppointmentCSVManager csvManager = new AppointmentCSVManager();
    // private AppointmentOutcome apptOutcome;

    // Constructor
    public AppointmentController() {
    }

    // // To view available appointment slots
    // public List<AppointmentSlots> viewAvailableSlots() {
    //     List<AppointmentSlots> availableSlots = model.getAvailableSlots();
    //     view.displayAvailableSlots(availableSlots);
    //     return availableSlots;
    // }

    // // To schedule an appointment
    // public void viewScheduleAppointment(int appointmentId, int doctorId, Date appointmentDate, Date appointmentTime) {
    //     boolean success = model.scheduleAppointment(appointmentId, doctorId, appointmentDate, appointmentTime);
    //     if (success) {
    //         view.displayAppointmentScheduled(appointmentId);
    //     } else {
    //         view.displayScheduleError();
    //     }
    // }

    // // Method to reschedule an appointment
    // public void viewRescheduleAppointment(int appointmentId, Date newAppointmentDate, Date newAppointmentTime) {
    //     boolean success = model.rescheduleAppointment(appointmentId, newAppointmentDate, newAppointmentTime);
    //     if (success) {
    //         view.displayAppointmentRescheduled(appointmentId);
    //     } else {
    //         view.displayRescheduleError();
    //     }
    // }

    // // Method to cancel an appointment
    // public void viewCancelAppointment(int appointmentId) {
    //     boolean success = model.cancelAppointment(appointmentId);
    //     if (success) {
    //         view.displayAppointmentCanceled(appointmentId);
    //     } else {
    //         view.displayCancelError();
    //     }
    // }

    // // Method to view scheduled appointments
    // public List<Appointment> viewScheduledAppointment() {
    //     List<Appointment> scheduledAppointments = model.getScheduledAppointments();
    //     view.displayScheduledAppointments(scheduledAppointments);
    //     return scheduledAppointments;
    // }

    // // Method to view past appointment outcomes
    // public List<AppointmentOutcome> viewPastAppointmentOutcome(int appointmentId) {
    //     List<AppointmentOutcome> outcomes = model.getPastAppointmentOutcomes(appointmentId);
    //     view.displayPastAppointmentOutcomes(outcomes);
    //     return outcomes;
    // }


    public void scheduleAppointment(Appointment appointment) {
        try {
            csvManager.addAppointment(appointment);
            System.out.println("Appointment scheduled successfully.");
        } catch (IOException e) {
            System.out.println("Error scheduling appointment: " + e.getMessage());
        }
    }

    public void rescheduleAppointment(String appointmentId, TimeSlot newTimeslot) {
        try {
            List<Appointment> appointments = csvManager.readAppointments();
            for (Appointment appointment : appointments) {
                if (appointment.getAppointmentId().equals(appointmentId)) {
                    appointment.setTimeSlot(newTimeslot);
                    csvManager.updateAppointment(appointmentId, appointment);
                    System.out.println("Appointment rescheduled successfully.");
                    return;
                }
            }
            System.out.println("Appointment not found.");
        } catch (IOException e) {
            System.out.println("Error rescheduling appointment: " + e.getMessage());
        }
    }

    public void cancelAppointment(String appointmentId) {
        try {
            List<Appointment> appointments = csvManager.readAppointments();
            for (Appointment appointment : appointments) {
                if (appointment.getAppointmentId().equals(appointmentId)) {
                    appointment.setStatus(ApptStatus.CANCELLED);
                    csvManager.updateAppointment(appointmentId, appointment);
                    System.out.println("Appointment canceled successfully.");
                    return;
                }
            }
            System.out.println("Appointment not found.");
        } catch (IOException e) {
            System.out.println("Error canceling appointment: " + e.getMessage());
        }
    }
}