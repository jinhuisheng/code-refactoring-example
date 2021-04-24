package org.coderead.model;

import org.coderead.trategy.Calculator;
import org.coderead.trategy.CalculatorFactory;

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

    public int getVolumeCredits(Map<String, Play> plays) {
        return count(plays, this::getVolumeCredit);
    }

    public int getTotalAmount(Map<String, Play> plays) {
        return count(plays, this::getAmount);
    }

    public int count(Map<String, Play> plays, BiFunction<String, Integer, Integer> function) {
        int total = 0;
        for (Performance performance : getPerformances()) {
            Play play = plays.get(performance.getPlayId());
            total += function.apply(play.getType(), performance.getAudience());
        }
        return total;
    }

    public int getAmount(String playType, int audience) {
        Calculator calculator = CalculatorFactory.getCalculator(playType);
        return calculator.getAmount(audience);
    }

    public int getVolumeCredit(String playType, int audience) {
        return CalculatorFactory.getCalculator(playType).getVolumeCredit(audience);
    }
}
