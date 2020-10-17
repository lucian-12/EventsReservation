import java.text.NumberFormat;
import java.util.Locale;

public class StatementPrinter {

    public String print(Invoice invoice) {
        int totalAmount = 0;
        int volumeCredits = 0;
        StringBuilder result = new StringBuilder(String.format("Statement for %s\n", invoice.getCustomer()));

        NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

        for (Reservation reservation : invoice.getReservations()) {
            Event event = reservation.getEvent();
            int thisAmount = 0;

            switch (event.getType()) {
                case "conference":
                    thisAmount = 40000;
                    if (reservation.getNbSeats() > 3) {
                        thisAmount += 1000 * (reservation.getNbSeats() - 3);
                    }
                    break;
                case "workshop":
                    thisAmount = 30000;
                    if (reservation.getNbSeats() > 2) {
                        thisAmount += 10000 + 500 * (reservation.getNbSeats() - 2);
                    }
                    thisAmount += 300 * reservation.getNbSeats();
                    break;
                default:
                    throw new Error("unknown type: ${event.type}");
            }

            // add volume credits
            volumeCredits += Math.max(reservation.getNbSeats() - 2, 0);
            // add extra credit for every workshop attendee
            if ("workshop".equals(event.getType())) volumeCredits += Math.floor(reservation.getNbSeats());

            // print line for this order
            result.append(String.format("  %s: %s (%s seats)\n", event.getName(), frmt.format(thisAmount / 100), reservation.getNbSeats()));
            totalAmount += thisAmount;
        }
        result.append(String.format("Amount owed is %s\n", frmt.format(totalAmount / 100)));
        result.append(String.format("You earned %s credits\n", volumeCredits));
        return result.toString();
    }

}
