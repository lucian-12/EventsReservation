import java.text.NumberFormat;
import java.util.Locale;

public class StatementPrinter {

    public String print(Invoice invoice) {
        int totalAmount = 0;
        int volumeCredits = 0;
        StringBuilder result = new StringBuilder(String.format("Statement for %s\n", invoice.getCustomer()));

        NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

        for (Reservation reservation : invoice.getReservations()) {
            int thisAmount = amountFor(reservation);

            volumeCredits += volumeCreditsFor(reservation);

            // print line for this order
            result.append(String.format("  %s: %s (%s seats)\n", reservation.getEvent().getName(), frmt.format(thisAmount / 100), reservation.getNbSeats()));
            totalAmount += thisAmount;
        }
        result.append(String.format("Amount owed is %s\n", frmt.format(totalAmount / 100)));
        result.append(String.format("You earned %s credits\n", volumeCredits));
        return result.toString();
    }

    private int volumeCreditsFor(Reservation reservation) {
        int result = 0;
        // add volume credits
        result += Math.max(reservation.getNbSeats() - 2, 0);
        // add extra credit for every workshop attendee
        if ("workshop".equals(reservation.getEvent().getType()))
            result += Math.floor(reservation.getNbSeats());
        return result;
    }

    private int amountFor(Reservation reservation) {
        int result;

        switch (reservation.getEvent().getType()) {
            case "conference":
                result = 40000;
                if (reservation.getNbSeats() > 3) {
                    result += 1000 * (reservation.getNbSeats() - 3);
                }
                break;
            case "workshop":
                result = 30000;
                if (reservation.getNbSeats() > 2) {
                    result += 10000 + 500 * (reservation.getNbSeats() - 2);
                }
                result += 300 * reservation.getNbSeats();
                break;
            default:
                throw new Error("unknown type: ${event.type}");
        }
        return result;
    }

}
