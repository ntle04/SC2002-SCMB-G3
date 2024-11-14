package main.model;

import java.util.Scanner;

import main.controller.*;
import main.util.Role;

public class Pharmacist extends Person{

    private ReplenishmentRequestController requestController = new ReplenishmentRequestController();
    // private PrescriptionController prescriptionController;
    // private AppointmentOutcomeController outcomeController;

    public Pharmacist() {
        super(Authenticate.getUserId(), Role.PHARMACIST);
    }

    public void updatePrescriptionStatus(){

    }

    public void submitReplenishmentRequest(){
        //temporary med id <-- to be replaced with printMedicineList()
        String medId = "Med0001";
        int qty;
        //temp person id <-- to be replaced with current login person id
        String personId = "M0001";

        Scanner sc = new Scanner(System.in);

        // System.out.print("Enter Medication ID: ");
        // medId = sc.next();
        System.out.print("Enter Quantity: ");
        qty = sc.nextInt();

        requestController.createRequest(medId, qty, personId);
    }

    
}
