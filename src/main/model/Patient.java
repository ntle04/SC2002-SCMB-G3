// package main.model;
// import java.util.ArrayList;
// import java.util.Calendar;
// import java.util.List;
// import java.time.LocalDateTime;
// public class Patient {

// 	private int patientId;
// 	private char patientBloodType;
//     private String name;
//     private String password;
//     private String email;
//     private LocalDateTime dob; 
//     private char gender;
//     private String address;
//     private List<Appointment> patientAppointment;
//     private List<String> diagnosis;
//     private List<String> treatment;
	
//     // Patient Constructor 
//     public Patient(int patientId, String name, LocalDateTime dob, char gender, String email, char patientBloodType) {
//         this.patientId = patientId;
//         this.name = name;
//         this.dob = dob; 
//         this.gender = gender;
//         this.email = email;
//         this.patientBloodType = patientBloodType;
//         this.diagnosis = new ArrayList<String>();
//         this.treatment = new ArrayList<String>();
//     }

   
//     public int getPatientId() {
// 		return patientId;
// 	}


// 	public void setPatientId(int patientId) {
// 		this.patientId = patientId;
// 	}


// 	public char getPatientBloodType() {
// 		return patientBloodType;
// 	}


// 	public void setPatientBloodType(char patientBloodType) {
// 		this.patientBloodType = patientBloodType;
// 	}


// 	public String getName() {
// 		return name;
// 	}

// 	public void setName(String name) {
// 		this.name = name;
// 	}

// 	public String getPassword() {
// 		return password;
// 	}


// 	public void setPassword(String password) {
// 		this.password = password;
// 	}

// 	public String getEmail() {
// 		return email;
// 	}


// 	public void setEmail(String email) {
// 		this.email = email;
// 	}


// 	public LocalDateTime getDob() {
// 		return dob;
// 	}


// 	public void setDob(LocalDateTime dob) {
// 		this.dob = dob;
// 	}


// 	public char getGender() {
// 		return gender;
// 	}


// 	public void setGender(char gender) {
// 		this.gender = gender;
// 	}


// 	public String getAddress() {
// 		return address;
// 	}


// 	public void setAddress(String address) {
// 		this.address = address;
// 	}


// 	public List<Appointment> getPatientAppointment() {
// 		return patientAppointment;
// 	}


// 	public void setPatientAppointment(List<Appointment> patientAppointment) {
// 		this.patientAppointment = patientAppointment;
// 	}


// 	public List<String> getDiagnosis() {
// 		return diagnosis;
// 	}


// 	public void setDiagnosis(List<String> diagnosis) {
// 		this.diagnosis = diagnosis;
// 	}


// 	public List<String> getTreatment(int patientId) {
// 		return treatment;
// 	}


// 	public void setTreatment(List<String> treatment) {
// 		this.treatment = treatment;
// 	}



// 	public void updatePatientContact(int patientId, String newEmail, String newAddress) {
// 		this.patientId = patientId;
// 		this.email = newEmail;
// 		this.address = newAddress;
// 	}
// }