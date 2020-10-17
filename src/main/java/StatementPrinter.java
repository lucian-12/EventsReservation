import java.text.NumberFormat;
import java.util.Locale;

public class StatementPrinter {

    public String print(Invoice invoice) {
        StatementData statementData = new StatementData();
        statementData.setCustomer(invoice.getCustomer());
        statementData.setReservations(invoice.getReservations());

        return renderPlainText(statementData);
    }

    private String renderPlainText(StatementData statementData) {
        StringBuilder result = new StringBuilder(String.format("Statement for %s\n", statementData.getCustomer()));

        for (Reservation reservation : statementData.getReservations()) {
            // print line for this order
            result.append(String.format("  %s: %s (%s seats)\n", reservation.getEvent().getName(), usd(amountFor(reservation)), reservation.getNbSeats()));
        }

        result.append(String.format("Amount owed is %s\n", usd(totalAmount(statementData))));
        result.append(String.format("You earned %s credits\n", totalVolumeCredits(statementData)));
        return result.toString();
    }

    private int totalAmount(StatementData statementData) {
        int result = 0;
        for (Reservation reservation : statementData.getReservations()) {
            result += amountFor(reservation);
        }
        return result;
    }

    private int totalVolumeCredits(StatementData statementData) {
        int result = 0;
        for (Reservation reservation : statementData.getReservations()) {
            result += volumeCreditsFor(reservation);
        }
        return result;
    }

    private String usd(int aNumber) {
        NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);
        return frmt.format(aNumber / 100);
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
