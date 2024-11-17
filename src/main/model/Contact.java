package main.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Contact {

    private String name;
    private String dob;
    private String age;
    private char gender;
    private String contactNumber;
    private String email;
    private String address;

    public Contact(String name, String age, String dob, char gender, String contactNumber, String email, String address){
        this.name = name;
        this.age = age;
        this.dob = dob;
        this.gender = gender;
        this.contactNumber = contactNumber;
        this.email = email;
        this.address = address;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }


    public String getAge(){
        return age;
    }

    public void setAge(String age){
        this.age = age;
    }


    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getContactNumber(){
        return contactNumber;
    }

    public void setContactNumber(String contactNumber){
        this.contactNumber = contactNumber;
    }

    public String getDOB(){
        return dob;
    }

    public void setDOB(String dob){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate birthDate = LocalDate.parse(dob, formatter);
        LocalDate today = LocalDate.now();
        int age = Period.between(birthDate, today).getYears();

        this.dob = dob;
        this.age = String.valueOf(age);
    }

    public char getGender(){
        return gender;
    }

    public void setGender(char gender){
        this.gender = gender;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }
    
}
