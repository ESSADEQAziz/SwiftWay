package swiftway_user;

import java.sql.Time;

public class Service {

    private String service;
    private String source;
    private String destination;
    private Integer fare;
    private Time dtime;
    private Time atime;
    private Integer seats;
    private String dt;

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
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

    public Integer getFare() {
        return fare;
    }

    public void setFare(Integer fare) {
        this.fare = fare;
    }

    public Time getDtime() {
        return dtime;
    }

    public void setDtime(Time dtime) {
        this.dtime = dtime;
    }

    public Time getAtime() {
        return atime;
    }

    public void setAtime(Time atime) {
        this.atime = atime;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public Service(String service, String source, String destination, Integer fare, Time dtime, Time atime, String dt) {
        this.service = service;
        this.source = source;
        this.destination = destination;
        this.fare = fare;
        this.dtime = dtime;
        this.atime = atime;
        this.dt = dt;

    }
}