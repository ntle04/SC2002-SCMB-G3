// package main.view;

// import main.model.AppointmentOutcome;
// import main.model.Prescription;

// public class AppointmentOutcomeView {

//     // Method to print the details of the appointment outcome
//     public void printApptOutcome(AppointmentOutcome outcome) {
//         System.out.println("Appointment Outcome Details:");
//         System.out.println("-----------------------------");
//         System.out.println("Service Type: " + outcome.getServiceType());
        
//         // Print prescriptions if available
//         if (outcome.getPrescriptions() != null && !outcome.getPrescription().isEmpty()) {
//             System.out.println("Prescriptions:");
//             for (Prescriptions prescription : outcome.getPrescription()) {
//                 System.out.println("- " + prescription.toString()); // Assuming Prescriptions has a meaningful toString method
//             }
//         } else {
//             System.out.println("No prescriptions required.");
//         }

//         System.out.println("Notes: " + outcome.getNotes());
//         System.out.println("-----------------------------");
//     }
// }