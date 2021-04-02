package org.coderead.statement;

import java.text.NumberFormat;
import java.util.Locale;

public class StatementPrinter {

    private StatementResult result;
    private NumberFormat format;

    public StatementPrinter(StatementResult result) {
        this.format = getNumberFormat();
        this.result = result;
    }

    public String print(String customer) {
        StringBuilder stringBuilder = init(customer);
        printItems(stringBuilder);
        printAmounts(stringBuilder);
        printCredits(stringBuilder);
        return stringBuilder.toString();
    }

    private StringBuilder init(String customer) {
        String result = String.format("Statement for %s", customer);
        return new StringBuilder(result);
    }

    private void printItems(StringBuilder stringBuilder) {
        this.result.getResultList().forEach(item -> printItem(stringBuilder, item));
    }

    private StringBuilder printCredits(StringBuilder stringBuilder) {
        return stringBuilder.append(String.format("You earned %s credits\n", this.result.getVolumeCredits()));
    }

    private StringBuilder printAmounts(StringBuilder stringBuilder) {
        return stringBuilder.append(String.format("Amount owed is %s\n", format.format(this.result.getTotalAmount() / 100)));
    }

    private StringBuilder printItem(StringBuilder stringBuilder, StateItemResult item) {
        String itemStr = String.format(" %s: %s (%d seats)\n", item.getName(), this.format.format(item.getAmount() / 100), item.getAudience());
        return stringBuilder.append(itemStr);
    }

    private NumberFormat getNumberFormat() {
        Locale locale = new Locale("en", "US");
        return NumberFormat.getCurrencyInstance(locale);
    }
}
