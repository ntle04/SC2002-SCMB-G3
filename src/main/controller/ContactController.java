package main.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import main.model.Contact;
import main.util.Role;

public class ContactController {

    private Contact contact;

    public ContactController(Contact contact){
        this.contact = contact;
    }

    public Contact loadContactById(String id) {
        String p_filePath = "src/main/data/patient_list.csv";
        String s_filePath = "src/main/data/staff_list.csv";
        String filePath = (Authenticate.getUserRole()==Role.PATIENT) ? p_filePath : s_filePath;

        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                if (values[0].equals(id)) {
                    String name = values[1];
                    String dob = values[3];
                    String gender = values[4];
                    String contactNumber = values[5];
                    String email = values[6];
                    String address = values[7];
                    
                    // Create a new Contact object from the CSV data
                    contact = new Contact(name, dob, gender.charAt(0), contactNumber, email, address);
                    return contact;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
            printMenu();
            choice = sc.nextInt();
            sc.nextLine();

            switch(choice){
                case 1:{
                    System.out.print("New name: ");
                    String name = sc.nextLine();
                    contact.setName(name);
                    break;
                }
                case 2:{
                    System.out.print("New DOB: ");
                    String name = sc.nextLine();
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

    private void printMenu(){
        System.out.println("Update contact information:");
        System.out.println("1. Name");
        System.out.println("2. DOB");
        System.out.println("3. Gender");
        System.out.println("4. Contact Number");
        System.out.println("5. Email");
        System.out.println("6. Address");
        System.out.println("7. Cancel");
        System.out.println("Which information do you wish to update?");
    }
    
}