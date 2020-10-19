package events.reservation.data;

import java.util.List;

public class StatementData {
    private String customer;
    private List<Reservation> reservations;
    private int calculateTotalAmount;
    private int calculateTotalVolumeCredits;

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getCustomer() {
        return customer;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setTotalAmount(int calculateTotalAmount) {
        this.calculateTotalAmount = calculateTotalAmount;
    }

    public int getTotalAmount() {
        return calculateTotalAmount;
    }

    public void setTotalVolumeCredits(int calculateTotalVolumeCredits) {
        this.calculateTotalVolumeCredits = calculateTotalVolumeCredits;
    }

    public int getTotalVolumeCredits() {
        return calculateTotalVolumeCredits;
    }

    public int calculateTotalVolumeCredits() {
        return getReservations().stream().mapToInt(Reservation::calculateTotalVolumeCredits).sum();
    }

    public int calculateTotalAmount() {
        return getReservations().stream().mapToInt(Reservation::calculateAmount).sum();
    }
}