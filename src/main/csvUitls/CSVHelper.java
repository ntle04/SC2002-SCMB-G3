package main.csvUitls;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVHelper {

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

    //updates one by overwriting all data in csv
    public static void updateCSVById(String filePath, String id, String[] updatedRecord, String[] header) throws IOException {
        System.out.println("updating CSV");
        List<String[]> data = new ArrayList<>();

        // Read the existing CSV file
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line.trim().split(","));
            }
        }

        // Update the specific record based on some identifier
        for (int i = 1; i < data.size(); i++) {  // Start from 1 to skip the header
            String[] row = data.get(i);

            if (row[0].equals(id)) {  // Assuming id is the first column in updatedRecord
                // Replace the record with the updated request
                System.out.println("FOUND Record");
                data.set(i, updatedRecord);
                break;
            }
        }

        // Write the updated data back to the file, including the header
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // // Write the header
            // writer.write(String.join(",", header));
            // writer.newLine();

            // Write the updated records
            for (String[] row : data) {
                writer.write(String.join(",", row));
                writer.newLine();
            }
        }
    }

//     public static void updateCSVWithRecord(String filePath, List<Integer> fieldsToMatch, String[] updatedRecord, String[] header) throws IOException {
//     List<String[]> data = new ArrayList<>();

//     // Read the existing CSV file
//     try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//         String line;
//         while ((line = reader.readLine()) != null) {
//             data.add(line.trim().split(","));
//         }
//     }

//     // Update the specific record
//     // for (int i = 1; i < data.size(); i++) { // Start from 1 to skip the header
//     //     if (Arrays.equals(data.get(i), updatedRecord)) {
//     //         System.out.println("matched");
//     //         data.set(i, updatedRecord); // Replace the record
//     //         break;
//     //     }
//     //     System.out.println("NOT FOUND");
//     // }

//     // for (int i = 1; i < data.size(); i++) { // Start from 1 to skip the header
//     //     String[] currentRecord = data.get(i);
//     //     if (currentRecord[0].equals(updatedRecord[0])) { // Assuming the first column (ID) is the key
//     //         data.set(i, updatedRecord); // Replace the record
//     //         System.out.println("Record updated.");
//     //         break;
//     //     }
//     // }

//     for (int index : fieldsToMatch) {
//         if (index < 1 || index >= data.size()) { // Skip invalid indexes (index 0 is the header)
//             System.out.println("Index " + index + " is invalid or out of range.");
//             continue;
//         }

//         // Check if the record matches (e.g., based on specific criteria)
//         String[] record = data.get(index);
//         if (Arrays.equals(record, updatedRecord)) {
//             // Update the record
//             data.set(index, updatedRecord);
//             System.out.println("Record at index " + index + " updated.");
//             break; // Only update one record
//         }
//     }

//     // Write the updated data back to the file
//     try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
//         // // Write the header
//         // writer.write(String.join(",", header));
//         // writer.newLine();

//         // Write the updated records
//         for (String[] row : data) {
//             writer.write(String.join(",", row));
//             writer.newLine();
//         }
//     }
// }


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
