package main.controller;
import main.model.Person;
import main.csvUitls.Config;
import main.model.Contact;
import main.util.Role;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Console;

public class Authenticate {

    private static Person loggedInUser;
    private static PasswordService passwordService = new PasswordService();

    public static boolean validateLogin(String id, String password) {
        String filePath = Config.CREDENTIALS_FILE_PATH;
        String line;

        Console console = System.console();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                
                //login successful
                if (values[0].equals(id) && passwordService.validatePassword(password, values[1])) {
                    String role = values[2];
                    boolean firstLogin = Boolean.parseBoolean(values[3]);

                    ContactController contactController = new ContactController(null);
                    Contact contact = contactController.loadContactById(id, Role.valueOf(role.toUpperCase()));
                    loggedInUser = new Person(id, contact, getRole(role));

                    System.out.println("Login successful.");

                    if (firstLogin) {
                        System.out.println("Change your password on your first login.");
                        char[] passwordArr = console.readPassword();
                        String newPassword = new String(passwordArr);
                        passwordService.updatePassword(id, newPassword);
                    }

                    return true;
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

    public static void logout() {
        loggedInUser = null;
        System.out.println("Logging out...");
    }
    
}
