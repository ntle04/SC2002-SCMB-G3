package main.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.csvUitls.AppointmentCSVManager;
import main.csvUitls.AppointmentSlotCSVManager;
import main.csvUitls.AvailabilitySlotCSVManager;
import main.model.Appointment;
import main.model.AppointmentOutcome;
import main.model.AppointmentSlot;
import main.model.AvailabilitySlot;
import main.util.ApptStatus;
import main.util.TimeSlot;
import main.view.AppointmentView;

public class AppointmentController {
    // private Appointment model;
    private AppointmentView apptView = new AppointmentView();
    private List<Appointment> appointments;
    private AppointmentCSVManager apptCSVManager = new AppointmentCSVManager();
    // private AppointmentController apptCtrl = new AppointmentController();
    private AppointmentSlotController apptSlotCtrl = new AppointmentSlotController();
    // private AppointmentSlotCSVManager apptSlotCSVManager = new AppointmentSlotCSVManager();
    private AvailabilitySlotController availSlotCtrl = new AvailabilitySlotController();
    // private AvailabilitySlotCSVManager availCSVManager = new AvailabilitySlotCSVManager();
    // private AppointmentOutcome apptOutcome;

    // Constructor
    // public AppointmentController() {
    // }

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


    private void loadAppointments() {
        try {
            appointments = apptCSVManager.readAppointments();
        } catch (IOException e) {
            System.out.println("Error loading appointments: " + e.getMessage());
        }
    }

    public void scheduleAppointment(Appointment appointment) {
        try {
            apptCSVManager.addAppointment(appointment);
            System.out.println("Appointment scheduled successfully.");
        } catch (IOException e) {
            System.out.println("Error scheduling appointment: " + e.getMessage());
        }
    }

    public void rescheduleAppointment(String appointmentId, TimeSlot newTimeslot) {
        try {
            List<Appointment> appointments = apptCSVManager.readAppointments();
            for (Appointment appointment : appointments) {
                if (appointment.getAppointmentId().equals(appointmentId)) {
                    appointment.setTimeSlot(newTimeslot);
                    apptCSVManager.updateAppointment(appointmentId, appointment);
                    System.out.println("Appointment rescheduled successfully.");
                    return;
                }
            }
            System.out.println("Appointment not found.");
        } catch (IOException e) {
            System.out.println("Error rescheduling appointment: " + e.getMessage());
        }
    }

    // public void cancelAppointment(String appointmentId) {
    //     try {
    //         List<Appointment> appointments = csvManager.readAppointments();
    //         for (Appointment appointment : appointments) {
    //             if (appointment.getAppointmentId().equals(appointmentId)) {
    //                 appointment.setStatus(ApptStatus.CANCELLED);
    //                 csvManager.updateAppointment(appointmentId, appointment);
    //                 System.out.println("Appointment canceled successfully.");
    //                 return;
    //             }
    //         }
    //         System.out.println("Appointment not found.");
    //     } catch (IOException e) {
    //         System.out.println("Error canceling appointment: " + e.getMessage());
    //     }
    // }

    //  public void cancelAppointment(String appointmentId) {

    //     for (Appointment slot : appointments) {
    //         if (slot.getAppointmentId().equals(appointmentId)) {
    //             try {
    //                 //change appt status
    //                 apptCtrl.cancelAppointment(slot);
    //                 //change appt slot & avail slot status in model
    //                 slot.cancelAppointment();

    //                 //update in csv
                    

    //                 apptSlotCSVManager.updateAppointmentSlot(slot);

    //                 System.out.println("Appointment cancelled.");
    //             } catch (IOException e) {
    //                 System.out.println("Error cancelling appointment: " + e.getMessage());
    //             }

    //             break;
    //         }
    //     }
        
    // }

    public void cancelAppointment(Appointment appointment) {
        try {
            //update appt status
            appointment.setStatus(ApptStatus.CANCELLED);
            //update appt csv
            apptCSVManager.updateAppointment(appointment.getAppointmentId(), appointment);
            //update appt slot status
            System.out.println("updating appt slot...");
            System.out.println("ID: " + appointment.getAppointmentId());
            System.out.println("slot id: " + appointment.getAppointmentSlotId());
            apptSlotCtrl.cancelAppointmentSlot(appointment.getAppointmentSlotId());
            //update availability slot status n csv
            System.out.println("updating avail slot...");
            System.out.println("ID: " + appointment.getAppointmentId());
            System.out.println("status: " + appointment.getAppointmentSlotId());

            AppointmentSlot apptSlot = apptSlotCtrl.getAppointmentSlotById(appointment.getAppointmentSlotId());
            System.out.println("status: " + apptSlot.getAvailabilitySlotId());
            availSlotCtrl.cancelAvailabilitySlot(apptSlot.getAvailabilitySlotId());
            System.out.println("Appointment canceled successfully.");
            return;
        } catch (IOException e) {
            System.out.println("Error canceling appointment: " + e.getMessage());
        }
    }



    public List<Appointment> getCompletedAppointmentsByDoctorId(String doctorId){
        List<Appointment> filteredRecords = new ArrayList<>();
        loadAppointments();
        for (Appointment record : appointments) {
            if (record.getStatus() == ApptStatus.COMPLETED && record.getDoctorId().equals(doctorId)) {
                filteredRecords.add(record);
            }
        }
        return filteredRecords;
    }

    public List<Appointment> getCompletedAppointmentsByPatientId(String patientId){
        List<Appointment> filteredRecords = new ArrayList<>();
        loadAppointments();
        for (Appointment record : appointments) {
            if (record.getStatus() == ApptStatus.COMPLETED && record.getPatientId().equals(patientId)) {
                filteredRecords.add(record);
            }
        }
        return filteredRecords;
    }

    public List<Appointment> getConfirmedAppointmentsByPatientId(String patientId){
        List<Appointment> filteredRecords = new ArrayList<>();
        loadAppointments();
        for (Appointment record : appointments) {
            if (record.getStatus() == ApptStatus.CONFIRMED && record.getPatientId().equals(patientId)) {
                filteredRecords.add(record);
            }
        }
        if(filteredRecords.size() < 1){
            System.out.println("No scheduled appointments");
        }
        return filteredRecords;
    }

    public Appointment getConfirmedAppointmentByPatientId(String patientId){
        loadAppointments();
        System.out.println("Get confirmed appts by pateint id");
        System.out.println("size of arr: " + appointments);
        for (Appointment record : appointments) {
            System.out.println("ID: " + record.getAppointmentId());
            System.out.println("status: " + record.getStatus());
            System.out.println("patient id: " + record.getPatientId());
            if (record.getStatus() == ApptStatus.CONFIRMED && record.getPatientId().equals(patientId)) {
                System.out.println(record.getAppointmentId());
                return record;
            }
        }
        System.out.print("No appointment found.");
        return null;
    }

    public List<Appointment> getAppointmentsByPatientId(String patientId){
        List<Appointment> filteredRecords = new ArrayList<>();
        loadAppointments();
        for (Appointment record : appointments) {
            if (record.getPatientId().equals(patientId)) {
                filteredRecords.add(record);
            }
        }
        return filteredRecords;
    }

    public void printScheduledAppointments(List<Appointment> appointments){
        apptView.printScheduledAppointments(appointments);
    }
}