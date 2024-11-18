package main.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.csvUitls.AppointmentCSVManager;
import main.model.Appointment;
import main.model.AppointmentSlot;
import main.model.AvailabilitySlot;
import main.util.ApptStatus;
import main.util.TimeSlot;
import main.view.AppointmentView;


public class AppointmentController {
    private AppointmentView apptView = new AppointmentView();
    private List<Appointment> appointments;
    private AppointmentCSVManager apptCSVManager = new AppointmentCSVManager();
    private AppointmentSlotController apptSlotCtrl = new AppointmentSlotController();
    private AvailabilitySlotController availSlotCtrl = new AvailabilitySlotController();

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
        }
    }

    public void rescheduleAppointment(String appointmentId, TimeSlot newTimeslot) {
        try {
            List<Appointment> appointments = apptCSVManager.readAppointments();
            List<AvailabilitySlot> availability = availSlotCtrl.getAvailabilitylist();
            for (Appointment appointment : appointments) {
                if (appointment.getAppointmentId().equals(appointmentId)) {
                    appointment.setTimeSlot(newTimeslot);
                    apptCSVManager.updateAppointment(appointmentId, appointment);
                    System.out.println("Appointment rescheduled successfully.");
                    for(AvailabilitySlot avail : availability){
                        AppointmentSlot apptslot = apptSlotCtrl.getAppointmentSlotById(appointment.getAppointmentSlotId());
                        if(avail.getAvailabilitySlotId().equals(apptslot.getAvailabilitySlotId()))
                        {
                            availSlotCtrl.updateAvailability(avail.getDoctorId(),avail.getTimeSlot(),false);
                        }
                    }
                    return;

                }
            }
            System.out.println("Appointment not found.");
        } catch (IOException e) {
        }
    }

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

    public List<Appointment> getAllAppointments(){
        loadAppointments();
        if(appointments.size()<1){
            System.out.println("No appointments to load.");
        }
        return appointments;
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

    public List<Appointment> getConfirmedAppointmentsByDoctorId(String doctorId){
        List<Appointment> filteredRecords = new ArrayList<>();
        loadAppointments();
        System.out.println("Get confirmed appointments by Doctor Id");
        for (Appointment record : appointments) {
            System.out.println("ID: " + record.getAppointmentId());
            System.out.println("Status: " + record.getStatus());
            System.out.println("Doctor Id: " + record.getDoctorId());
            if (record.getStatus() == ApptStatus.CONFIRMED && record.getPatientId().equals(doctorId)) {
                System.out.println(record.getAppointmentId());
                filteredRecords.add(record);
            }
        }
        if(filteredRecords.size()<1){
            System.out.print("No appointment found.");
            return null;
        }else{
            return filteredRecords;
        }

        
    }

    public Appointment getConfirmedAppointmentByPatientId(String patientId){
        loadAppointments();
        System.out.println("Get confirmed appointments by Patient Id");
        for (Appointment record : appointments) {
            System.out.println("ID: " + record.getAppointmentId());
            System.out.println("Status: " + record.getStatus());
            System.out.println("Patient Id: " + record.getPatientId());
            if (record.getStatus() == ApptStatus.CONFIRMED && record.getPatientId().equals(patientId)) {
                System.out.println(record.getAppointmentId());
                return record;
            }
        }
        System.out.println("No appointment found.");
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

    public void printAllAppointments(){
        List<Appointment> records = getAllAppointments();
        for (Appointment record : records) {
            apptView.printAppointmentDetails(record);
        }
    }

    public void printScheduledAppointments(List<Appointment> appointments){
        apptView.printScheduledAppointments(appointments);
    }
}