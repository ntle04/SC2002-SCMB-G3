package main.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import main.model.ReplenishmentRequest;
import main.util.Config;
import main.util.RequestStatus;
import main.util.Role;
import main.view.ReplenishmentRequestView;

public class ReplenishmentRequestController {

    private ReplenishmentRequestView view = new ReplenishmentRequestView();
    private List<ReplenishmentRequest> requestList = new ArrayList<>();
    private String filePath = Config.REPLENISHMENT_REQUEST_FILE_PATH;



    public ReplenishmentRequestController(){
        requestList = loadAllRequestsFromFile();
    }

    //load data from csv
    private List<ReplenishmentRequest> loadAllRequestsFromFile() {
        List<ReplenishmentRequest> requests = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                ReplenishmentRequest request = ReplenishmentRequest.fromCSV(line);
                if (request != null) {
                    requests.add(request);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file, returning empty list.");
            return new ArrayList<>();
        }
        return requests;
    }

    //create new
    public void createRequest(String medId, int qty, String reqBy) {
        ReplenishmentRequest request = new ReplenishmentRequest(medId, qty, reqBy);
        requestList.add(request);
        saveRequestToFile(request);
        view.printSubmissionStatus(request);
    }

    //update status
    public void updateRequestStatus(String reqId, RequestStatus status) {
        for (ReplenishmentRequest request : requestList) {
            if (request.getReqId().equals(reqId)) {
                request.setStatus(status);
                saveAllRequestsToFile();
                view.printUpdateStatus(reqId, request.getStatus());
                break;
            }
            else{
                System.out.println("ERROR: Request ID not found.");
            }
        }
    }

    //save to csv
    private void saveRequestToFile(ReplenishmentRequest request) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(request.toCSV());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveAllRequestsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (ReplenishmentRequest request : requestList) {
                writer.write(request.toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //get all requests
    public List<ReplenishmentRequest> getAllRequests() {
        Role role = Authenticate.getLoggedInUser().getRole();

        //update with most recent data
        requestList = loadAllRequestsFromFile();

        //for admin
        if(role == Role.ADMINISTRATOR){
            return getRequestsSortedByDate();
        }
        else if(role == Role.PHARMACIST){
            return getRequestsByRequester("M0001");
        }
        else{
            System.out.println("You do not have access to this data.");
            return Collections.emptyList();
        }
    }

    //filter by status
    private List<ReplenishmentRequest> getRequestsByStatus(RequestStatus status) {
        return requestList.stream()
                .filter(request -> request.getStatus() == status)
                .collect(Collectors.toList());
    }

    //filter by requester
    private List<ReplenishmentRequest> getRequestsByRequester(String reqBy) {
        return requestList.stream()
                .filter(request -> request.getReqBy().equals(reqBy))
                .collect(Collectors.toList());
    }

    //sort by date
    private List<ReplenishmentRequest> getRequestsSortedByDate() {
        return requestList.stream()
                .sorted((req1, req2) -> req1.getReqDate().compareTo(req2.getReqDate()))
                .collect(Collectors.toList());
    }

    public void printReq(ReplenishmentRequest request){
        view.printReq(request);
    }

    public void printAllReq(List<ReplenishmentRequest> requests) {
        view.printAllReq(requests);
    }
    
}
