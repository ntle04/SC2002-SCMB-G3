package main.csvUitls;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*public class IdGenerator {
    private static final String DELIMITER = ",";

    public static int getLatestId(String filePath) {
        int maxId = 0;
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();
            
            while ((line = br.readLine()) != null) {
                System.out.println("Reading line: " + line);
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

        System.out.println("Max ID found: " + maxId);
        
        return maxId;
    }

    public static String generateNewId(String filePath) {
        int latestId = getLatestId(filePath);
        int newId = latestId + 1;
        
        return String.format("%04d", newId);
    }
    
}*/

public class IdGenerator {
    private static final String DELIMITER = ",";

    public static int getLatestId(String filePath) {
        int maxId = 0;
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip header
            
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                
                String[] values = line.split(DELIMITER);
                if (values.length == 0) continue;
                
                String idString = values[0].trim();
                if (!idString.startsWith("AV")) continue;
                
                try {
                    int currentId = Integer.parseInt(idString.substring(2)); // Remove "AV" prefix
                    maxId = Math.max(maxId, currentId);
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    System.out.println("Skipping invalid ID format: " + idString);
                }
            }
        } catch (IOException e) {
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
