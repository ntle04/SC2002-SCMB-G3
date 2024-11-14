package main.model;

import main.util.Role;


public class Person {
    private String id;
    private Role role;
    private Contact contact;

    public Person(String id, Role role, Contact contact){
        this.id = id;
        this.role = role;
        this.contact = contact;
    }

    //to add in dob field
    // public Person(String name, char gender, String contactNumber, String email, String address){
    //     this.contact = new Contact(name, gender, contactNumber, email, address);
    // }

    public String getId(){
        return id;
    }

    public Role getRole(){
        return role;
    }

    public Contact getContact(){
        return contact;
    }
    
}
