package data;

public class WorkshopReservation extends Reservation {
    public WorkshopReservation(String reservationID, int nbSeats, Event event) {
        super(reservationID, nbSeats, event);
    }

    @Override
    public int calculateAmount() {
        int result = 30000;
        if (getNbSeats() > 2) {
            result += 10000 + 500 * (getNbSeats() - 2);
        }
        result += 300 * getNbSeats();
        return result;
    }
}
