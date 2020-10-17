package data;

public class ConferenceReservation extends Reservation {
    public ConferenceReservation(String reservationID, int nbSeats, Event event) {
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
    int calculateVolumeCredits() {
        int result = 0;
        result += Math.max(getNbSeats() - 2, 0);
        return result;
    }
}
