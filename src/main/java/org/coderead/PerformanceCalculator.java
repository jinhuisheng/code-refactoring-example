package org.coderead;

import org.coderead.model.Performance;
import org.coderead.model.Play;

/**
 * 演出计算器
 *
 * @author kendoziyu
 * @since 2020/10/18 0018
 */
public class PerformanceCalculator {

    private Performance performance;
    private Play play;


    public PerformanceCalculator(Performance performance, Play play) {
        this.performance = performance;
        this.play = play;
    }

    public Play play() {
        return play;
    }

    /**
     * 计算一场戏剧演出的费用
     * @return
     */
    public int amount() {
        Play play = this.play;
        int result = 0;
        switch (play.getType()) {
            case "tragedy":
                result = 40000;
                if (performance.getAudience() > 30) {
                    result += 1000 * (performance.getAudience() - 30);
                }
                break;
            case "comedy":
                result = 30000;
                if (performance.getAudience() > 20) {
                    result += 10000 + 500 *(performance.getAudience() - 20);
                }
                result += 300 * performance.getAudience();
                break;
            default:
                throw new RuntimeException("unknown type:" + play.getType());
        }
        return result;
    }
}
