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
        StatementResult statementResult = count();
        return print(statementResult);
    }

    private StatementResult count() {
        return new StatementCounter(invoice.getPerformances(), plays).count();
    }

    private String print(StatementResult statementResult) {
        return new StatementPrinter(statementResult).print(invoice.getCustomer());
    }

}
