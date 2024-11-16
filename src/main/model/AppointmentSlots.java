// package main.model;

// import java.time.LocalDate;
// import java.time.LocalTime;
// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.List;
// import java.util.Scanner;
// import java.util.stream.Collectors;

// import main.util.ApptStatus;
// import main.util.TimeSlot;

// public class AppointmentSlots {
//     private String slotId;          // Unique identifier for the appointment slot
//     private LocalDate date;
//     private LocalTime time; // Date and time for the appointment
//     private String doctorId;           // Identifier for the doctor
//     private ApptStatus apptStatus;
//     private TimeSlot TimeSlot;
    
//     // Constructor
//     public AppointmentSlots(String slotId, LocalDate date, LocalTime time, String doctorId) {
//         this.slotId = slotId;
//         this.dateTime = dateTime;
//         this.doctorId = doctorId;
//         this.apptStatus = ApptStatus.AVAILABLE; // Default status is available
//     }

//     public LocalDateTime getDateTime() {
//         return dateTime;
//     }

//     public int getDoctorId() {
//         return doctorId;
//     }

//     public ApptStatus getAppointmentStatus() {
//         return apptStatus;
//     }

//     // Method to book the appointment slot
//     public void bookSlot(int patientId) {
//         if (apptStatus == ApptStatus.AVAILABLE) {
//             apptStatus = ApptStatus.CONFIRMED;
//             System.out.println("Appointment slot " + slotId + " booked for patient ID: " + patientId);
//         } else {
//             System.out.println("This slot is already booked or canceled.");
//         }
//     }

//     // Method to reschedule the appointment
//     public void rescheduleAppointment(int apptId, AppointmentSlots newSlot) {
//         if (this.apptStatus == ApptStatus.CONFIRMED) {
//             // Logic to update the appointment with new slot
//             System.out.println("Appointment " + apptId + " rescheduled to " + newSlot.getDateTime());
//             this.dateTime = newSlot.getDateTime();
//             // Additional logic may be needed to handle doctorId and status
            
            
            
//         } else {
//             System.out.println("Cannot reschedule. The appointment is not booked.");
//         }
//     }

//     // Method to cancel the appointment slot
//     public void cancelSlot() {
//         if (apptStatus == ApptStatus.CONFIRMED) {
//             apptStatus = ApptStatus.CANCELLED;
//             System.out.println("Appointment slot " + slotId + " has been cancelled.");
//         } else {
//             System.out.println("This slot is not booked or already cancelled.");
//         }
//     }
// }