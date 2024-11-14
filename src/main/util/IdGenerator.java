package main.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class IdGenerator {
    private static final String DELIMITER = ",";

    private static int getLatestId(String filePath) {
        int maxId = 0;
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();
            
            while ((line = br.readLine()) != null) {
                String[] values = line.split(DELIMITER);
                String idString = values[0];
                int currentId;
                
                if (idString.length() >= 4) {
                    currentId = Integer.parseInt(idString.substring(idString.length() - 4));
                } else {
                    currentId = Integer.parseInt(idString);
                }

                if (currentId > maxId) {
                    maxId = currentId;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }catch (NumberFormatException e) {
            System.out.println("Error parsing ID to integer.");
            e.printStackTrace();
        }
        
        return maxId;
    }

    public static String generateNewId(String filePath) {
        int latestId = getLatestId(filePath);
        int newId = latestId + 1;
        
        return String.format("%04d", newId);
    }
    
}
