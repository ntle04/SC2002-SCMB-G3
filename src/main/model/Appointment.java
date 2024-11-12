package main.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {
    private int appointmentId;
    private int doctorId;
    private int patientId;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    //private ApptStatus status; //un-comment 

    // Constructor
    public Appointment(int appointmentId, int doctorId, LocalDate date, LocalTime time) {
        this.appointmentId = appointmentId;
        this.doctorId = doctorId;
        this.appointmentDate = date;
        this.appointmentTime = time;
        //this.status = "Available"; //un-comment 
    }

    // put here first 
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
}