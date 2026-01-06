/*
Shahad Rafi Alshehri
2306119
ICS
Assignment 1
 */
package Assignment1;

import java.io.*;
import java.util.*;

public class BookingSystem {
    public static void main(String[] args) throws FileNotFoundException {

//ALL THE VARIABLES I NEED TO USE
        final int MAX_Ticket = 100;
        int fCounter;
        int pCounter;
        String AllLine;
        String[] Split;
        int pindex = 0;
        int findex = 0;
        int tindex = 0;
        double total = 0;

//FILES
        File Input = new File("flight_passenger.txt");
        File inputPF = new File("inputCommands.txt");
        File Output = new File("output.txt");

//SCANNERS AND PRINTWRITER
        Scanner in = new Scanner(Input);
        Scanner inPF = new Scanner(inputPF);
        PrintWriter pw = new PrintWriter(Output);

//CHECK IF FILES DO EXIST
        if (!Input.exists()) {
            System.out.println("the file named \"" + Input.getName() + "\" does not exist in");
            System.exit(0);
        }
        if (!inputPF.exists()) {
            System.out.println("the file named \"" + inputPF.getName() + "\" does not exist in");
            System.exit(0);
        }

//GENERATE ARRAYS FROM THE FILES + TICKET ARRAY  
        AllLine = in.nextLine();
        Split = AllLine.split(" ");
        fCounter = Integer.parseInt(Split[0]);
        pCounter = Integer.parseInt(Split[1]);
        Flight[] flights = new Flight[fCounter];
        Passenger[] Passengers = new Passenger[pCounter];
        Ticket[] Tickets = new Ticket[MAX_Ticket];

//FIRST FILE TO READ AND PRINT ON THE OUTPUT FILE
        while (in.hasNext()) {
            AllLine = in.nextLine();
            Split = AllLine.split(" ");
            //command = index 0, flight number = index 1, departure city = index 2, arrival city = index 3, gate number = index 4, rows = index 5, columns = index 6.
            if (Split[0].equals("AddFlight")) {
                flights[findex] = new Flight(Split[1], Split[2], Split[3], Integer.parseInt(Split[4]), Integer.parseInt(Split[5]), Integer.parseInt(Split[6]));
                pw.println(flights[findex]);
                findex++;
            }
            //command = index 0, passenger ID = index 1, passenger name = index 2.
            if (Split[0].equals("AddPassenger")) {
                Passengers[pindex] = new Passenger(Split[1], Split[2]);
                pw.println(Passengers[pindex]);
                pindex++;
            }
        }

//SECOND FILE TO READ AND PRINT ON THE OUTPUT FILE
        outerloop:
        while (inPF.hasNext()) {
            AllLine = inPF.nextLine();
            Split = AllLine.split(" ");
            //command = index 0, passenger passport = index 1, flight number = index 2, seat row = index 3, seat column = index 4, class type = index 5.
            if (Split[0].equals("BookTicket")) {
                pw.println("\n*********************BookTicket************************** \n");
                //search for passengers by method (SearchPassenger)
                if (SearchPassenger(Passengers, Split[1]) == -1) {
                    pw.println("Passenger with Passport number " + Split[1] + " is not Registered");
                } else {
                    //search for flights by method (SearchFlights)                    
                    if (SearchFlights(flights, Split[2]) == -1) {
                        pw.println("Flight " + Split[2] + " Not Found");
                    } else {
                        if (flights[SearchFlights(flights, Split[2])].bookSeat(Integer.parseInt(Split[3]) - 1, Split[4].charAt(0), Split[1])) {
                            Tickets[tindex] = new Ticket(flights[SearchFlights(flights, Split[2])], Passengers[SearchPassenger(Passengers, Split[1])], Split[4], Integer.parseInt(Split[3]) - 1, Split[5]);
                            //book a ticket by the method (bookTicket)                            
                            if (bookTicket(Tickets, tindex, Passengers, Split[1], flights, Split[2], Integer.parseInt(Split[3]) - 1, Split[4], Split[5])) {
                                pw.print("Seat booked successfully.\nTicket Information: \nReservation Confirmation Number= " + (Tickets[tindex].getReservationConfirmationNumber()));
                                pw.println(", Flight Number=" + Split[2] + ", Passenger Name= " + Passengers[SearchPassenger(Passengers, Split[1])].getName() + ", Seat Number= " + Split[3] + Split[4] + " , classType= " + Split[5]);
                                tindex++;
                            }
                        } else {
                            pw.println("Seat 10S is already Reserved Or Not found");
                        }
                    }
                }
            }

            //command = index 0, reservation number = index 1.
            if (Split[0].equals("GenerateInvoice")) {
                pw.println("\n*********************Generate Ticket Invoice************************** \n");
                GenerateInvoice(Tickets, tindex, Integer.parseInt(Split[1]), pw);
                Tickets[0].getClass();

            }

            //command = index 0, flight number = index 1.
            if (Split[0].equals("GenerateIFlightnvoice")) {
                pw.println("\n*********************Generate Flight Invoice************************** \n");
                GenerateIFlightnvoice(Tickets, tindex, flights, Split[1], Passengers, pw);
                for (int i = 0; i < tindex; i++) {
                    String x = Tickets[i].getFlight().getFlightNumber();
                    String b = Split[1];
                    if (x.equals(b)) {
                        pw.print("Ticket Information: \n");
                        pw.print(Tickets[i].toString() + "\n");
                        total += Tickets[i].ticketPrice();
                    }
                }
                if (SearchFlights(flights, Split[1]) != -1) {
                    pw.print("\nTotal Invoice price ="+total + "\n");
                    total = 0;
                }
            }
        }
        
//close files
        in.close();
        inPF.close();
        pw.flush();
        pw.close();
    }

//--------------------------------------------------------------------------------------------------------------------------------------
//return the index of the passenger if found, if not return -1
    public static int SearchPassenger(Passenger[] passenger, String passPort) {
        int x = -1;
        for (int i = 0; i < passenger.length; i++) {
            if ((passenger[i].getPassportNumber().equals(passPort))) {
                x = i;
                break;
            } else {
                x = -1;
            }
        }
        return x;
    }

//--------------------------------------------------------------------------------------------------------------------------------------
//return the index of the flight if found, if not return -1
    public static int SearchFlights(Flight[] flight, String flightNumber) {
        int x = -1;
        for (int i = 0; i < flight.length; i++) {
            if ((flight[i].getFlightNumber().equals(flightNumber))) {
                x = i;
                break;
            } else {
                x = -1;
            }
        }
        return x;
    }

//--------------------------------------------------------------------------------------------------------------------------------------
//if ticket booked return true, if not return false    
    public static boolean bookTicket(Ticket[] tickets, int tindex, Passenger[] passenger, String passPort, Flight[] flight, String flightNumber, int seatRow, String seatcol, String classType) {
        tickets[tindex].getResevation();
        return true;
    }

//--------------------------------------------------------------------------------------------------------------------------------------
//return the index of the ticket if found, if not return -1    
    public static int SearchTicket(Ticket[] tickets, int tindex, int Res) {
        int x = -1;
        for (int i = 0; i < tindex; i++) {
            if (tickets[i].getResevation() == Res) {
                x = i;
                break;
            } else {
                x = -1;
            }
        }
        return x;
    }

//--------------------------------------------------------------------------------------------------------------------------------------
    public static void GenerateInvoice(Ticket[] tickets, int tindex, int Res, PrintWriter pw) {
        if (SearchTicket(tickets, tindex, Res) != -1) {
            pw.print("Ticket Information: \n");
            pw.println(tickets[SearchTicket(tickets, tindex, Res)]);
            pw.print("Total ticket price = " + tickets[SearchTicket(tickets, tindex, Res)].ticketPrice() + "\n");
        } else {
            pw.println("Reservation Number is not available");
        }
    }

//--------------------------------------------------------------------------------------------------------------------------------------
    public static void GenerateIFlightnvoice(Ticket[] tickets, int tindex, Flight[] flights, String flightnumber, Passenger[] passengers, PrintWriter pw) {
        if (SearchFlights(flights, flightnumber) != -1) {
            pw.println("Seat Plane for flight " + flightnumber + " is: \n" + "************************************");
            pw.print(flights[SearchFlights(flights, flightnumber)].printSeatPlan());
        } else {
            pw.println("Flight Not Found or No Ticket booked for this flight");
        }
    }

}
