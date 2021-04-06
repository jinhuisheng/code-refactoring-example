package org.coderead.model;

import org.coderead.strategy.Calculator;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * 发票
 *
 * @author kendoziyu
 * @since 2020/10/11 0011
 */
public class Invoice {

    private String customer;

    private List<Performance> performances;

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public List<Performance> getPerformances() {
        return performances;
    }

    public void setPerformances(List<Performance> performances) {
        this.performances = performances;
    }

    public int getTotalAmount(Map<String, Play> plays) {
        return count(plays, this::getAmount);
    }

    public int getVolumeCredits(Map<String, Play> plays) {
        return count(plays, this::getVolumeCredit);
    }

    public int count(Map<String, Play> plays, BiFunction<String, Integer, Integer> func) {
        int total = 0;
        for (Performance performance : getPerformances()) {
            Play play = plays.get(performance.getPlayId());
            total += func.apply(play.getType(), performance.getAudience());
        }
        return total;
    }

    private int getAmount(String playType, int audience) {
        Calculator calculator = Calculator.getCalculator(playType);
        return calculator.getAmount(audience);
    }

    private int getVolumeCredit(String playType, int audience) {
        Calculator calculator = Calculator.getCalculator(playType);
        return calculator.getVolumeCredit(audience);
    }

}
