package main.csvUitls;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.model.Appointment;
import main.util.ApptStatus;
import main.util.TimeSlot;

public class AppointmentCSVManager {
    
    private static final String FILE_PATH = Config.APPOINTMENT_LIST_FILE_PATH;
    private static final String[] HEADER = {"apptId", "doctorId", "patientId", "timeslot", "status", "appointmentSlotId", "appointmentOutcomeId"};

    public List<Appointment> readAppointments() throws IOException {
        List<String[]> records = CSVHelper.readCSV(FILE_PATH);
        List<Appointment> appointments = new ArrayList<>();
        for (String[] record : records) {
            appointments.add(new Appointment(record[0], record[2], record[1], TimeSlot.getByTime(record[3]), ApptStatus.valueOf(record[4]), record[5], record[6]));
        }
        return appointments;
    }

    public void addAppointment(Appointment appointment) throws IOException {
        List<String[]> data = new ArrayList<>();
        data.add(new String[]{
            String.valueOf(appointment.getAppointmentId()),
            String.valueOf(appointment.getDoctorId()),
            String.valueOf(appointment.getPatientId()),
            appointment.getTimeSlot().getTime(),
            String.valueOf(appointment.getStatus()),
            String.valueOf(appointment.getAppointmentSlotId()),
            String.valueOf(appointment.getAppointmentOutcomeId()),
        });
        CSVHelper.appendCSV(FILE_PATH, data);
    }

    public void updateAppointment(String appointmentId, Appointment updatedAppointment) throws IOException {
        CSVHelper.updateCSVById(FILE_PATH, appointmentId, updatedAppointment.toCSVRecord(), HEADER);
    }

    public void deleteAppointment(String appointmentId) throws IOException {
        CSVHelper.deleteFromCSV(FILE_PATH, appointmentId, HEADER);
    }
}
