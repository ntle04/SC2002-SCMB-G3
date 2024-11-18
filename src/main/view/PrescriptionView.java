package main.view;

import main.model.Prescription;
import java.util.List;

public class PrescriptionView {
    
    /** 
     * @param prescriptions
     */
    public void printPrescriptions(List<Prescription> prescriptions) {
        for (Prescription aPrescription : prescriptions){
        System.out.println("Prescription Details:");
        System.out.println("Prescription ID: " + aPrescription.getPrescriptionId());
        System.out.println("Medication ID: " + aPrescription.getMedId());
        System.out.println("Dosage: " + aPrescription.getDosage());
        System.out.println("Quantity: " + aPrescription.getQuantity());
        System.out.println("Status: " + aPrescription.getPrescriptionStatus());
        System.out.println("Date: " + aPrescription.getDate());
        System.out.println("----------------");
        }
    }

    public void printPrescription(Prescription prescription) {
        
        System.out.println("Prescription Details:");
        System.out.println("Prescription ID: " + prescription.getPrescriptionId());
        System.out.println("Medication ID: " + prescription.getMedId());
        System.out.println("Dosage: " + prescription.getDosage());
        System.out.println("Quantity: " + prescription.getQuantity());
        System.out.println("Status: " + prescription.getPrescriptionStatus());
        System.out.println("Date: " + prescription.getDate());
        
    }
}
