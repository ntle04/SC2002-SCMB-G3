package main.model;

import java.util.Scanner;

import main.controller.*;
import main.util.Role;

public class Administrator extends Person{
    public Administrator(){
        super(Authenticate.getLoggedInUser().getId(), null, Role.ADMINISTRATOR);
    }

    

}
