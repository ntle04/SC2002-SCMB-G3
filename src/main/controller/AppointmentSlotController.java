package main.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import main.csvUitls.AppointmentCSVManager;
import main.csvUitls.AppointmentSlotCSVManager;
import main.csvUitls.AvailabilitySlotCSVManager;
import main.model.Appointment;
import main.model.AppointmentSlot;
import main.model.AvailabilitySlot;
import main.util.ApptStatus;
import main.util.TimeSlot;
import main.view.AppointmentSlotView;


public class AppointmentSlotController {
    private List<AppointmentSlot> appointmentSlots;
    private AvailabilitySlotCSVManager availSlotCSVManager = new AvailabilitySlotCSVManager();
    private AppointmentSlotCSVManager apptSlotCSVManager = new AppointmentSlotCSVManager();
    private AppointmentSlotView view = new AppointmentSlotView();
    private AvailabilitySlotController availSlotController = new AvailabilitySlotController();
    private AppointmentCSVManager apptCSVManager = new AppointmentCSVManager();
    // private AppointmentController apptController = new AppointmentController();

    public AppointmentSlotController() {
        this.appointmentSlots = new ArrayList<>();
        loadAppointmentSlots();
    }


    private void loadAppointmentSlots() {
        try {
            appointmentSlots = apptSlotCSVManager.loadAppointmentSlots();
        } catch (IOException e) {
            System.out.println("Error loading appointment slots: " + e.getMessage());
        }


        System.out.println("Finish loading data");
    }


    public void bookAppointment(String patientId, String availabilitySlotId) {
        try {
            AvailabilitySlot availabilitySlot = availSlotController.getAvailabilitySlotById(availabilitySlotId);
            AppointmentSlot newSlot = new AppointmentSlot(availabilitySlot.getDoctorId(), patientId, availabilitySlotId);
            newSlot.bookSlot(newSlot.getAppointmentSlotId(), patientId, availabilitySlotId);
            System.out.println("Booking appt");
            apptSlotCSVManager.addAppointmentSlot(newSlot);
        } catch (IOException e) {
            System.out.println("Error booking appointment: " + e.getMessage());
        }
    }




    /*public void confirmAppointment(String appointmentSlotId) {
        for (AppointmentSlot slot : appointmentSlots) {
            // Check if the slot matches both doctorId, patientId and timeSlot
            if (slot.getAppointmentSlotId().equals(appointmentSlotId)) {
                try {
                    System.out.println("2: avail id: " + slot.getAvailabilitySlot().getAvailabilitySlotId());
                    slot.confirmAppointment();
                    System.out.println("after confirming appt which supp to change status, new avail status: " + slot.getAvailabilitySlot().isAvailable());
                    System.out.println("new appt slot status: " + slot.getStatus());
                    availSlotCSVManager.updateAvailabilitySlot(slot.getAvailabilitySlot());
                    apptSlotCSVManager.updateAppointmentSlot(slot);
                    apptCSVManager.addAppointment(new Appointment(slot.getAvailabilitySlot().getDoctorId(), slot.getPatientId(), slot.getAvailabilitySlot().getTimeSlot(), slot.getAppointmentSlotId(), null));
                    System.out.println("Appointment confirmed.");
                } catch (IOException e) {
                    System.out.println("Error confirming appointment: " + e.getMessage());
                }


                break;
            }
        }
    }*/


    public void confirmAppointment(String appointmentSlotId) {
        for (AppointmentSlot slot : appointmentSlots) {
            if (slot.getAppointmentSlotId().equals(appointmentSlotId)) {
                try {
                    // First update the availability slot
                    AvailabilitySlot availSlot = slot.getAvailabilitySlot();
                    if (availSlot != null) {
                        availSlot.setAvailability(false);
                        availSlotCSVManager.updateAvailabilitySlot(availSlot);
                       
                        // Then confirm the appointment
                        slot.confirmAppointment();
                        apptSlotCSVManager.updateAppointmentSlot(slot);
                       
                        // Create the appointment record
                        Appointment newAppointment = new Appointment(
                            availSlot.getDoctorId(),
                            slot.getPatientId(),
                            availSlot.getTimeSlot(),
                            slot.getAppointmentSlotId(),
                            null
                        );
                        apptCSVManager.addAppointment(newAppointment);
                       
                        System.out.println("Appointment confirmed successfully.");
                        return;
                    } else {
                        System.out.println("Error: Associated availability slot not found");
                    }
                } catch (IOException e) {
                    System.out.println("Error confirming appointment: " + e.getMessage());
                    e.printStackTrace();
                }
                break;
            }
        }
        System.out.println("Appointment slot not found with ID: " + appointmentSlotId);
    }
   


    // public void cancelAppointment(String appointmentId) {


    //     for (AppointmentSlot slot : appointmentSlots) {
    //         // Check if the slot matches both doctorId, patientId and timeSlot
    //         if (slot.ge().equals(appointmentId)) {
    //             try {
    //                 //change appt status & update appt csv
    //                 apptController.cancelAppointment(slot);
    //                 //change appt slot & avail slot status in model
    //                 slot.cancelAppointment();


    //                 //update in csv
                   


    //                 apptSlotCSVManager.updateAppointmentSlot(slot);


    //                 System.out.println("Appointment cancelled.");
    //             } catch (IOException e) {
    //                 System.out.println("Error cancelling appointment: " + e.getMessage());
    //             }


    //             break;
    //         }
    //     }
       
    // }


    public void cancelAppointmentSlot(String appointmentSlotId) {
        try {
            AppointmentSlot appointmentSlot = getAppointmentSlotById(appointmentSlotId);
            if(appointmentSlot!=null){
                //update appt slot status
                appointmentSlot.setStatus(ApptStatus.CANCELLED);
                //update appt csv
                apptSlotCSVManager.updateAppointmentSlot(appointmentSlot);

                System.out.println("Appointment slot canceled successfully.");
            }else{
                System.out.println("Appointment slot not found.");
            }
            return;
        } catch (IOException e) {
            System.out.println("Error canceling appointment: " + e.getMessage());
        }
    }


    public List<AppointmentSlot> filterSlotsByDoctorId(String doctorId) {
        List<AppointmentSlot> filteredSlots = new ArrayList<>();
        for (AppointmentSlot slot : appointmentSlots) {
            if (slot.getAvailabilitySlot().getDoctorId().trim().equalsIgnoreCase(doctorId.trim())) {
                System.out.println(slot.getAvailabilitySlot().getAvailabilitySlotId());
                filteredSlots.add(slot);
            }
        }
        return filteredSlots;
    }


    public AppointmentSlot getAppointmentSlotById(String slotId) {
        loadAppointmentSlots(); //get latest data
        for (AppointmentSlot slot : appointmentSlots) {
            System.out.println("ID: " + slot.getAppointmentSlotId());
            System.out.println("params: " + slotId);
            if (slot.getAppointmentSlotId().equals(slotId)) {
                return slot;
            }
        }
        System.out.println("Appointment slot with specified ID not found");
        return null;
    }


    public void printPendingAppointmentSlots(){
        view.printPendingAppointmentSlots(filterSlotsByDoctorId(Authenticate.getLoggedInUser().getId()));
    }
   
}





