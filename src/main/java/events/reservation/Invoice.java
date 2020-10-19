package events.reservation;

import events.reservation.data.StatementData;

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

    StatementData createStatementData() {
        StatementData statementData = new StatementData();
        statementData.setCustomer(getCustomer());
        statementData.setReservations(getReservations());
        statementData.setTotalAmount(statementData.calculateTotalAmount());
        statementData.setTotalVolumeCredits(statementData.calculateTotalVolumeCredits());
        return statementData;
    }
}
