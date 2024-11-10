package main.model;

import java.util.Date;

public class Contact {

    private char gender;
    private String name;
    private String password;
    private String contactNumber;
    private String email;
    private Date dob;
    private String address;

    public Contact(String name, char gender, String contactNumber){
        this.name = name;
        this.password = "password"; //set temporary password
        this.gender = gender;
        this.contactNumber = contactNumber;
    }

    //to add in dob field
    public Contact(String name, char gender, String contactNumber, String email, String address){
        this.gender = gender;
        this.name = name;
        this.password = "password"; //set temporary password
        this.contactNumber = contactNumber;
        this.email = email;
        // this.dob = dob;
        this.address = address;
    }

    public char getGender(){
        return gender;
    }

    public void setGender(char gender){
        this.gender = gender;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getContactNumber(){
        return contactNumber;
    }

    public void setContactNumber(String contactNumber){
        this.contactNumber = contactNumber;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public Date getDOB(){
        return dob;
    }

    public void setDOB(Date dob){
        this.dob = dob;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }
    
}
