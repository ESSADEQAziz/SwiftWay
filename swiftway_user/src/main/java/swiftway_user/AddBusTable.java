package swiftway_user;

import java.sql.Time;

public class AddBusTable {
    private final String service1;
    private  final String source1;
    private  final String destination1;
    private Integer fare1;
    private Time depart1;
    private Time arrival1;
    private Integer seat1;;
    private  final String date1;


    public String getService1() {
        return service1;
    }

    public String getSource1() {
        return source1;
    }

    public String getDestination1() {
        return destination1;
    }

    public Integer getFare1() {
        return fare1;
    }

    public void setFare1(Integer fare1) {
        this.fare1 = fare1;
    }

    public Time getDepart1() {
        return depart1;
    }

    public void setDepart1(Time depart1) {
        this.depart1 = depart1;
    }

    public Time getArrival1() {
        return arrival1;
    }

    public void setArrival1(Time arrival1) {
        this.arrival1 = arrival1;
    }

    public Integer getSeat1() {
        return seat1;
    }

    public void setSeat1(Integer seat1) {
        this.seat1 = seat1;
    }

    public String getDate1() {
        return date1;
    }

    public AddBusTable(String service1, String source1,
                       String destination1, Integer fare1, Time depart1, Time arrival1, Integer seat1, String date1) {
        this.service1 = service1;
        this.source1 = source1;
        this.destination1 = destination1;
        this.fare1 = fare1;
        this.depart1 = depart1;
        this.arrival1 = arrival1;
        this.seat1 = seat1;
        this.date1 = date1;
    }
}
