import java.text.NumberFormat;
import java.util.Locale;

public class StatementPrinter {

    public String print(Invoice invoice) {
        StatementData statementData = invoice.createStatementData();

        return renderPlainText(statementData);
    }

    private String renderPlainText(StatementData statementData) {
        StringBuilder result = new StringBuilder(String.format("Statement for %s\n", statementData.getCustomer()));

        for (Reservation reservation : statementData.getReservations()) {
            // print line for this order
            result.append(String.format("  %s: %s (%s seats)\n", reservation.getEvent().getName(), usd(reservation.calculateAmount()), reservation.getNbSeats()));
        }

        result.append(String.format("Amount owed is %s\n", usd(statementData.getTotalAmount())));
        result.append(String.format("You earned %s credits\n", statementData.getTotalVolumeCredits()));
        return result.toString();
    }

    private String usd(int aNumber) {
        NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);
        return frmt.format(aNumber / 100);
    }

}
