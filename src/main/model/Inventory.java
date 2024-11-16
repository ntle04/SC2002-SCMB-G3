package main.model;

import main.util.StockLevel;
import main.model.Medicine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import main.csvUitls.Config;

public class Inventory {
    private ArrayList<Medicine> medicationInventory;
    Inventory(){
        medicationInventory = new ArrayList<Medicine>();
    }

    public void addMedicine(Medicine medicine){
        medicationInventory.add(medicine);
    }

    public void removeMedicine(Medicine medicine){
        medicationInventory.remove(medicine);
    }

    // public boolean updateMedicine(String medId){
    //     for(Medicine medicine : medicationInventory){
    //         if(medicine.getMedId().equals(medId)){
                
    //         }
    //     }
    // }
}
