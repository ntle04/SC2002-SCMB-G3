package main.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

public class AgeCalc {
    
    /** 
     * @param dobString
     * @return String
     */
    public static String calculateAge(String dobString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            // Parse the DOB string into a Date object
            Date dob = dateFormat.parse(dobString);

            // Get the current date
            Calendar currentDate = Calendar.getInstance();

            // Get the current year, month, and day
            int currentYear = currentDate.get(Calendar.YEAR);
            int currentMonth = currentDate.get(Calendar.MONTH) + 1; // Month is 0-based, so add 1
            int currentDay = currentDate.get(Calendar.DAY_OF_MONTH);

            // Create a calendar object for the DOB
            Calendar dobCalendar = Calendar.getInstance();
            dobCalendar.setTime(dob);

            // Get the year, month, and day from DOB
            int dobYear = dobCalendar.get(Calendar.YEAR);
            int dobMonth = dobCalendar.get(Calendar.MONTH) + 1; // Month is 0-based
            int dobDay = dobCalendar.get(Calendar.DAY_OF_MONTH);

            // Calculate the age
            int age = currentYear - dobYear;

            // Adjust age if the birthday hasn't occurred yet this year
            if (currentMonth < dobMonth || (currentMonth == dobMonth && currentDay < dobDay)) {
                age--;
            }


            return String.valueOf(age);

        } catch (Exception e) {
            // If parsing fails, return -1 or some indication of an error
            System.out.println("Error parsing DOB: " + e.getMessage());
            return "Invalid DOB format.";
        }
    }   
}
