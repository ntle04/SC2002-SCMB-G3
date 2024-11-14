package main.view;

import java.text.SimpleDateFormat;
import java.util.List;

import main.model.ReplenishmentRequest;
import main.util.RequestStatus;

public class ReplenishmentRequestView {

    SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");


    public void printAllReq(List<ReplenishmentRequest> requests) {
        for (ReplenishmentRequest request : requests) {
            printReq(request);
            System.out.println("-------------------------");
        }
    }

    public void printReq(ReplenishmentRequest request){
        System.out.println("Request ID: " + request.getReqId());
        System.out.println("Medication ID: " + request.getMedId());
        System.out.println("Quantity: " + request.getQty());
        System.out.println("Requested By: " + request.getReqBy());
        System.out.println("Request Date: " + date.format(request.getReqDate()));
        System.out.println("Status: " + request.getStatus()); 
    }

    public void printSubmissionStatus(ReplenishmentRequest request){
        System.out.println("Replenishment Request submitted.");
        System.out.println("Submission date: " + date.format(request.getReqDate()));
        System.out.println("Request id: " + request.getReqId());
    }

    public void printUpdateStatus(String reqId, RequestStatus status) {
        System.out.println("Request ID " + reqId + " " + status);
    }
    
}
