package main.model;

import main.util.StockLevel;
import main.csvUitls.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Inventory {
    private static final String file_path = Config.MEDICATION_INVENTORY_FILE_PATH;
    Scanner sc = new Scanner(System.in);
    private static ArrayList<Medicine> medicationInventory;
    public Inventory(){
        medicationInventory = new ArrayList<>();
        loadAllMedicines();
    }
    
    
    /** 
     * @param csvLine
     */
    public static void fromCSV(String csvLine) {
        String[] values = csvLine.split(",");
        
        if (values.length != 6) {
            return; // If data doesn't match expected format, return null
        }
        
        try {
            // Parse values from CSV line
            String medId = values[0].trim();
            String name = values[1].trim();
            String qty = values[2].trim();
            String salePrice = values[3].trim();
            
            String lastPurchase = values[4].trim();
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date parseDob = dateFormat.parse(lastPurchase);
            
            String stockLevel = values[5].trim();

            Medicine m = new Medicine(medId, name, qty, salePrice, lastPurchase, StockLevel.valueOf(stockLevel));
            medicationInventory.add(m);

        } catch (NumberFormatException | ParseException e) {
            System.out.println("Error parsing CSV line: " + csvLine);
        }
    }

    public boolean isEmpty(){
        return medicationInventory.isEmpty();
    }

    public ArrayList<Medicine>getAllMedicines(){
        return medicationInventory;
    }

    public Medicine getMedicineById(String medId){
        for(Medicine med : medicationInventory){
            if(med.getMedId().equals(medId)){
                return  med;
            }
        }
        return null;
    }

    private void loadAllMedicines(){
        try (BufferedReader reader = new BufferedReader(new FileReader(file_path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fromCSV(line); // This will add medicines to the medicationInventory list
            }
        } catch (IOException e) {
        }
    }

    public void addMedicine(Medicine medicine){
        medicationInventory.add(medicine);
        saveAllChanges();
    }

    public boolean removeMedicine(String medId){
        for(Medicine medicine: medicationInventory){
            if(medicine.getMedId().equals(medId)){
                medicationInventory.remove(medicine);
                System.out.println("Medication removed.");
                saveAllChanges();
                return true;
            }
        }
        System.out.println("Medicine with ID " + medId + " not found.");
        return false;
    }

    //save to csv
    public void saveAllChanges() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file_path))) {
            for (Medicine request : medicationInventory) {
                writer.write(request.toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
        }
    }
}
