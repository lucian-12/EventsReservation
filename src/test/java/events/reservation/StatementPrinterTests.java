package events.reservation;

import events.reservation.data.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.approvaltests.Approvals.verify;

public class StatementPrinterTests {

    @Test
    void exampleStatement() {

        Invoice invoice = new Invoice("BigCo", List.of(
                new ReservationConference("dev", 5, new Event("DevConference", "conference")),
                new ReservationWorkshop("web", 3, new Event("WebWorkshop", "workshop")),
                new ReservationConference("ml", 4, new Event("MachineLearning", "conference"))));

        StatementPrinter statementPrinter = new StatementPrinter();
        String result = statementPrinter.print(invoice);

        verify(result);
    }
}
