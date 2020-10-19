package events.reservation;

import events.reservation.data.StatementData;

import java.text.NumberFormat;
import java.util.Locale;

public class StatementPrinter {

    public String print(Invoice invoice) {
        StatementData statementData = invoice.createStatementData();
        return renderPlainText(statementData);
    }

    private String renderPlainText(StatementData statementData) {
        StringBuilder result = new StringBuilder(String.format("Statement for %s\n",
                statementData.getCustomer()));

        for (Reservation reservation : statementData.getReservations()) {

            // print line for this order
            result.append(String.format("  %s: %s (%s seats)\n",
                    reservation.getEvent().getName(),
                    usd(reservation.calculateAmount()),
                    reservation.getNbSeats()));
        }

        result.append(String.format("Amount owed is %s\n", usd(statementData.getCalculateTotalAmount())));
        result.append(String.format("You earned %s credits\n", statementData.getCalculateTotalVolumeCredits()));
        return result.toString();
    }

    private String usd(int i) {
        NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);
        return frmt.format(i / 100);
    }

}
