package main.csvUitls;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.model.AppointmentSlot;
import main.util.ApptStatus;

public class AppointmentSlotCSVManager {

    private static final String FILE_PATH = Config.APPOINTMENT_SLOTS_FILE_PATH;
    private final String[] HEADER = {"appointmentSlotId,doctorId,patientId,timeslot,status,availabilitySlotId"};

    
    /** 
     * @return List<AppointmentSlot>
     * @throws IOException
     */
    public List<AppointmentSlot> loadAppointmentSlots() throws IOException {
        List<String[]> data = CSVHelper.readCSV(FILE_PATH);
        List<AppointmentSlot> appointmentSlots = new ArrayList<>();
        
        for (String[] row : data) {
            String appointmentSlotId = row[0];
            String availabilitySlotId = row[5];
            String patientId = row[2];
            ApptStatus status = ApptStatus.valueOf(row[4]);
            
            // Use the constructor that takes appointmentSlotId
            appointmentSlots.add(new AppointmentSlot(
                appointmentSlotId,    // Use the ID from CSV
                availabilitySlotId,
                patientId,
                status
            ));
            
        }
        return appointmentSlots;
    }

    public void addAppointmentSlot(AppointmentSlot appointmentSlot) throws IOException {
        List<String[]> data = new ArrayList<>();
        data.add(new String[]{
            String.valueOf(appointmentSlot.getAppointmentSlotId()),
            String.valueOf(appointmentSlot.getAvailabilitySlot().getDoctorId()),
            String.valueOf(appointmentSlot.getPatientId()),
            appointmentSlot.getAvailabilitySlot().getTimeSlot().getTime(),
            String.valueOf(appointmentSlot.getStatus()),
            String.valueOf(appointmentSlot.getAvailabilitySlotId()),
        });
        CSVHelper.appendCSV(FILE_PATH, data);
    }

    public void updateAppointmentSlot(AppointmentSlot appointmentSlot) throws IOException {

        String[] updatedRecord = {
            String.valueOf(appointmentSlot.getAppointmentSlotId()),
            String.valueOf(appointmentSlot.getAvailabilitySlot().getDoctorId()),
            String.valueOf(appointmentSlot.getPatientId()),
            appointmentSlot.getAvailabilitySlot().getTimeSlot().getTime(),
            String.valueOf(appointmentSlot.getStatus()),
            String.valueOf(appointmentSlot.getAvailabilitySlotId()),
        };

        CSVHelper.updateCSVById(FILE_PATH, appointmentSlot.getAppointmentSlotId(), updatedRecord, HEADER);
    }
    
}
