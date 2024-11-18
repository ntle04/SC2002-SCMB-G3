package main.controller;

import java.io.IOException;
import java.util.List;

import main.csvUitls.Config;
import main.csvUitls.ContactCSVManager;
import main.model.Person;
import main.util.Role;

public class PersonController {

    private static final String PATIENT_FILE_PATH = Config.PATIENT_LIST_FILE_PATH;
    private static final String STAFF_FILE_PATH = Config.STAFF_LIST_FILE_PATH;

    private ContactCSVManager csvManager = new ContactCSVManager();

    
    /** 
     * @param personId
     * @param role
     * @return Person
     */
    public Person getPersonById(String personId, Role role){

        String filePath = ((role == Role.PATIENT) ? PATIENT_FILE_PATH : STAFF_FILE_PATH);

        try {
            // Load persons from the CSV file
            List<Person> persons = csvManager.loadPersonsFromCSV(filePath, role);

            // Locate the person to update
            for (Person person : persons) {
                if (person.getId().equals(personId)) {
                    return person;
                }
            }
        } catch (IOException e) {
        }

        System.out.println("Person not found");
        return null;

    }


    
}
