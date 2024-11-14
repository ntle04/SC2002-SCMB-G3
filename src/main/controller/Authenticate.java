package main.controller;
import main.model.Person;
import main.util.Role;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Authenticate {

    private static Person loggedInUser;

    public static boolean validateLogin(String id, String password) {
        String filePath = "src/main/data/credentials.csv";
        String line;
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].equals(id) && values[1].equals(password)) {
                    String role = values[2];
                    loggedInUser = new Person(id, getRole(role));
                    return true; //login successful
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return false; //login failed
    }

    private static Role getRole(String role){
        switch(role){
            case "Pharmacist": return Role.PHARMACIST;
            case "Doctor": return Role.DOCTOR;
            case "Admin": return Role.ADMINISTRATOR;
            case "Patient": return Role.PATIENT;
            default: return null;
        }
    }

    public static Person getLoggedInUser() {
        return loggedInUser;
    }

    public static String getUserId() {
        return loggedInUser != null ? loggedInUser.getId() : null;
    }

    public static Role getUserRole() {
        return loggedInUser != null ? loggedInUser.getRole() : null;
    }
    
}
