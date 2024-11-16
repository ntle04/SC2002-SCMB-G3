package main.view;

import java.util.List;

import main.model.AppointmentSlot;
import main.model.AvailabilitySlot;
import main.model.Doctor;
import main.model.Patient;

public class DoctorView {
    public void displayPatientRecords(Patient patient)
    {
        System.out.println("Patient ID: " + patient.getId());
        System.out.println("Name: " + patient.getContact().getName());
        System.out.println("DOB: " + patient.getContact().getDOB());
        System.out.println("Gender: " + patient.getContact().getGender());
        System.out.println("Email: " + patient.getContact().getEmail());
        System.out.println("Blood Type: " + patient.getPatientBloodType());
        System.out.println("Diagnoses: " + patient.getDiagnosis());
        System.out.println("Treatments: " + patient.getTreatment(patient.getId()));
    }


    public void displaySchedule(Doctor doctor) {
    // Output the doctor's name and ID
    System.out.println("Schedule for Dr. " + doctor.getContact().getName() + " (ID: " + doctor.getId() + "):");
   
    // Retrieve the availability slots for this doctor
    List<AvailabilitySlot> slots = doctor.getAvailability();
   
    // If there are no slots, print a message
    if (slots == null || slots.isEmpty()) {
        System.out.println("No availability.");
    } else {
        // Iterate over each availability slot and display its information
        for (AvailabilitySlot slot : slots) {
            // Check if the slot is available
            if (slot.isAvailable()) {
                System.out.println("Available at: " + slot.getTimeSlot() + " | Status: Available");
            } else {
                System.out.println("Not available at: " + slot.getTimeSlot() + " | Status: Not Available");
            }
        }
    }
}

    public void displayUpcomingAppointments(List<AppointmentSlot> upcomingAppointments, List<Patient> patients,Doctor doctor) {
        if (upcomingAppointments.isEmpty()) {
            System.out.println("No upcoming appointments.");
        } else {
            System.out.println("=== Upcoming Appointments ===");
            for (int i = 0; i < upcomingAppointments.size(); i++) {
                AppointmentSlot appointmentSlot = upcomingAppointments.get(i);
                // Find the patient for this appointment
                Patient patient = doctor.getPatientById(appointmentSlot.getPatientId());
                if (patient != null) {
                    System.out.println((i + 1) + ". Appointment with Patient ID: " + patient.getId() +
                            ", Patient Name: " + patient.getContact().getName() +
                            ", Time: " + appointmentSlot.getAvailabilitySlot().getTimeSlot() +
                            ", Status: " + appointmentSlot.getStatus());
                }
            }
        }
}

}