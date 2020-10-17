public class Reservation {

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

    int calculateAmount() {
        int result;

        switch (getEvent().getType()) {
            case "conference":
                result = 40000;
                if (getNbSeats() > 3) {
                    result += 1000 * (getNbSeats() - 3);
                }
                break;
            case "workshop":
                result = 30000;
                if (getNbSeats() > 2) {
                    result += 10000 + 500 * (getNbSeats() - 2);
                }
                result += 300 * getNbSeats();
                break;
            default:
                throw new Error("unknown type: ${event.type}");
        }
        return result;
    }

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
