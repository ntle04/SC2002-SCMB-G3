package main.csvUitls;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVHelper {

    
    /** 
     * @param filePath
     * @return List<String[]>
     * @throws IOException
     */
    public static List<String[]> readCSV(String filePath) throws IOException {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;
            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false; // Skip the first line (header)
                    continue;
                }
                data.add(line.trim().split(","));
            }
        }
        return data;
    }

    //append data to csv
    public static void appendCSV(String filePath, List<String[]> data) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            for (String[] row : data) {
                bw.write(String.join(",", row));
                bw.newLine();
            }
        }
    }

    public static void appendSingleCSV(String filePath, String[] data) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            String line = String.join(",", data);
            writer.write(line);
            writer.newLine();
        }
    }

    public static void updateCSVById(String filePath, String id, String[] updatedRecord, String[] header) throws IOException {
        List<String[]> data = new ArrayList<>();
        boolean recordFound = false;
    
        // Read the existing CSV file
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    data.add(line.trim().split(","));
                    continue;
                }
                
                String[] row = line.trim().split(",");
                if (row[0].equals(id)) {
                    data.add(updatedRecord);
                    recordFound = true;
                } else {
                    data.add(row);
                }
            }
        }
    
        if (!recordFound) {
            System.out.println("Warning: No record found with ID: " + id);
        }
    
        // Write the updated data back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String[] row : data) {
                writer.write(String.join(",", row));
                writer.newLine();
            }
        }
    }

    public static void deleteFromCSV(String filePath, String id, String[] header) throws IOException {
        List<String[]> data = new ArrayList<>();

        // Read the existing CSV file
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line.split(","));
            }
        }

        // Remove the record based on the identifier (e.g., doctorId)
        data.removeIf(row -> row[0].equals(id)); // Assuming identifier is in the first column

        // Write the updated data back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Write the header
            writer.write(String.join(",", header));
            writer.newLine();

            // Write the remaining records
            for (String[] row : data) {
                writer.write(String.join(",", row));
                writer.newLine();
            }
        }
    }
    
}
