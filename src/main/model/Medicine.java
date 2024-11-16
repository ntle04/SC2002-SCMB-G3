package main.model;

import main.util.StockLevel;

public class Medicine {
    private String medId;
    private String medName;
    private String quantity;
    private String salePrice;
    private StockLevel stockLevel;

    Medicine(String medId, String medName, String quantity, String salePrice, StockLevel stockLevel){
        this.medId = medId;
        this.medName = medName;
        this.quantity = quantity;
        this.salePrice = salePrice;
        this.stockLevel = stockLevel;
    }

    public String getMedId(){
        return medId;
    }

    public void setMedId(String medId){
        this.medId = medId;
    }

    public String getMedName(){
        return medName;
    }

    public void setMedName(String medName){
        this.medName = medName;
    }

    public String getQuantity(){
        return quantity;
    }

    public void setQuantity(String quantity){
        this.quantity = quantity;
    }

    private String getSalePrice(){
        return salePrice;
    }

    public void setSalePrice(String salePrice){
        this.salePrice = salePrice;
    }

    public StockLevel getStockLevel(){
        return stockLevel;
    }

    public void setStockLevel(StockLevel stockLevel){
        this.stockLevel = stockLevel;
    }
}
