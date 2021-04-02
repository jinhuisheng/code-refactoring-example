package org.coderead;

import org.coderead.model.Invoice;
import org.coderead.model.Play;
import org.coderead.statement.StatementCounter;
import org.coderead.statement.StatementPrinter;
import org.coderead.statement.StatementResult;

import java.util.Map;

/**
 * 客户服务类
 *
 * @author kendoziyu
 * @since 2020/10/11 0011
 */
public class Statement {

    private Invoice invoice;
    private Map<String, Play> plays;

    public Statement(Invoice invoice, Map<String, Play> plays) {
        this.invoice = invoice;
        this.plays = plays;
    }

    public String show() {
        return print(count());
    }

    private StatementResult count() {
        StatementCounter statementCounter = new StatementCounter(invoice.getPerformances(), plays);
        return statementCounter.count();
    }

    private String print(StatementResult result1) {
        StatementPrinter statementPrinter = new StatementPrinter(result1);
        return statementPrinter.print(invoice.getCustomer());
    }

}
