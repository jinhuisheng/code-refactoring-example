package org.coderead;

import org.coderead.model.Invoice;
import org.coderead.model.Performance;
import org.coderead.model.Play;
import org.coderead.model.StatementData;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * 详单数据创建器
 *
 * @author kendoziyu
 * @since 2020/10/18 0018
 */
public class StatementDataCreator {

    private Invoice invoice;
    private Map<String, Play> plays;

    public StatementDataCreator(Invoice invoice, Map<String, Play> plays) {
        this.invoice = invoice;
        this.plays = plays;
    }

    public StatementData createStatementData() {
        StatementData data = new StatementData();
        data.setCustomer(invoice.getCustomer());
        data.setPerformances(invoice.getPerformances().stream().map(this::enrichPerformance).collect(Collectors.toList()));
        data.setTotalAmount(totalAmount(data));
        data.setTotalVolumeCredits(totalVolumeCredits(data));
        return data;
    }

    private Performance enrichPerformance(Performance performance) {
        Play play = playFor(performance);
        performance.setPlay(play);
        performance.setAmount(amountFor(performance));
        performance.setVolumeCredits(volumeCreditsFor(performance));
        return performance;
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
        Play play = perf.getPlay();
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

    /**
     * 计算观众量积分
     * @param performance 表演
     * @return 观众量积分
     */
    private int volumeCreditsFor(Performance performance) {
        int result = 0;
        result += Math.max(performance.getAudience() - 30, 0);

        if ("comedy".equals(performance.getPlay().getType())) {
            result += Math.floor(performance.getAudience() / 5);
        }
        return result;
    }

    /**
     * 计算总金额
     * @return
     * @param data
     */
    private int totalAmount(StatementData data) {
        return data.getPerformances().stream().mapToInt(Performance::getAmount).sum();
    }

    /**
     * 获取观众量积分总和
     * @return 积分值
     * @param data
     */
    private int totalVolumeCredits(StatementData data) {
        return data.getPerformances().stream().mapToInt(Performance::getVolumeCredits).sum();
    }
}
