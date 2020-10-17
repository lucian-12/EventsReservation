package data;

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

    int calculateVolumeCredits() {
        int result = 0;
        // add volume credits
        result += Math.max(getNbSeats() - 2, 0);
        // add extra credit for every workshop attendee
        if ("workshop".equals(getEvent().getType()))
            result += Math.floor(getNbSeats());
        return result;
    }
}
