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

    private Invoice invoice;
    private Map<String, Play> plays;

    public Statement(Invoice invoice, Map<String, Play> plays) {
        this.invoice = invoice;
        this.plays = plays;
    }

    public String show() {
        StatementData data = new StatementData();
        data.setCustomer(invoice.getCustomer());
        data.setPerformances(invoice.getPerformances());
        return renderPlainText(data);
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
            stringBuilder.append(String.format(" %s: %s (%d seats)\n", playFor(performance).getName(), usd(amountFor(performance)), performance.getAudience()));
        }
        stringBuilder.append(String.format("Amount owed is %s\n", usd(totalAmount())));
        stringBuilder.append(String.format("You earned %s credits\n", totalVolumeCredits()));
        return stringBuilder.toString();
    }

    /**
     * 计算总金额
     * @return
     */
    private int totalAmount() {
        int totalAmount = 0;
        for (Performance performance : invoice.getPerformances()) {
            totalAmount += amountFor(performance);
        }
        return totalAmount;
    }

    /**
     * 获取观众量积分总和
     * @return 积分值
     */
    private int totalVolumeCredits() {
        int result = 0;
        for (Performance performance : invoice.getPerformances()) {
            result += volumeCreditsFor(performance);
        }
        return result;
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
    /**
     * 计算观众量积分
     * @param performance 表演
     * @return 观众量积分
     */
    private int volumeCreditsFor(Performance performance) {
        int result = 0;
        result += Math.max(performance.getAudience() - 30, 0);

        if ("comedy".equals(playFor(performance).getType())) {
            result += Math.floor(performance.getAudience() / 5);
        }
        return result;
    }

    /**
     * 查询表演的剧目
     * @param perf 表演
     * @return 剧目
     */
    private Play playFor(Performance perf) {
        return plays.get(perf.getPlayId());
    }

    /**
     * 计算一场戏剧演出的费用
     * @param perf 表演
     * @return
     */
    private int amountFor(Performance perf) {
        Play play = playFor(perf);
        int result = 0;
        switch (play.getType()) {
            case "tragedy":
                result = 40000;
                if (perf.getAudience() > 30) {
                    result += 1000 * (perf.getAudience() - 30);
                }
                break;
            case "comedy":
                result = 30000;
                if (perf.getAudience() > 20) {
                    result += 10000 + 500 *(perf.getAudience() - 20);
                }
                result += 300 * perf.getAudience();
                break;
            default:
                throw new RuntimeException("unknown type:" + play.getType());
        }
        return result;
    }
}
