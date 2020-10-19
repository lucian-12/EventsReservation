package events.reservation;

import events.reservation.data.Invoice;
import events.reservation.data.Reservation;
import events.reservation.data.StatementData;

import java.text.NumberFormat;
import java.util.Locale;

public class StatementPrinter {

    public String print(Invoice invoice) {
        StatementData statementData = invoice.createStatementData();
        return renderPlainText(statementData);
    }

    private String renderHTMLText(StatementData statementData) {
        StringBuilder result = new StringBuilder("<h1>" + String.format("Statement for %s\n", statementData.getCustomer()) + "</h1>");
        result.append("<table>\n");
        for (Reservation reservation : statementData.getReservations()) {
            result.append("<tr><th>").append(reservation.getEvent().getType()).append("</th>");
            result.append("<th>").append(reservation.getNbSeats()).append("</th>");
            result.append("<th>").append(usd(reservation.calculateAmount())).append("</th>");
            result.append("</tr>\n");
        }
        result.append("</table>\n");
        result.append("<p>").append(String.format("Amount owed is %s\n", usd(statementData.getTotalAmount()))).append("</p>\n");
        result.append("<p>").append(String.format("You earned %s credits\n", statementData.getTotalVolumeCredits())).append("</p>\n");

        return result.toString();
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

        result.append(String.format("Amount owed is %s\n", usd(statementData.getTotalAmount())));
        result.append(String.format("You earned %s credits\n", statementData.getTotalVolumeCredits()));
        return result.toString();
    }

    private String usd(int i) {
        NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);
        return frmt.format(i / 100);
    }

}
