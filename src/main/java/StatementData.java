import java.util.List;

public class StatementData {
    private String customer;
    private List<Reservation> reservations;

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
}
