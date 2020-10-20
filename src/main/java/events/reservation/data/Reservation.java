package events.reservation.data;

public abstract class Reservation {

    private String reservationID;
    private int nbSeats;
    private Event event;

    public Reservation(String reservationID, int nbSeats, Event event) {
        this.reservationID = reservationID;
        this.nbSeats = nbSeats;
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }

    public Reservation setEvent(Event event) {
        this.event = event;
        return this;
    }

    public String getReservationID() {
        return reservationID;
    }

    public Reservation setReservationID(String reservationID) {
        this.reservationID = reservationID;
        return this;
    }

    public int getNbSeats() {
        return nbSeats;
    }

    public Reservation setNbSeats(int nbSeats) {
        this.nbSeats = nbSeats;
        return this;
    }

    public abstract int calculateAmount();

    public abstract int calculateTotalVolumeCredits();
}
