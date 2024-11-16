package main.model;

import main.util.TimeSlot;

public class AvailabilitySlot {

    private String doctorId;
    private TimeSlot timeSlot;
    private boolean isAvailable;
    
    
    // Constructor
    public AvailabilitySlot(String doctorId, TimeSlot timeSlot) {
        this.doctorId = doctorId;
        this.timeSlot = timeSlot;
        this.isAvailable = true;
    }

    public AvailabilitySlot(String doctorId, TimeSlot timeSlot, boolean isAvailable) {
        this.doctorId = doctorId;
        this.timeSlot = timeSlot;
        this.isAvailable = isAvailable;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailability(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
    
}
