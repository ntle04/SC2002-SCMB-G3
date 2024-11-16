package main.csvUitls;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.model.AvailabilitySlot;
import main.util.TimeSlot;

public class AvailabilitySlotCSVManager {

    private static final String FILE_PATH = Config.AVAILABILITY_SLOTS_FILE_PATH;
    private final String[] HEADER = {"availabilitySlotId,doctorId,timeslot,isAvailable"};

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

    public void addAvailabilities(List<AvailabilitySlot> availabilitySlots) throws IOException {
        List<String[]> data = new ArrayList<>();
        for (AvailabilitySlot slot : availabilitySlots) {
            data.add(new String[]{
                String.valueOf(slot.getAvailabilitySlotId()),
                String.valueOf(slot.getDoctorId()),
                slot.getTimeSlot().getTime(),
                String.valueOf(slot.isAvailable())
            });
        }
        CSVHelper.appendCSV(FILE_PATH, data);
    }

    public void addAvailability(AvailabilitySlot availabilitySlot) throws IOException {
        // Prepare a single record for the slot
        String[] data = new String[]{
            String.valueOf(availabilitySlot.getAvailabilitySlotId()),
            String.valueOf(availabilitySlot.getDoctorId()),
            availabilitySlot.getTimeSlot().getTime(),
            String.valueOf(availabilitySlot.isAvailable())
        };
    
        // Append the record to the CSV
        CSVHelper.appendSingleCSV(FILE_PATH, data);
    }
    

    public void updateAvailabilitySlots(List<AvailabilitySlot> availabilitySlots) throws IOException {
        List<String[]> data = new ArrayList<>();
        for (AvailabilitySlot slot : availabilitySlots) {
            data.add(new String[]{
                String.valueOf(slot.getAvailabilitySlotId()),
                String.valueOf(slot.getDoctorId()),
                slot.getTimeSlot().getTime(),
                String.valueOf(slot.isAvailable())
            });
        }
        CSVHelper.appendCSV(FILE_PATH, data);
    }

    public void updateAvailabilitySlot(AvailabilitySlot availabilitySlot) throws IOException {
        System.out.println("in avail slot csv");
        System.out.println("status check: " + availabilitySlot.isAvailable());


        String[] updatedRecord = {
            String.valueOf(availabilitySlot.getAvailabilitySlotId()),
            String.valueOf(availabilitySlot.getDoctorId()),
            availabilitySlot.getTimeSlot().getTime(),
            String.valueOf(availabilitySlot.isAvailable()),
        };

        System.out.println("new status: " + availabilitySlot.isAvailable()); 

        CSVHelper.updateCSVById(FILE_PATH, availabilitySlot.getAvailabilitySlotId(), updatedRecord, HEADER);
    }
    
}
