package main.menu;
import main.util.Role;

public abstract class Menu {

    public abstract void printMenu();
    public abstract void handleUserInput();

    public static Menu getMenuForRole(Role role) {
        
        if (role == null) {
            System.out.println("ERROR: Role is null.");
            return null;
        }

        switch(role){
            case PATIENT: {
                return new PatientMenu();
            }
            case PHARMACIST: {
                //return new PharmacistMenu();
            }
            case DOCTOR: {
                return new DoctorMenu();
            }
            case ADMINISTRATOR: {
                return new AdminMenu();
            }
            default:{
                System.out.println("ERROR: Unknown role.");
                return null;
            }
        }
    }

    

    

    

    

    
}