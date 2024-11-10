package main;

import java.util.Scanner;

import main.model.*;

enum Role{
    PHARMACIST,
    DOCTOR,
    ADMINISTRATOR,
    PATIENT
}


public class HMSApp
{
    public static void main(String [] args){
        System.out.println("Hello!");
        Person sarah = new Person("Sarah", 'F', "999");
        sarah.printContact();
        sarah.updateContact();
        sarah.printContact();
    }
}

