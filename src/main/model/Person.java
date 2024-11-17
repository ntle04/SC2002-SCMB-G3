package main.model;

import main.util.Role;


public class Person {
    private String id;
    private Contact contact;
    private Role role;

    public Person(String id, Contact contact, Role role){
        this.id = id; 
        this.contact = contact;
        this.role = role;
    }

    //to add in dob field
    // public Person(String name, char gender, String contactNumber, String email, String address){
    //     this.contact = new Contact(name, gender, contactNumber, email, address);
    // }

    public String getId(){
        return id;
    }
    
    public void printContact(){
		System.out.println("---Contact Information----");
		System.out.println("Name: " + contact.getName());
		System.out.println("DOB: " + contact.getDOB());
		System.out.println("Gender: " + contact.getGender());
		System.out.println("Contact Number: " + contact.getContactNumber());
		System.out.println("Email: " + contact.getEmail());
		System.out.println("Address: " + contact.getAddress());
    }
    
    public void updateContact(Contact contact){
        this.contact = contact;
    }
    
    public Contact getContact(){
        return contact;
    }

    public Role getRole(){
        return role;
    }

    public void setRole(Role role){
        this.role = role;
    }
}
