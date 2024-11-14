package main.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import main.util.RequestStatus;

public class ReplenishmentRequest {

    private static int idCounter = 1;
    private String reqId;
    private String medId;
    private int qty;
    private RequestStatus status;
    private String reqBy;
    private Date reqDate;

    //new request
    public ReplenishmentRequest(String medId, int qty, String reqBy){
        this.reqId = "REQ" + idCounter++;
        this.medId = medId;
        this.qty = qty;
        this.status = RequestStatus.PENDING;
        this.reqBy = reqBy;
        this.reqDate = new Date();
    }

    //for export
    public ReplenishmentRequest(String reqId, String medId, int qty, RequestStatus status, String reqBy, Date reqDate){
        this.reqId = reqId;
        this.medId = medId;
        this.qty = qty;
        this.status = status;
        this.reqBy = reqBy;
        this.reqDate = reqDate;
    }

    public String getReqId(){
        return reqId;
    }

    public String getMedId(){
        return medId;
    }

    public int getQty(){
        return qty;
    }

    public void setQty(int qty){
        this.qty = qty;
    }

    public RequestStatus getStatus(){
        return status;
    }

    public void setStatus(RequestStatus status){
        this.status = status;
    }

    public String getReqBy(){
        return reqBy;
    }

    public void setReqBy(String reqBy){
        this.reqBy = reqBy;
    }

    public Date getReqDate(){
        return reqDate;
    }

    public void setReqDate(Date reqDate){
        this.reqDate = reqDate;
    }

    public String toCSV() {
        return reqId + "," + medId + "," + qty + "," + status + "," + reqBy + "," + reqDate;
    }

    public static ReplenishmentRequest fromCSV(String csvLine) {
        String[] values = csvLine.split(",");
        
        if (values.length != 6) {
            return null; // If data doesn't match expected format, return null
        }
        
        try {
            // Parse values from CSV line
            String reqId = values[0].trim();
            String medId = values[1].trim();
            int qty = Integer.parseInt(values[2].trim());
            String status = values[3].trim();
            String reqBy = values[4].trim();
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
            Date reqDate = dateFormat.parse(values[5].trim()); // Parse the date field
            
            return new ReplenishmentRequest(reqId, medId, qty, RequestStatus.valueOf(status), reqBy, reqDate);

        } catch (NumberFormatException | ParseException e) {
            System.out.println("Error parsing CSV line: " + csvLine);
            return null;
        }
    }

}
