package main.csvUitls;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.model.Prescription;
import main.util.PrescriptionStatus;

public class PrescriptionCSVManager {
    private static final String FILE_PATH = Config.PRESCRIPTION_LIST_FILE_PATH;
    private final String[] HEADER = {"prescriptionId,medId,dosage,qty,status,date"};

    public List<Prescription> loadPrescriptions() throws IOException {
        List<String[]> data = CSVHelper.readCSV(FILE_PATH);
        List<Prescription> prescriptions = new ArrayList<>();
        for (String[] row : data) {
            String prescriptionId = row[0];
            String medId = row[1];
            String dosage = row[2];
            String qty = row[3];
            PrescriptionStatus status = PrescriptionStatus.valueOf(row[4]);
            String date = row[5];
            String outcomeId = row[6];
            prescriptions.add(new Prescription(outcomeId, prescriptionId, medId, dosage, qty, status, date));
        }
        return prescriptions;
    }

    public void addPrescription(Prescription prescription) throws IOException {

        String[] data = new String[]{
            prescription.getOutcomeId(),
            prescription.getPrescriptionId(),
            prescription.getMedId(),
            prescription.getDosage(),
            prescription.getQuantity(),
            String.valueOf(prescription.getPrescriptionStatus()),
            prescription.getDate()
        };
    
        CSVHelper.appendSingleCSV(FILE_PATH, data);
    }

    public void updatePrescription(Prescription prescription) throws IOException {
        String[] updatedRecord = {
            prescription.getOutcomeId(),
            prescription.getPrescriptionId(),
            prescription.getMedId(),
            prescription.getDosage(),
            prescription.getQuantity(),
            String.valueOf(prescription.getPrescriptionStatus()),
            prescription.getDate()
        };

        CSVHelper.updateCSVById(FILE_PATH, prescription.getPrescriptionId(), updatedRecord, HEADER);
    }
    
}
