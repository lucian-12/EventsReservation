package data;

import java.util.List;

public class StatementData {
    private String customer;
    private List<Reservation> reservations;
    private int totalAmount;
    private int totalVolumeCredits;

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

    int calculateTotalVolumeCredits() {
        return getReservations().stream().mapToInt(Reservation::calculateVolumeCredits).sum();
    }

    int calculateTotalAmount() {
        return getReservations().stream().mapToInt(Reservation::calculateAmount).sum();
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalVolumeCredits(int totalVolumeCredits) {
        this.totalVolumeCredits = totalVolumeCredits;
    }

    public int getTotalVolumeCredits() {
        return totalVolumeCredits;
    }

}