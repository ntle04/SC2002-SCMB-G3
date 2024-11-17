package main.model;

import main.controller.*;
import main.view.*;
import main.util.Role;


public class Administrator extends Person{


    public Administrator(){
        super(Authenticate.getLoggedInUser().getId(), null, Role.ADMINISTRATOR);

        StaffView staffView = new StaffView();
        StaffController staffCon = new StaffController(staffView);
        InventoryView invView = new InventoryView();
        Inventory inv = new Inventory();

    }
}
