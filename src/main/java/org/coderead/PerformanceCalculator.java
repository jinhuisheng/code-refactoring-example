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
}
