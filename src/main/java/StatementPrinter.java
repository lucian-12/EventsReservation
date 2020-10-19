import java.text.NumberFormat;
import java.util.Locale;

public class StatementPrinter {

    public String print(Invoice invoice) {
        StringBuilder result = new StringBuilder(String.format("Statement for %s\n",
                invoice.getCustomer()));

        for (Reservation reservation : invoice.getReservations()) {

            // print line for this order
            result.append(String.format("  %s: %s (%s seats)\n",
                    reservation.getEvent().getName(),
                    usd(calculateAmount(reservation)),
                    reservation.getNbSeats()));
        }

        result.append(String.format("Amount owed is %s\n", usd(calculateTotalAmount(invoice))));
        result.append(String.format("You earned %s credits\n", calculateVolumeCredits(invoice)));
        return result.toString();
    }

    private int calculateTotalAmount(Invoice invoice) {
        return invoice.getReservations().stream().mapToInt(this::calculateAmount).sum();
    }

    private int calculateVolumeCredits(Invoice invoice) {
        return invoice.getReservations().stream().mapToInt(this::calculateVolumeCredits).sum();
    }

    private String usd(int i) {
        NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);
        return frmt.format(i / 100);
    }

    private int calculateVolumeCredits(Reservation reservation) {
        int result = Math.max(reservation.getNbSeats() - 2, 0);
        if ("workshop".equals(reservation.getEvent().getType())) {
            result += Math.floor(reservation.getNbSeats());
        }
        return result;
    }

    private int calculateAmount(Reservation reservation) {
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
