package main.csvUitls;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.controller.PrescriptionController;
import main.model.AppointmentOutcome;
import main.model.Prescription;
import main.util.TimeSlot;

public class AppointmentOutcomeCSVManager {

    private static final String FILE_PATH = Config.APPOINTMENT_OUTCOME_FILE_PATH;
    private final String[] HEADER = {"outcomeId,appointmentId,appointmentDate,appointmentTime,serviceType,notes,patientId,doctorId"};

    PrescriptionController prescriptionController = new PrescriptionController();

    
    /** 
     * @return List<AppointmentOutcome>
     * @throws IOException
     */
    public List<AppointmentOutcome> loadAppointmentOutcomes() throws IOException {
        List<String[]> data = CSVHelper.readCSV(FILE_PATH);
        List<AppointmentOutcome> appointmentOutcomes = new ArrayList<>();
        for (String[] row : data) {
            String outcomeId = row[0];
            String appointmentId = row[1];
            String appointmentDate = row[2];
            TimeSlot appointmentTime = TimeSlot.getByTime(row[3]);
            String serviceType = row[4]; 
            String notes = row[5];
            String doctorId = row[6];
            String patientId = row[7];

            List<Prescription> prescriptions = prescriptionController.findPrescriptionsByApptOutcomeId(row[0]);
            appointmentOutcomes.add(new AppointmentOutcome(outcomeId, appointmentId, appointmentDate, appointmentTime, serviceType, prescriptions, notes, doctorId, patientId));
        }
        return appointmentOutcomes;
    }


    // public List<AppointmentOutcome> loadAppointmentOutcomes() throws IOException {
    	
    // 	try {
    // 		List<String[]> data = CSVHelper.readCSV(FILE_PATH);  
    //         for (String[] row : data) {
    //             System.out.println(Arrays.toString(row));
    //         }
    //         System.out.println("csv manager size1: " + data.size());     
    //         List<AppointmentOutcome> appointmentOutcomes = new ArrayList<>();
    //         for (String[] row : data) {
    //         	System.out.println("cin for loop: " + row[0]);

    //             String outcomeId = row[0];
    //             String appointmentId = row[1];
    //             String appointmentDate = row[2];
    //             TimeSlot appointmentTime = TimeSlot.getByTime(row[3]);
    //             String serviceType = row[4]; 
    //             String notes = row[5];
    //             String doctorId = row[6];
    //             String patientId = row[7];

    //             List<Prescription> prescriptions = prescriptionController.findPrescriptionsByApptOutcomeId(outcomeId);
                
    //             appointmentOutcomes.add(new AppointmentOutcome(outcomeId,appointmentId, appointmentDate, appointmentTime, serviceType, prescriptions, notes, doctorId, patientId));             
                
    //         }

    //         System.out.println("csv manager size: " + appointmentOutcomes.size());
    //         return appointmentOutcomes;
    		
    // 	} catch (Exception e) {
    //         return new ArrayList<>();
	// 	}
    // }

    public void addAppointmentOutcome(AppointmentOutcome appointmentOutcome) throws IOException {
        List<String[]> data = new ArrayList<>();
        data.add(new String[]{
            String.valueOf(appointmentOutcome.getOutcomeId()),
            String.valueOf(appointmentOutcome.getAppointmentId()),
            String.valueOf(appointmentOutcome.getAppointmentDate()),
            String.valueOf(appointmentOutcome.getAppointmentTime().getTime()),
            String.valueOf(appointmentOutcome.getServiceType()),
            String.valueOf(appointmentOutcome.getNotes()),
            String.valueOf(appointmentOutcome.getDoctorId()),
            String.valueOf(appointmentOutcome.getPatientId()),
        });
        CSVHelper.appendCSV(FILE_PATH, data);
    }

    public void updateAppointmentOutcome(AppointmentOutcome appointmentOutcome) throws IOException {

        String[] updatedRecord = {
            String.valueOf(appointmentOutcome.getOutcomeId()),
			 String.valueOf(appointmentOutcome.getAppointmentId()),
	         String.valueOf(appointmentOutcome.getAppointmentDate()),
	         String.valueOf(appointmentOutcome.getAppointmentTime().getTime()),
	         String.valueOf(appointmentOutcome.getServiceType()),
	         String.valueOf(appointmentOutcome.getNotes()),
	         String.valueOf(appointmentOutcome.getDoctorId()),
	         String.valueOf(appointmentOutcome.getPatientId()),
        };

        CSVHelper.updateCSVById(FILE_PATH, appointmentOutcome.getAppointmentId(), updatedRecord, HEADER);
    }
    
}
