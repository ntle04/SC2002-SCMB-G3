package main.csvUitls;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.model.AvailabilitySlot;
import main.util.TimeSlot;



public class AvailabilitySlotCSVManager {
    private static final String FILE_PATH = Config.AVAILABILITY_SLOTS_FILE_PATH;
    private final String[] HEADER = {"availabilitySlotId,doctorId,timeslot,isAvailable"};

    
    /** 
     * @param doctorId
     * @param timeSlot
     * @return boolean
     * @throws IOException
     */
    // Helper method to check if a slot already exists
    private boolean slotExists(String doctorId, TimeSlot timeSlot) throws IOException {
        List<AvailabilitySlot> existingSlots = loadAvailabilities();
        return existingSlots.stream()
            .anyMatch(slot -> slot.getDoctorId().equals(doctorId) 
                && slot.getTimeSlot().getTime().equals(timeSlot.getTime()));
    }

    public List<AvailabilitySlot> loadAvailabilities() throws IOException {
        List<String[]> data = CSVHelper.readCSV(FILE_PATH);
        List<AvailabilitySlot> availabilitySlots = new ArrayList<>();
        for (String[] row : data) {
            String availabilitySlotId = row[0];
            String doctorId = row[1];
            TimeSlot timeSlot = TimeSlot.getByTime(row[2]);
            boolean isAvailable = Boolean.parseBoolean(row[3]);
            availabilitySlots.add(new AvailabilitySlot(availabilitySlotId, doctorId, timeSlot, isAvailable));
        }
        return availabilitySlots;
    }

    public void addAvailability(AvailabilitySlot availabilitySlot) throws IOException {
        // Check if slot already exists
        if (slotExists(availabilitySlot.getDoctorId(), availabilitySlot.getTimeSlot())) {
            // If it exists, update it instead of adding
            updateAvailabilitySlot(availabilitySlot);
            return;
        }

        // Generate new ID properly
        String newId = "AV" + IdGenerator.generateNewId(FILE_PATH);
        availabilitySlot.setAvailabilitySlotId(newId);

        String[] data = new String[]{
            availabilitySlot.getAvailabilitySlotId(),
            availabilitySlot.getDoctorId(),
            availabilitySlot.getTimeSlot().getTime(),
            String.valueOf(availabilitySlot.isAvailable())
        };
    
        CSVHelper.appendSingleCSV(FILE_PATH, data);
    }

    public void updateAvailabilitySlot(AvailabilitySlot availabilitySlot) throws IOException {
        String[] updatedRecord = {
            availabilitySlot.getAvailabilitySlotId(),
            availabilitySlot.getDoctorId(),
            availabilitySlot.getTimeSlot().getTime(),
            String.valueOf(availabilitySlot.isAvailable())
        };

        CSVHelper.updateCSVById(FILE_PATH, availabilitySlot.getAvailabilitySlotId(), updatedRecord, HEADER);
    }

    public void updateAvailabilitySlots(List<AvailabilitySlot> availabilitySlot) throws IOException {
        for(AvailabilitySlot slot : availabilitySlot){
        String[] updatedRecord = {
            slot.getAvailabilitySlotId(),
            slot.getDoctorId(),
            slot.getTimeSlot().getTime(),
            String.valueOf(slot.isAvailable())
        };

        CSVHelper.updateCSVById(FILE_PATH, slot.getAvailabilitySlotId(), updatedRecord, HEADER);
    }
    }

    public void addAvailabilities(List<AvailabilitySlot> availabilitySlots) throws IOException {
        for (AvailabilitySlot slot : availabilitySlots) {
            addAvailability(slot);  // Use the single add method to handle duplicates
        }
    }
}
