package swiftway_user;

import java.sql.Time;

public class ViewBookings_user {

    private String name;
    private String phone;
    private String source;
    private String destination;
    private String service;
    private String date;
    private String seats;
    private String amount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public ViewBookings_user(String name, String phone, String source, String destination, String service, String date,
            String seats, String amount) {
        this.name = name;
        this.phone = phone;
        this.source = source;
        this.destination = destination;
        this.service = service;
        this.date = date;
        this.seats = seats;
        this.amount = amount;
    }
}
