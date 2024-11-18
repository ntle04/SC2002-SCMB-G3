package main.model;

import java.util.Scanner;

import main.controller.*;
import main.util.Role;
import main.view.PrescriptionView;

public class Pharmacist extends Person{
    PrescriptionView view = new PrescriptionView();
    private ReplenishmentRequestController requestController = new ReplenishmentRequestController();
    //private PrescriptionController prescriptionController = new PrescriptionController(outcomeController.getPrescriptions(), view);
    private AppointmentOutcomeController outcomeController;
    //private AppointmentController apptcontroller = new AppointmentController(null, null);
    Scanner sc = new Scanner(System.in);

    public Pharmacist() {
        super(Authenticate.getLoggedInUser().getId(), null, Role.PHARMACIST);
    }

    /*public void updatePrescriptionStatus(PrescriptionStatus status, String prescriptionId){
        prescriptionController.updateStatus(prescriptionId,status);
        List<Appointment> appt = apptcontroller.viewAllCompletedAppts();
        Appointment selectedId = sc.nextLine();
        for (Appointment appointment : appt)
        {
            if(appointment.getAppointmentId().equals(selectedId)){

            }
        }
    }*/

    public void submitReplenishmentRequest(){
        String medId;
        int qty;
        String personId = Authenticate.getLoggedInUser().getId();

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Medication ID: ");
        medId = sc.next();
        System.out.print("Enter Quantity: ");
        qty = sc.nextInt();

        requestController.createRequest(medId, qty, personId);
    }

    
}
