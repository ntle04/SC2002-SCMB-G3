package main.controller;

import main.csvUitls.PrescriptionCSVManager;
import main.model.Prescription;
import main.util.PrescriptionStatus;
import main.view.PrescriptionView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionController {
    private List<Prescription> prescriptions = new ArrayList<>();
    private PrescriptionView view = new PrescriptionView();
    private PrescriptionCSVManager csvManager = new PrescriptionCSVManager();

    public PrescriptionController(){
        
    }

    public PrescriptionController(List<Prescription> prescriptions, PrescriptionView view){
        this.prescriptions = prescriptions;
        this.view = view;
    }

    private void getAllPrescriptions() {
        try {
            prescriptions = csvManager.loadPrescriptions();
        } catch (IOException e) {
            System.out.println("Error loading prescriptions: " + e.getMessage());
        }
    }


    public void viewPrescriptions(){
        view.printPrescriptions(prescriptions);
    }

    public void addPrescription(Prescription prescription){
        prescriptions.add(prescription);
    }

    public void viewPrescription(Prescription prescription){
        view.printPrescription(prescription);
    }

    private Prescription findPrescriptionById(String prescriptionId) {
        for (Prescription prescription : prescriptions) {
            if (prescription.getPrescriptionId().equals(prescriptionId)) {
                return prescription;
            }
        }
        return null;  // Return null if not found
    }

    // Method to update the status of the prescription
    public void updateStatus(String prescriptionId, PrescriptionStatus newStatus) {
        Prescription selected = findPrescriptionById(prescriptionId);
        selected.setPrescriptionStatus(newStatus);
        view.printPrescription(selected);
    }

    public List<Prescription> findPrescriptionsByApptOutcomeId(String apptOutcomeId){
        List<Prescription> filteredList = new ArrayList<>();
        for (Prescription prescription : prescriptions) {
            if (prescription.getOutcomeId().equals(apptOutcomeId)) {
                filteredList.add(prescription);
            }
        }
        return filteredList;
    }


}
