package main.model;

import main.util.ApptStatus;
import main.util.TimeSlot;

public class Appointment {
	private String appointmentId;
	private String doctorId;
	private String patientId;
	private TimeSlot timeSlot;
	private ApptStatus status;

	public Appointment(String appointmentId, String doctorId, String patientId, TimeSlot timeSlot, ApptStatus status) {
		this.appointmentId = appointmentId;
		this.doctorId = doctorId;
		this.patientId = patientId;
		this.timeSlot = timeSlot;
		this.status = status; 
	}
	
	public String getAppointmentId() {
		return appointmentId;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
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

	// public void cancelAppointment(int appointmentId) {
	// 	this.appointmentId = appointmentId;
	// }

	public String[] toCSVRecord() {
        return new String[]{appointmentId, doctorId, patientId, timeSlot.getTime(), status.name()};
    }



}
