package main.view;

public class AdministratorView {
    public void printInvActions(){
        System.out.println("Select your choice:");
        System.out.println("1. View inventory");
        System.out.println("2. Add stock");
        System.out.println("3. Remove stock");
        System.out.println("4. Update stock");
    }

    public void printStaffActions(){
        System.out.println("Select your choice:");
        System.out.println("1. Add staff");
        System.out.println("2. Update staff");
        System.out.println("3. Remove staff");
        System.out.println("4. View all hospital staff");
        System.out.println("5. View filtered staff list");
    }

    public void printFilterMenu(){
        System.out.println("Filter by:");
        System.out.println("1. Role");
        System.out.println("2. Gender");
        System.out.println("3. Age");
    }
}
