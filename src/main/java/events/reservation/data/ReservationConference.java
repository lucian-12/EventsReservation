package events.reservation.data;

public class ReservationConference extends Reservation {
    public ReservationConference(String reservationID, int nbSeats, Event event) {
        super(reservationID, nbSeats, event);
    }

    @Override
    public int calculateAmount() {
        int result = 40000;
        if (getNbSeats() > 3) {
            result += 1000 * (getNbSeats() - 3);
        }
        return result;
    }

    @Override
    public int calculateTotalVolumeCredits() {
        return Math.max(getNbSeats() - 2, 0);
    }
}
