package main.view;
import java.util.List;
import main.model.Inventory;
import main.model.Medicine;

public class InventoryView {

    // Display the medicine inventory
    public void printInventory(Inventory inventory) {    
        if(inventory.isEmpty()){
            System.out.println("Inventory is empty.");
        }

        for(Medicine medicine : inventory.getAllMedicines()){
            System.out.println("Medication ID: " + medicine.getMedId());
            System.out.println("Name: " + medicine.getMedName());
            System.out.println("Quantity: " + medicine.getQuantity()); 
            System.out.println("Sale Price: " + medicine.getSalePrice());
            System.out.println("Last Purchase: " + medicine.getLastPurchase());
            System.out.println("Stock Level: " + medicine.getStockLevel());
        }
    }

    
}
    