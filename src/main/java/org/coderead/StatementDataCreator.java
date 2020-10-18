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
        PerformanceCalculator calculator = new PerformanceCalculator(performance, playFor(performance));
        performance.setPlay(calculator.play());
        performance.setAmount(calculator.amount());
        performance.setVolumeCredits(calculator.volumeCredits());
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
