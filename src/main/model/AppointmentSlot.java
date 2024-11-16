package main.model;

import main.util.ApptStatus;
import main.util.TimeSlot;

public class AppointmentSlot {
     private String patientId;
    private ApptStatus status;
    private AvailabilitySlot availabilitySlot;

    public AppointmentSlot(String doctorId, String patientId, TimeSlot timeSlot, ApptStatus status) {
        this.patientId = patientId;
        this.status = status;
        this.availabilitySlot = new AvailabilitySlot(doctorId, timeSlot);
    }

    public String getPatientId() {
        return patientId;
    }

    public ApptStatus getStatus() {
        return status;
    }

    public AvailabilitySlot getAvailabilitySlot() {
        return availabilitySlot;
    }

    public void bookSlot(String patientId, AvailabilitySlot availabilitySlot){
        this.patientId = patientId;
        this.status = ApptStatus.PENDING;
        this.availabilitySlot = availabilitySlot;
    }

    public void confirmAppointment() {
        this.status = ApptStatus.CONFIRMED;
        availabilitySlot.setAvailability(false);
    }

    public void cancelAppointment() {
        this.status = ApptStatus.CANCELLED;
        availabilitySlot.setAvailability(true);
    }

    public void completeAppointment() {
        this.status = ApptStatus.COMPLETED;
    }
}