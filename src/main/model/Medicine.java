package main.model;

import main.util.StockLevel;

public class Medicine {
    private String medId;
    private String medName;
    private String quantity;
    private StockLevel stockLevel;

    Medicine(String medId, String medName, String quantity, StockLevel stockLevel){
        this.medId = medId;
        this.medName = medName;
        this.quantity = quantity;
        this.stockLevel = stockLevel;
    }

    public String getMedId(){
        return medId;
    }

    public String getMedName(){
        return medName;
    }

    public String getQuantity(){
        return quantity;
    }

    public StockLevel getStockLevel(){
        return stockLevel;
    }
}
