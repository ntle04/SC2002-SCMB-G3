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

    
    /** 
     * @return String
     */
    public String getAvailabilitySlotId() {
        return availabilitySlotId;
    }

    public void setAvailabilitySlotId(String availabilitySlotId) {
        // Validate the ID format
        if (availabilitySlotId != null) {
            // Check if the ID follows the correct format (AV followed by digits)
            if (!availabilitySlotId.startsWith("AV")) {
                availabilitySlotId = "AV" + availabilitySlotId;
            }
            
            // Ensure the numeric part is 4 digits
            if (availabilitySlotId.length() > 2) {
                String numericPart = availabilitySlotId.substring(2);
                try {
                    int idNumber = Integer.parseInt(numericPart);
                    this.availabilitySlotId = String.format("AV%04d", idNumber);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid availability slot ID format. Numeric part required after 'AV'");
                }
            } else {
                throw new IllegalArgumentException("Invalid availability slot ID format. Must be 'AV' followed by numbers");
            }
        } else {
            this.availabilitySlotId = null;
        }
    }

    /*public String getPatientId(AppointmentSlot id){
        //List<Patient> list = patientapptmanager.loadpatients();
        //patientapptmanager.csv should have the the apptid and the patient id inside
        for(Patient patient : list){

        }


    }*/

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

    public void setStatus(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
    
}
