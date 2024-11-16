package main.model;

import main.csvUitls.Config;
import main.csvUitls.IdGenerator;
import main.util.TimeSlot;

public class AvailabilitySlot {
    private String availabilitySlotId;
    private String doctorId;
    private TimeSlot timeSlot;
    private boolean isAvailable;
    private static final String FILE_PATH = Config.AVAILABILITY_SLOTS_FILE_PATH;
    private static String idCounter = IdGenerator.generateNewId(FILE_PATH);
    
    // for csv import
    public AvailabilitySlot(String availabilitySlotId, String doctorId, TimeSlot timeSlot, boolean isAvailable) {
        this.availabilitySlotId = availabilitySlotId;
        this.doctorId = doctorId;
        this.timeSlot = timeSlot;
        this.isAvailable = isAvailable;
    }

    //for new records
    public AvailabilitySlot(String doctorId, TimeSlot timeSlot) {
        
        this.availabilitySlotId = "AV" + idCounter;
        this.doctorId = doctorId;
        this.timeSlot = timeSlot;
        this.isAvailable = true;

        //update counter
        idCounter = IdGenerator.generateNewId(FILE_PATH);
    }

    //for changing status
    public AvailabilitySlot(String doctorId, TimeSlot timeSlot, boolean isAvailable) {
        this.doctorId = doctorId;
        this.timeSlot = timeSlot;
        this.isAvailable = isAvailable;
    }

    public String getAvailabilitySlotId() {
        return availabilitySlotId;
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
