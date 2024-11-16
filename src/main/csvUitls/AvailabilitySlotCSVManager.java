package main.csvUitls;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.model.AvailabilitySlot;
import main.util.TimeSlot;

public class AvailabilitySlotCSVManager {

    private static final String FILE_PATH = Config.AVAILABILITY_SLOTS_FILE_PATH;

    public List<AvailabilitySlot> loadAvailabilities() throws IOException {
        List<String[]> data = CSVHelper.readCSV(FILE_PATH);
        List<AvailabilitySlot> availabilitySlots = new ArrayList<>();
        for (String[] row : data) {
            String doctorId = row[0];
            TimeSlot timeSlot = TimeSlot.getByTime(row[1]);
            boolean isAvailable = Boolean.parseBoolean(row[2]);
            availabilitySlots.add(new AvailabilitySlot(doctorId, timeSlot, isAvailable));
        }
        return availabilitySlots;
    }

    public void addAvailabilities(List<AvailabilitySlot> availabilitySlots) throws IOException {
        List<String[]> data = new ArrayList<>();
        for (AvailabilitySlot slot : availabilitySlots) {
            data.add(new String[]{
                String.valueOf(slot.getDoctorId()),
                slot.getTimeSlot().getTime(),
                String.valueOf(slot.isAvailable())
            });
        }
        CSVHelper.appendCSV(FILE_PATH, data);
    }

    public void updateAvailabilitySlots(List<AvailabilitySlot> availabilitySlots) throws IOException {
        List<String[]> data = new ArrayList<>();
        for (AvailabilitySlot slot : availabilitySlots) {
            data.add(new String[]{
                String.valueOf(slot.getDoctorId()),
                slot.getTimeSlot().getTime(),
                String.valueOf(slot.isAvailable())
            });
        }
        CSVHelper.appendCSV(FILE_PATH, data);
    }
    
}
