package main.csvUitls;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.model.AppointmentSlot;
import main.util.ApptStatus;
import main.util.TimeSlot;

public class AppointmentSlotCSVManager {

    private static final String FILE_PATH = Config.APPOINTMENT_SLOTS_FILE_PATH;
    private final String[] HEADER = {"doctorId,patientId,timeslot,status"};

    public List<AppointmentSlot> loadAppointmentSlots() throws IOException {
        List<String[]> data = CSVHelper.readCSV(FILE_PATH);
        List<AppointmentSlot> appointmentSlots = new ArrayList<>();
        for (String[] row : data) {
            String doctorId = row[0];
            String patientId = row[1];
            TimeSlot timeSlot = TimeSlot.getByTime(row[2]);
            ApptStatus status = ApptStatus.valueOf(row[3]);
            appointmentSlots.add(new AppointmentSlot(doctorId, patientId, timeSlot, status));
        }
        return appointmentSlots;
    }

    public void addAppointmentSlot(AppointmentSlot appointmentSlot) throws IOException {
        List<String[]> data = new ArrayList<>();
        data.add(new String[]{
            String.valueOf(appointmentSlot.getAvailabilitySlot().getDoctorId()),
            String.valueOf(appointmentSlot.getPatientId()),
            appointmentSlot.getAvailabilitySlot().getTimeSlot().getTime(),
            String.valueOf(appointmentSlot.getStatus())
        });
        CSVHelper.appendCSV(FILE_PATH, data);
    }

    public void updateAppointmentSlot(AppointmentSlot appointmentSlot) throws IOException {
        String[] updatedRecord = {
            appointmentSlot.getAvailabilitySlot().getDoctorId(),
            appointmentSlot.getPatientId(),
            appointmentSlot.getAvailabilitySlot().getTimeSlot().getTime(),
            String.valueOf(appointmentSlot.getStatus()),
        };

        CSVHelper.updateCSVWithRecord(FILE_PATH, updatedRecord, HEADER);
    }
    
}