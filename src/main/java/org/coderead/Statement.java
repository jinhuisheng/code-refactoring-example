package org.coderead;

import org.coderead.model.Invoice;
import org.coderead.model.Performance;
import org.coderead.model.Play;
import org.coderead.model.StatementData;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

/**
 * 详单类
 *
 * @author kendoziyu
 * @since 2020/10/11 0011
 */
public class Statement {

    private StatementDataCreator dataCreator;

    public Statement(Invoice invoice, Map<String, Play> plays) {
        dataCreator = new StatementDataCreator(invoice, plays);
    }

    public String show() {
        return renderPlainText(dataCreator.createStatementData());
    }

    public String showHtml() {
        return renderHtml(dataCreator.createStatementData());
    }

    private String renderHtml(StatementData data) {
        String title = String.format("<h1>Statement for %s</h1>\n", data.getCustomer());
        StringBuilder result = new StringBuilder(title);

        result.append("<table>\n");
        result.append("<tr><th>play</th><th>seats</th><th>cost</th></tr>\n");
        for (Performance performance : data.getPerformances()) {
            result.append(String.format(" <tr><td>%s</td><td>%d</td><td>%s</td></tr>\n", performance.getPlay().getName(), performance.getAudience(), usd(performance.getAmount())));
        }
        result.append("</table>\n");
        result.append(String.format("<p>Amount owed is <em>%s</em></p>\n", usd(data.getTotalAmount())));
        result.append(String.format("<p>You earned <em>%s</em> credits</p>\n", data.getTotalVolumeCredits()));
        return result.toString();
    }

    /**
     * 使用纯文本渲染
     * @param data 详单数据
     * @return
     */
    private String renderPlainText(StatementData data) {
        String result = String.format("Statement for %s\n", data.getCustomer());
        StringBuilder stringBuilder = new StringBuilder(result);

        for (Performance performance : data.getPerformances()) {
            stringBuilder.append(String.format(" %s: %s (%d seats)\n", performance.getPlay().getName(), usd(performance.getAmount()), performance.getAudience()));
        }
        stringBuilder.append(String.format("Amount owed is %s\n", usd(data.getTotalAmount())));
        stringBuilder.append(String.format("You earned %s credits\n", data.getTotalVolumeCredits()));
        return stringBuilder.toString();
    }



    /**
     * 以美元格式化货币
     * @param amount 数量
     * @return 美元形式
     */
    private String usd(int amount) {
        Locale locale = new Locale("en", "US");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        return format.format(amount /100);
    }
}
