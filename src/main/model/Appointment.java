package main.model;

import main.csvUitls.Config;
import main.csvUitls.IdGenerator;
import main.util.ApptStatus;
import main.util.TimeSlot;

public class Appointment {
	private String appointmentId;
	private String appointmentSlotId;
	private String appointmentOutcomeId;
	private String patientId;
	private String doctorId;
	private TimeSlot timeSlot;
	private ApptStatus status;

	private static final String FILE_PATH = Config.APPOINTMENT_LIST_FILE_PATH;
    private static String idCounter = IdGenerator.generateNewId(FILE_PATH);

	public Appointment(String appointmentId, String patientId, String doctorId, TimeSlot timeslot, ApptStatus status, String appointmentSlotId, String appointmentOutcomeId) {
		this.appointmentId = appointmentId;
		this.appointmentSlotId = appointmentSlotId;
		this.appointmentOutcomeId = appointmentOutcomeId;
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.timeSlot = timeslot;
		this.status = status; 
	}

	//new record
    public Appointment(String patientId, String doctorId, TimeSlot timeslot, String appointmentSlotId, String appointmentOutcomeId) {
        this.appointmentSlotId = "APP" + idCounter;
		this.appointmentOutcomeId = appointmentOutcomeId;
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.timeSlot = timeslot;
        this.status = ApptStatus.CONFIRMED;
        
        //update counter
        idCounter = IdGenerator.generateNewId(FILE_PATH);
    }
	
	public String getAppointmentId() {
		return appointmentId;
	}

	public String getAppointmentSlotId() {
		return appointmentSlotId;
	}

	public String getAppointmentOutcomeId() {
		return appointmentOutcomeId;
	}

	public String getPatientId() {
		return patientId;
	}

	public String getDoctorId() {
		return doctorId;
	}
	
	public TimeSlot getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(TimeSlot timeSlot) {
		this.timeSlot = timeSlot;
	}

	public ApptStatus getStatus() {
		return status;
	}
	
	public void setStatus(ApptStatus status) {
		this.status = status;
	}

	// un-comment
	// public List<AppointmentSlots> getAppointmentSlots(){
	// 	return getAppointmentSlots;
	// }
	

	// need to link with other models for doctor and appointment (for all the below
	// functions)

	// public void setAppointmentSlots(int appointmentId, int doctorId, LocalDate appointmentDate,
	// 		LocalTime appointmentTime) {
	// 	this.appointmentId = appointmentId;
	// 	this.doctorId = doctorId;
	// 	this.appointmentDate = appointmentDate;
	// 	this.appointmentTime = appointmentTime;
	// }

	// public void scheduleAppointment(int doctorId, LocalDate appointmentDate, LocalTime appointmentTime) {
	// 	this.doctorId = doctorId;
	// 	this.appointmentDate = appointmentDate;
	// 	this.appointmentTime = appointmentTime;
	// }

	// public void rescheduleAppointment(int appointmentId, LocalDate newAppointmentDate, LocalTime newAppointmentTime) {
	// 	this.appointmentId = appointmentId;
	// 	this.appointmentDate = newAppointmentDate;
	// 	this.appointmentTime = newAppointmentTime;
	// }

	// public void cancelAppointment() {
	// 	this.status = ApptStatus.CANCELLED;
	// }

	public String[] toCSVRecord() {
        return new String[]{appointmentId, doctorId, patientId, timeSlot.getTime(), status.name(), appointmentSlotId, appointmentOutcomeId};
    }



}
