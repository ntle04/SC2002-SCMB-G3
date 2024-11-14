package main.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import main.util.ApptStatus;

public class Appointment {
	private int appointmentId;
	private int doctorId;
	private int patientId;
	private LocalDate appointmentDate;
	private LocalTime appointmentTime;
	private ApptStatus status;

	// Constructor
	public Appointment(int appointmentId, int doctorId, LocalDate date, LocalTime time, ApptStatus status) {
		this.appointmentId = appointmentId;
		this.doctorId = doctorId;
		this.appointmentDate = date;
		this.appointmentTime = time;
		this.status = ApptStatus.CONFIRMED; 
	}
	
	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public LocalTime getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(LocalTime appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	// un-comment
	public List<AppointmentSlots> getAppointmentSlots(){
		return getAppointmentSlots;
	}
	

	// need to link with other models for doctor and appointment (for all the below
	// functions)

	public void setAppointmentSlots(int appointmentId, int doctorId, LocalDate appointmentDate,
			LocalTime appointmentTime) {
		this.appointmentId = appointmentId;
		this.doctorId = doctorId;
		this.appointmentDate = appointmentDate;
		this.appointmentTime = appointmentTime;
	}

	public void scheduleAppointment(int doctorId, LocalDate appointmentDate, LocalTime appointmentTime) {
		this.doctorId = doctorId;
		this.appointmentDate = appointmentDate;
		this.appointmentTime = appointmentTime;
	}

	public void rescheduleAppointment(int appointmentId, LocalDate newAppointmentDate, LocalTime newAppointmentTime) {
		this.appointmentId = appointmentId;
		this.appointmentDate = newAppointmentDate;
		this.appointmentTime = newAppointmentTime;
	}

	public void cancelAppointment(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	public ApptStatus getStatus() {
		return status;
	}
	
	public void setStatus(ApptStatus status) {
		this.status = status;
	}

}
