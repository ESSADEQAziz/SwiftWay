package swiftway_user;

public class Seat {

    private int seats;
    private boolean booked = false;

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public Seat(int seats, boolean booked) {
        this.seats = seats;
        this.booked = booked;
    }

    public Seat(int seats) {
        this(seats, false);
    }
}
