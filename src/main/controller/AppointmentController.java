package main.controller;

import java.util.Date;
import java.util.List;

import main.model.Appointment;

public class AppointmentController {
    private Appointment model;
    private AppointmentView view;
    private AppointmentOutcome apptOutcome;

    // Constructor
    public AppointmentController(Appointment model, AppointmentView view) {
        this.model = model;
        this.view = view;
    }

    // To view available appointment slots
    public List<AppointmentSlots> viewAvailableSlots() {
        List<AppointmentSlots> availableSlots = model.getAvailableSlots();
        view.displayAvailableSlots(availableSlots);
        return availableSlots;
    }

    // To schedule an appointment
    public void viewScheduleAppointment(int appointmentId, int doctorId, Date appointmentDate, Date appointmentTime) {
        boolean success = model.scheduleAppointment(appointmentId, doctorId, appointmentDate, appointmentTime);
        if (success) {
            view.displayAppointmentScheduled(appointmentId);
        } else {
            view.displayScheduleError();
        }
    }

    // Method to reschedule an appointment
    public void viewRescheduleAppointment(int appointmentId, Date newAppointmentDate, Date newAppointmentTime) {
        boolean success = model.rescheduleAppointment(appointmentId, newAppointmentDate, newAppointmentTime);
        if (success) {
            view.displayAppointmentRescheduled(appointmentId);
        } else {
            view.displayRescheduleError();
        }
    }

    // Method to cancel an appointment
    public void viewCancelAppointment(int appointmentId) {
        boolean success = model.cancelAppointment(appointmentId);
        if (success) {
            view.displayAppointmentCanceled(appointmentId);
        } else {
            view.displayCancelError();
        }
    }

    // Method to view scheduled appointments
    public List<Appointment> viewScheduledAppointment() {
        List<Appointment> scheduledAppointments = model.getScheduledAppointments();
        view.displayScheduledAppointments(scheduledAppointments);
        return scheduledAppointments;
    }

    // Method to view past appointment outcomes
    public List<AppointmentOutcome> viewPastAppointmentOutcome(int appointmentId) {
        List<AppointmentOutcome> outcomes = model.getPastAppointmentOutcomes(appointmentId);
        view.displayPastAppointmentOutcomes(outcomes);
        return outcomes;
    }
}
