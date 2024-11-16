package main.model;

import main.util.StockLevel;
import main.model.Medicine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Date;

import main.csvUitls.Config;

public class Inventory {
    private ArrayList<Medicine> medicationInventory;
    Inventory(){
        medicationInventory = new ArrayList<Medicine>();
    }

    public static Inventory fromCSV(String csvLine) {
        String[] values = csvLine.split(",");
        
        if (values.length != 6) {
            return null; // If data doesn't match expected format, return null
        }
        
        try {
            // Parse values from CSV line
        	String id = values[0].trim();
            String name = values[1].trim();
			String quantity = values[2].trim();
			String salePrice = values[3].trim();
            String lastPurchase = values[4].trim();
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
            Date parseLP = dateFormat.parse(lastPurchase);
            
            String stockLevel = values[5].trim();
            StockLevel SLEnum = StockLevel.valueOf(stockLevel);


            Medicine medicine = new Medicine(id, name, quantity, salePrice, lastPurchase, SLEnum);

            return new Inventory();

        } catch (NumberFormatException | ParseException e) {
            System.out.println("Error parsing CSV line: " + csvLine);
            return null;
        }
    }

    public void addMedicine(Medicine medicine){
        medicationInventory.add(medicine);
    }

    public void removeMedicine(Medicine medicine){
        medicationInventory.remove(medicine);
    }

    public boolean updateMedicine(String medId, String name, String quantity, String price, String lastPurchase, StockLevel stockLevel){
        for(Medicine medicine : medicationInventory){
            if(medicine.getMedId().equals(medId)){
                medicine.setMedName(name);
                medicine.setSalePrice(price);
                medicine.setLastPurchase(lastPurchase);
                medicine.setStockLevel(stockLevel);
                System.out.println("Medicine updated");
                return true;
            }
        }
        System.out.println("Medicine Id " +medId+ " not found");
        return false;
    }

    public boolean isEmpty(){
        return medicationInventory.isEmpty();
    }

    public List<Medicine>getAllMedicines(){
        return medicationInventory;
    }
}
