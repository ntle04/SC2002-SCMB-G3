package main.controller;

import main.util.RequestStatus;
import main.util.StockLevel;
import main.model.Medicine;
import main.model.Inventory;
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

public class InventoryController {
    private Inventory inv;
    private static final String file_path = Config.MEDICATION_INVENTORY_FILE_PATH;
    Scanner sc = new Scanner(System.in);
    // private static ArrayList<Medicine> medicationInventory;
    // private  ArrayList<Medicine> medicationInventory = inv.loadAllMedicines();

    public InventoryController(Inventory inv){
        this.inv = inv;
    }

    public void createMedicine(){
        String id = "Med" + IdGenerator.generateNewId(file_path);
        System.out.printf("Enter medication name: ");
        String name = sc.nextLine();
        System.out.printf("Enter quantity: ");
        String quantity = sc.nextLine();
        System.out.printf("Enter sale price: ");
        String salePrice = sc.nextLine();
        System.out.printf("Enter last purchase date (dd/MM/yy): ");
        String lastPurchase = sc.nextLine();
        System.out.printf("Enter stock level (low, medium, high): ");
        String stock = sc.nextLine().toUpperCase();
        StockLevel stockEnum = StockLevel.valueOf(stock);

        Medicine medicine = new Medicine(id, name, quantity, salePrice, lastPurchase, stockEnum);
        inv.addMedicine(medicine);
        System.out.println("Medicine has been added.");
    }

    public void removeMedicine(){
        System.out.printf("Enter medication ID: ");
        String id = sc.nextLine();
        boolean success = inv.removeMedicine(id);
        if(!success)
            System.out.println("Failed to remove medication.");
    }

    public boolean updateMedicine(){
        int choice = -1;
        System.out.println("Enter medication ID: ");
        String id = sc.nextLine();
        for(Medicine medicine : inv.getAllMedicines()){
            if(medicine.getMedId().equals(id)){
                do{
                    updateMedMenu();
                    
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
                            break;
                        case 5:
                            System.out.printf("Enter new stock level (low, medium, high): ");
                            String stock = sc.nextLine();
                            StockLevel stockEnum = StockLevel.valueOf(stock.toUpperCase());
                            medicine.setStockLevel(stockEnum);
                            break;
                    }
                    System.out.println("Medicine updated.");
                } while(choice != 6);
                
                inv.saveAllChanges();
                return true;
            }
        }
        System.out.println("Medicine Id " +id+ " not found.");
        return false;
    }

    public void updateMedMenu(){
        System.out.println("Enter your choice: ");
        System.out.println("1. Change medication name");
        System.out.println("2. Change medication quantity");
        System.out.println("3. Change medication price");
        System.out.println("4. Change last purchase date");
        System.out.println("5. Change stock level");
        System.out.println("6. Quit");
    }
}
