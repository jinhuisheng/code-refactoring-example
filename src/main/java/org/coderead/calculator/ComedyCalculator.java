package org.coderead.calculator;

import org.coderead.model.Performance;
import org.coderead.model.Play;

/**
 * 喜剧计算器
 *
 * @author kendoziyu
 * @since 2020/10/18 0018
 */
public class ComedyCalculator extends PerformanceCalculator {

    public ComedyCalculator(Performance performance, Play play) {
        super(performance, play);
    }

    @Override
    public int amount() {
        int result = 30000;
        if (performance.getAudience() > 20) {
            result += 10000 + 500 *(performance.getAudience() - 20);
        }
        result += 300 * performance.getAudience();
        return result;
    }

    @Override
    public int volumeCredits() {
        return (int) (super.volumeCredits() + Math.floor(performance.getAudience() / 5));
    }
}
