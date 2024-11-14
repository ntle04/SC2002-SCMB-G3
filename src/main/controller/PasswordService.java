package main.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import main.util.Config;

public class PasswordService {

    private String filePath = Config.CREDENTIALS_FILE_PATH;

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean validatePassword(String inputPassword, String storedHash) {
        String inputPasswordHash = hashPassword(inputPassword);
        return inputPasswordHash != null && inputPasswordHash.equals(storedHash);
    }
    
    // Method to update the hashed password in the CSV file
    public void updatePassword(String id, String newPassword) {
        List<String[]> csvData = new ArrayList<>();
        
        // Hash the new password
        String hashedPassword = hashPassword(newPassword);
        if (hashedPassword == null) {
            System.out.println("Error: Hashing failed");
        }
        
        // Read and modify CSV file content
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].equals(id)) {
                    values[1] = hashedPassword;
                    values[3] = "FALSE";
                }
                csvData.add(values);

                System.out.println("Password updated successful.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: e");
        }
        
        // Write modified data back to the CSV file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String[] rowData : csvData) {
                bw.write(String.join(",", rowData));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: e");
        }
    }
    
}
