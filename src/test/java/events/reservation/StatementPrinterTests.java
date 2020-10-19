package events.reservation;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.approvaltests.Approvals.verify;

public class StatementPrinterTests {

    @Test
    void exampleStatement() {

        Invoice invoice = new Invoice("BigCo", List.of(
                new Reservation("dev", 5, new Event("DevConference", "conference")),
                new Reservation("web", 3, new Event("WebWorkshop", "workshop")),
                new Reservation("ml", 4, new Event("MachineLearning", "conference"))));

        StatementPrinter statementPrinter = new StatementPrinter();
        String result = statementPrinter.print(invoice);

        verify(result);
    }
}
