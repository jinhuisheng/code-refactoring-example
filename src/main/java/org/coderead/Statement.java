package org.coderead;

import org.coderead.model.AmountStrategies;
import org.coderead.model.Invoice;
import org.coderead.model.Performance;
import org.coderead.model.Play;

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

    private Invoice invoice;
    private Map<String, Play> plays;
    private AmountStrategies amountStrategies;

    public Statement(Invoice invoice, Map<String, Play> plays) {
        this.invoice = invoice;
        this.plays = plays;
        amountStrategies = new AmountStrategies();
    }

    public String show() {
        int totalAmount = 0;
        int volumeCredits = 0;
        String result = String.format("Statement for %s", invoice.getCustomer());
        StringBuilder stringBuilder = new StringBuilder(result);

        Locale locale = new Locale("en", "US");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);

        for (Performance performance : invoice.getPerformances()) {
            Play play = plays.get(performance.getPlayId());
            int thisAmount = amountStrategies.countAmount(play.getType(), performance.getAudience());
            int thisCredit = countCredit(performance, play);
            stringBuilder.append(String.format(" %s: %s (%d seats)\n", play.getName(), format.format(thisAmount / 100), performance.getAudience()));
            volumeCredits += thisCredit;
            totalAmount += thisAmount;
        }
        stringBuilder.append(String.format("Amount owed is %s\n", format.format(totalAmount / 100)));
        stringBuilder.append(String.format("You earned %s credits\n", volumeCredits));
        return stringBuilder.toString();
    }

    private int countCredit(Performance performance, Play play) {
        int thisCredit = Math.max(performance.getAudience() - 30, 0);
        double externalCredit = countExternalCredit(performance, play);
        thisCredit += externalCredit;
        return thisCredit;
    }

    private double countExternalCredit(Performance performance, Play play) {
        double externalCredit = 0;
        if ("comedy".equals(play.getType())) {
            externalCredit = Math.floor(performance.getAudience() / 5);
        }
        return externalCredit;
    }

}
