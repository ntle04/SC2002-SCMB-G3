package main.model;

import main.util.StockLevel;
import main.model.Medicine;
import main.csvUitls.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;

public class Inventory {
    private static final String file_path = Config.MEDICATION_INVENTORY_FILE_PATH;
    Scanner sc = new Scanner(System.in);
    private static ArrayList<Medicine> medicationInventory = new ArrayList<>();
    public Inventory(){
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
            medicationInventory.add(medicine);
            return new Inventory();

        } catch (NumberFormatException | ParseException e) {
            System.out.println("Error parsing CSV line: " + csvLine);
            return null;
        }
    }

    public void addMedicine(Medicine medicine){
        medicationInventory.add(medicine);
    }

    public void createMedicine(){
        String id = IdGenerator.generateNewId(file_path);
        System.out.printf("Enter medication name: ");
        String name = sc.nextLine();
        System.out.printf("Enter quantity: ");
        String quantity = sc.nextLine();
        System.out.printf("Enter sale price: ");
        String salePrice = sc.nextLine();
        System.out.printf("Enter last purchase date (dd/MM/yy): ");
        String lastPurchase = sc.nextLine();
        System.out.printf("Enter stock level (low, medium, high): ");
        String stock = sc.nextLine();
        StockLevel stockEnum = StockLevel.valueOf(stock.toUpperCase());

        Medicine medicine = new Medicine(id, name, quantity, salePrice, lastPurchase, stockEnum);
        addMedicine(medicine);
        saveAllChanges();
    }

    public void removeMedicine(){
        System.out.printf("Enter medication ID: ");
        String id = sc.nextLine();
        for(Medicine medicine: medicationInventory){
            if(medicine.getMedId().equals(id)){
                medicationInventory.remove(medicine);
                System.out.println("Medication removed.");
                saveAllChanges();
                return;
            }
        }
        System.out.println("Medicine Id " +id+ " not found.");
        return;
    }

    public boolean updateMedicine(){
        int choice = -1;
        System.out.println("Enter medication ID: ");
        String id = sc.nextLine();
        for(Medicine medicine : medicationInventory){
            if(medicine.getMedId().equals(id)){
                do{
                    System.out.println("Enter your choice: ");
                    System.out.println("1. Change medication name");
                    System.out.println("2. Change medication quantity");
                    System.out.println("3. Change medication price");
                    System.out.println("4. Change last purchase date");
                    System.out.println("5. Change stock level");
                    System.out.println("6. Quit");
                    
                    choice = sc.nextInt();
                    sc.nextLine();
                    switch(choice){
                        case 1:
                            System.out.printf("Enter new medication name: ");
                            String name = sc.nextLine();
                            medicine.setMedName(name);
                            break;
                        case 2:
                            System.out.printf("Enter new medication quantity: ");
                            String quantity = sc.nextLine();
                            medicine.setQuantity(quantity);
                            break;
                        case 3:
                            System.out.printf("Enter new medication price: ");
                            String price = sc.nextLine();
                            medicine.setSalePrice(price);
                            break;
                        case 4: 
                            System.out.printf("Enter new last purchase date (dd/MM/yyyy): ");
                            String lastPurchase = sc.nextLine();
                            medicine.setLastPurchase(lastPurchase);
                        case 5:
                            System.out.printf("Enter new stock level: ");
                            String stock = sc.nextLine();
                            StockLevel stockEnum = StockLevel.valueOf(stock.toUpperCase());
                            medicine.setStockLevel(stockEnum);
                    }
                    System.out.println("Medicine updated.");
                } while(choice != 6);
                
                saveAllChanges();
                return true;
            }
        }
        System.out.println("Medicine Id " +id+ " not found.");
        return false;
    }

    public boolean isEmpty(){
        return medicationInventory.isEmpty();
    }

    public ArrayList<Medicine>getAllMedicines(){
        return medicationInventory;
    }

    //save to csv
    private void saveAllChanges() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file_path))) {
            for (Medicine request : medicationInventory) {
                writer.write(request.toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
