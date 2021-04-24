package org.coderead;

import org.coderead.model.Invoice;
import org.coderead.model.Performance;
import org.coderead.model.Play;

import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

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
        return String.format("Statement for %s", invoice.getCustomer()) +
                formatPerformances() +
                MessageFormat.format("Amount owed is {0}\n", formatNumber(invoice.getTotalAmount(plays))) +
                MessageFormat.format("You earned {0} credits\n", invoice.getVolumeCredits(plays));
    }

    private String formatPerformances() {
        return invoice.getPerformances().stream()
                .map(this::getFormatPerformance)
                .collect(Collectors.joining());
    }

    private String getFormatPerformance(Performance performance) {
        Play play = plays.get(performance.getPlayId());
        return formatPerformance(performance, play, invoice.getAmount(play.getType(), performance.getAudience()));
    }

    private String formatPerformance(Performance performance, Play play, int thisAmount) {
        return MessageFormat.format(" {0}: {1} ({2} seats)\n"
                , play.getName()
                , formatNumber(thisAmount)
                , performance.getAudience());
    }

    private String formatNumber(int thisAmount) {
        return NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(thisAmount / 100);
    }
}
