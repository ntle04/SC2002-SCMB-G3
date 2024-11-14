package main.model;

import java.util.Scanner;
import main.util.Role;


public class Person {
    private String id;
    private Contact contact;
    private Role role;

    public Person(String id, Role role){
        this.id = id;
        this.role = role;
    }

    // public Person(String name, char gender, String contactNumber){
    //     this.contact = new Contact(name, gender, contactNumber);
    // }
    //to add in dob field
    public Person(String name, char gender, String contactNumber, String email, String address){
        this.contact = new Contact(name, gender, contactNumber, email, address);
    }

    public String getId(){
        return id;
    }

    public Role getRole(){
        return role;
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

    public void updateContact(){

        int choice;
        Scanner sc = new Scanner(System.in);

        do{
            //print menu
            System.out.println("Update contact information:");
            System.out.println("1. Name");
            System.out.println("2. DOB");
            System.out.println("3. Gender");
            System.out.println("4. Contact Number");
            System.out.println("5. Email");
            System.out.println("6. Address");
            System.out.println("7. Cancel");
            System.out.println("Which information do you wish to update?");
            choice = sc.nextInt();
            switch(choice){
                case 1:{
                    System.out.print("New name: ");
                    String name = sc.next();
                    contact.setName(name);
                    break;
                }
                case 2:{
                    System.out.print("New DOB: ");
                    String name = sc.next();
                    contact.setName(name);
                    break;
                }
                case 3:{
                    System.out.print("New gender: ");
                    char gender = sc.next().charAt(0);
                    contact.setGender(gender);
                    break;
                }
                case 4:{
                    System.out.print("New contact number: ");
                    String contactNumber = sc.next();
                    contact.setContactNumber(contactNumber);
                    break;
                }
                case 5:{
                    System.out.print("New email: ");
                    String email = sc.next();
                    contact.setEmail(email);
                    break;
                }
                case 6:{
                    System.out.print("New address: ");
                    String address = sc.next();
                    contact.setAddress(address);
                    break;
                }
                case 7:{
                    break;
                }
            }
        }while(choice < 7);

    }


    
}
