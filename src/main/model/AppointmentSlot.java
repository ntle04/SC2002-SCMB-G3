package main.model;

import main.controller.AvailabilitySlotController;
import main.csvUitls.Config;
import main.csvUitls.IdGenerator;
import main.util.ApptStatus;
import main.util.TimeSlot;

/*public class AppointmentSlot {
    private String appointmentSlotId;
    private String availabilitySlotId;
    private String patientId;
    private ApptStatus status;
    private AvailabilitySlotController availSlotController = new AvailabilitySlotController();
    // private AvailabilitySlot availabilitySlot;
    private static final String FILE_PATH = Config.APPOINTMENT_SLOTS_FILE_PATH;
    private static String idCounter = IdGenerator.generateNewId(FILE_PATH);

    //for csv import
    public AppointmentSlot(String appointmentSlotId, String availabilitySlotId, String patientId, ApptStatus status) {
        this.appointmentSlotId = appointmentSlotId;
        this.availabilitySlotId = availabilitySlotId;
        this.patientId = patientId;
        this.status = status;
    }

    // public AppointmentSlot(String doctorId, String patientId, TimeSlot timeSlot, ApptStatus status) {
    //     this.appointmentSlotId = "AS" + idCounter;
    //     this.patientId = patientId;
    //     this.status = status;
    //     this.availabilitySlot = new AvailabilitySlot(doctorId, timeSlot);
        
    //     //update counter
    //     idCounter = IdGenerator.generateNewId(FILE_PATH);
    // }

    //new record
    public AppointmentSlot(String doctorId, String patientId, String availabilitySlotId) {
        this.appointmentSlotId = "AS" + idCounter;
        this.patientId = patientId;
        this.status = ApptStatus.PENDING;
        this.availabilitySlotId = availabilitySlotId;
        
        //update counter
        idCounter = IdGenerator.generateNewId(FILE_PATH);
    }*/


    public class AppointmentSlot {

        private static final String FILE_PATH = Config.APPOINTMENT_SLOTS_FILE_PATH;
        
        private static String idCounter;
        private String appointmentSlotId;
        private String availabilitySlotId;
        private String patientId;
        private ApptStatus status;
        private AvailabilitySlot availabilitySlot;

        AvailabilitySlotController availSlotController = new AvailabilitySlotController();

        //for csv
        public AppointmentSlot(String appointmentSlotId, String availabilitySlotId, String patientId, ApptStatus status) {
            idCounter = IdGenerator.generateNewId(FILE_PATH);
            this.appointmentSlotId = "AS" + idCounter;
            this.availabilitySlotId = availabilitySlotId;
            this.patientId = patientId;
            this.status = status;
            // Load the availability slot when creating appointment slot
            // AvailabilitySlotController availSlotController = new AvailabilitySlotController();
            // this.availabilitySlot = availSlotController.getAvailabilitySlotById(availabilitySlotId);
        }

        
        // Modified constructor
        // public AppointmentSlot(String appointmentSlotId, String availabilitySlotId, String patientId, ApptStatus status) {
        //     this.appointmentSlotId = "AS" + idCounter;
        //     this.availabilitySlotId = availabilitySlotId;
        //     this.patientId = patientId;
        //     this.status = status;
        //     idCounter = IdGenerator.generateNewId(FILE_PATH);
        //     // Load the availability slot when creating appointment slot
        //     AvailabilitySlotController availSlotController = new AvailabilitySlotController();
        //     this.availabilitySlot = availSlotController.getAvailabilitySlotById(availabilitySlotId);
        // }

        //for new records
        public AppointmentSlot(String doctorId, String patientId, String availabilitySlotId) {
            idCounter = IdGenerator.generateNewId(FILE_PATH);
            this.appointmentSlotId = "AS" + idCounter;
            this.patientId = patientId;
            this.status = ApptStatus.PENDING;
            this.availabilitySlotId = availabilitySlotId;
            
            //update counter
            idCounter = IdGenerator.generateNewId(FILE_PATH);
        }
    
        public void confirmAppointment() {
            if (this.availabilitySlot != null) {
                this.status = ApptStatus.CONFIRMED;
                this.availabilitySlot.setAvailability(false); // Mark as not available
            } else {
                throw new RuntimeException("AvailabilitySlot not found for id: " + this.availabilitySlotId);
            }
        }
    
    

    public String getAppointmentSlotId() {
        return appointmentSlotId;
    }

    public String getAvailabilitySlotId(){
        return availabilitySlotId;
    }

    public String getPatientId() {
        return patientId;
    }

    public ApptStatus getStatus() {
        return status;
    }

    public void setStatus(ApptStatus status) {
        this.status = status;
    }

    public AvailabilitySlot getAvailabilitySlot() {
        return availSlotController.getAvailabilitySlotById(getAvailabilitySlotId());
    }

    public void bookSlot(String appointmentSlotId, String patientId, String availabilitySlotId){
        this.appointmentSlotId = appointmentSlotId;
        this.patientId = patientId;
        this.status = ApptStatus.PENDING;
        this.availabilitySlotId = availabilitySlotId;
    }

    /*public void confirmAppointment() {
        AvailabilitySlot availabilitySlot = availSlotController.getAvailabilitySlotById(availabilitySlotId);
        availabilitySlot.setAvailability(false);
        this.status = ApptStatus.CONFIRMED;
        System.out.println("from appt slot model: status: " + availabilitySlot.isAvailable());
    }*/

    // public void cancelAppointment() {
    //     this.status = ApptStatus.CANCELLED;
    //     availSlotController.getAvailabilitySlotById(availabilitySlotId).setAvailability(true);
    // }

    public void completeAppointment() {
        this.status = ApptStatus.COMPLETED;
    }
}
