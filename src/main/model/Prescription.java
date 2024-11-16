package main.model;

import main.util.PrescriptionStatus;

public class Prescription {
    private String prescriptionId;
    private String medId;
    private String dosage;
    private String quantity;
    private PrescriptionStatus status;
    private String date;

    public Prescription(String prescriptionId, String medId, String dosage, String quantity,String date) {
        this.prescriptionId = prescriptionId;
        this.medId = medId;
        this.dosage = dosage;
        this.status = PrescriptionStatus.TO_BE_DISPENSED;
        this.date = date;
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

