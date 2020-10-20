package events.reservation;

import java.util.List;

public class Invoice {

    private String customer;
    private List<Reservation> reservations;

    public Invoice(String customer, List<Reservation> reservations) {
        this.customer = customer;
        this.reservations = reservations;
    }

    public String getCustomer() {
        return customer;
    }

    public Invoice setCustomer(String customer) {
        this.customer = customer;
        return this;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public Invoice setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
        return this;
    }
}
