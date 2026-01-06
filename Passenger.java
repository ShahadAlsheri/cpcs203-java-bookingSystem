/*
Shahad Rafi Alshehri
2306119
ICS
Assignment 1
 */
package Assignment1;

public class Passenger {

    String passportNumber;
    String name;

    public Passenger(String passportNumber, String name) {
        this.passportNumber = passportNumber;
        this.name = name;
    }
    
//GETTERS
    String getPassportNumber() {
        return passportNumber;
    }

    public String getName() {
        return name;
    }
    
//SETTERS
    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void updateDetails(String newName) {
        name = newName;
    }
    
//METHODS
    @Override
    public String toString() {
        return "Passenger " + name + " added successfully";

    }
}
