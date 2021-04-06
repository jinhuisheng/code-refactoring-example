package org.coderead;

import org.coderead.model.Invoice;
import org.coderead.model.Performance;
import org.coderead.model.Play;
import org.coderead.strategy.Calculator;

import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

/**
 * 客户服务类
 *
 * @author kendoziyu
 * @since 2020/10/11 0011
 */
public class Statement {

    private final Invoice invoice;
    private final Map<String, Play> plays;

    public Statement(Invoice invoice, Map<String, Play> plays) {
        this.invoice = invoice;
        this.plays = plays;
    }

    public String show() {
        return MessageFormat.format("{0}{1}{2}{3}",
                String.format("Statement for %s", invoice.getCustomer()),
                formatPerformances(),
                MessageFormat.format("Amount owed is {0}\n", format(invoice.getTotalAmount(plays))),
                MessageFormat.format("You earned {0} credits\n", invoice.getVolumeCredits(plays)));
    }

    private StringBuilder formatPerformances() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Performance performance : invoice.getPerformances()) {
            Play play = plays.get(performance.getPlayId());
            stringBuilder.append(formatPerformance(performance, play));
        }
        return stringBuilder;
    }

    private StringBuilder formatPerformance(Performance performance, Play play) {
        StringBuilder stringBuilder = new StringBuilder();
        Calculator calculator = Calculator.getCalculator(play.getType());
        stringBuilder.append(MessageFormat.format(" {0}: {1} ({2} seats)\n", play.getName(),
                format(calculator.getAmount(performance.getAudience())),
                performance.getAudience()));
        return stringBuilder;
    }

    private String format(int amount) {
        return NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(amount / 100);
    }

}
