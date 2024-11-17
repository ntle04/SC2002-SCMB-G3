package main.model;

import main.csvUitls.Config;
import main.csvUitls.IdGenerator;
import main.util.PrescriptionStatus;

public class Prescription {
    private String prescriptionId;
    private String medId;
    private String dosage;
    private String quantity;
    private PrescriptionStatus status;
    private String date;

    private static final String FILE_PATH = Config.PRESCRIPTION_LIST_FILE_PATH;
    private static String idCounter = IdGenerator.generateNewId(FILE_PATH);

    //csv
    public Prescription(String prescriptionId, String medId, String dosage, String quantity, PrescriptionStatus status, String date) {
        this.prescriptionId = prescriptionId;
        this.medId = medId;
        this.dosage = dosage;
        this.quantity = quantity;
        this.status = status;
        this.date = date;
    }
    
    //new record
    public Prescription(String medId, String dosage, String quantity, String date) {
        this.prescriptionId = "PR" + idCounter;
        this.medId = medId;
        this.dosage = dosage;
        this.status = PrescriptionStatus.TO_BE_DISPENSED;
        this.date = date;

        //update counter
        idCounter = IdGenerator.generateNewId(FILE_PATH);
    }

    public String getPrescriptionId(){
        return prescriptionId;
    }
    
    public void setPrescriptionId(String id){
        prescriptionId = id;
    }
    
    public String getMedId(){
        return medId;
    }
    
    public void setMedId(String id){
        medId = id;
    }
    
    
    public String getDosage(){
        return dosage;
    }
    
    public void setDosage(String dosage){
        this.dosage = dosage;
    }

    public String getQuantity(){
        return quantity;
    }

    public void setQuantity(String quantity){
        this.quantity = quantity;
    }
    
    public PrescriptionStatus getPrescriptionStatus(){
        return status;
    }

    public void setPrescriptionStatus(PrescriptionStatus status){
        this.status = status;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }
    
    
    
}

