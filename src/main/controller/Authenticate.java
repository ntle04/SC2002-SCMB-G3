package main.controller;
import main.util.Role;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Authenticate {

    static String role;

    public static boolean validateLogin(String id, String password) {
        String filePath = "src/main/data/credentials.csv";
        String line;
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].equals(id) && values[1].equals(password)) {
                    role = values[2];
                    return true; //login successful
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return false; //login failed
    }

    public static Role getRole(){
        if(role.equals("Pharmacist")) return Role.PHARMACIST;
        else if(role.equals("Doctor")) return Role.DOCTOR;
        else if(role.equals("Admin")) return Role.ADMINISTRATOR;
        else if(role.equals("Patient")) return Role.PATIENT;
        else return null;
    }
    
}
