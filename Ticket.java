public class Ticket {

    Flight Flight;
    Passenger passenger;
    String seatNumber;
    int seatRow;
    String classType;
    final static int reservationNumber = 100;
    static int reservationConfirmationNumber = reservationNumber-1;
    int resevation;
    final double VAT = 0.15;
    double ticketPrice = 2000;

    public Ticket(Flight flight, Passenger passenger, String seatNumber, int row, String classType) {
        this.Flight = flight;
        this.passenger = passenger;
        this.seatNumber = seatNumber;
        this.seatRow = row;
        this.classType = classType;
        reservationConfirmationNumber = reservationConfirmationNumber+1;
        resevation = reservationConfirmationNumber;
    }
    
//GETTERS
    public Flight getFlight() {
        return Flight;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public int getSeatRow() {
        return seatRow;
    }

    public String getClassType() {
        return classType;
    }

    public int getReservationConfirmationNumber() {
        return reservationConfirmationNumber;
    }
    
    public int getResevation(){
        return resevation;
    }
    
//SETTERS
    public void setFlight(Flight flight) {
        Flight = flight;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setSeatRow(int row) {
        seatRow = row;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }
    
//METHODS
    public double ticketPrice() {
        double finalPrice = 2000;

        if (classType.equals("BusinessClass")) {
            finalPrice += (ticketPrice * 2);
        } else if (classType.equals("FirstClass")) {
            finalPrice += (ticketPrice * 4);
        }
        if (Flight.getArrivalCity().equals("JED")) {
            finalPrice -= (finalPrice * 0.20);
        }
        finalPrice += (finalPrice * VAT);
        finalPrice = (double) (Math.round(finalPrice));
        return finalPrice;
    }

    @Override
    public String toString() {
        return "Reservation Confirmation Number= " + resevation
                + ", Flight Number=" + Flight.getFlightNumber()
                + ", Passenger Name= " + passenger.getName()
                + ", Seat Number= " + ((seatRow + 1) + (seatNumber))
                + " , classType= " + classType;
    }

}
