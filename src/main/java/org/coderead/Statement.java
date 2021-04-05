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
    private final NumberFormat format;

    public Statement(Invoice invoice, Map<String, Play> plays) {
        this.invoice = invoice;
        this.plays = plays;
        format = NumberFormat.getCurrencyInstance(new Locale("en", "US"));
    }

    public String show() {
        return MessageFormat.format("{0}{1}{2}{3}",
                begin(),
                getPerformanceFormats(),
                MessageFormat.format("Amount owed is {0}\n", format.format(invoice.getTotalAmount(plays) / 100)),
                MessageFormat.format("You earned {0} credits\n", invoice.getVolumeCredits(plays)));
    }

    private String begin() {
        return String.format("Statement for %s", invoice.getCustomer());
    }

    private StringBuilder getPerformanceFormats() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Performance performance : invoice.getPerformances()) {
            Play play = plays.get(performance.getPlayId());
            stringBuilder.append(getPerformanceFormats(performance, play));
        }
        return stringBuilder;
    }

    private StringBuilder getPerformanceFormats(Performance performance, Play play) {
        StringBuilder temp = new StringBuilder();
        Calculator calculator = Calculator.getCalculator(play.getType());
        temp.append(MessageFormat.format(" {0}: {1} ({2} seats)\n", play.getName(),
                this.format.format(calculator.getAmount(performance.getAudience()) / 100),
                performance.getAudience()));
        return temp;
    }

}
