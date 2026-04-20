public class Flight {

    String flightNumber;
    String departureCity;
    String arrivalCity;
    int gateNumber;
    int row;
    int column;
    String[][] seatMap;

    public Flight(String flightNumber, String departureCity, String arrivalCity, int gateNumber, int r, int c) {
        this.flightNumber = flightNumber;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.gateNumber = gateNumber;
        row = r;
        column = c;
        seatMap = new String[row][column];
    }

//GETTERS
    public String getFlightNumber() {
        return flightNumber;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public int getGateNumber() {
        return gateNumber;
    }

    public String[][] getSeatMap() {
        return seatMap;
    }

//SETTERS
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public void setGateNumber(int gateNumber) {
        this.gateNumber = gateNumber;
    }

//METHODS
    public boolean bookSeat(int row, char column, String passengerID) {
        if (row > this.row || (column - 'A') > this.column) {
            return false;
        } else if (!isSeatAvailable(row, column)) {
            return false;
        } else {
            seatMap[row][column - 'A'] = passengerID;
            return true;
        }
    }

    public boolean isSeatAvailable(int row, char column) {
        if ((seatMap[row][column - 'A']) != null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String toString() {
        return "Flight " + flightNumber + " added successfully";

    }

    public String printSeatPlan() {
        String s = new String(" Row           ");
        String d = new String("");

        for (int i = 0; i < column; i++) {
            String c;
            if (((char) (i + 97) == 'f')) {
                c = (char) (i + 97) + "           ";
            } else {
                c = (char) (i + 97) + "            ";
            }
            s += c;
        }
        s += "\n";
        for (int i = 0; i < row; i++) {
            if (i < 10) {
                s += " " + (i) + "            ";
            } else {
                s += " " + (i) + "           ";
            }
            for (int j = 0; j < column; j++) {
                if(j==column-1){
                    if ((seatMap[i][j]) != null) {
                    s += seatMap[i][j] + "       ";
                } else {
                    s += "O           ";
                }
                }
                else if ((seatMap[i][j]) != null) {
                    s += seatMap[i][j] + "       ";
                } else {
                    s += "O            ";
                }
            }
            s += "\n";
        }
        return s + "\n" + d;
    }
}
