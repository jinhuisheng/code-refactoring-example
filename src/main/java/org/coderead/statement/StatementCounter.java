package org.coderead.statement;

import org.coderead.model.CountStrategies;
import org.coderead.model.Performance;
import org.coderead.model.Play;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StatementCounter {
    private final CountStrategies countStrategies;
    private final List<Performance> performances;
    private final Map<String, Play> plays;

    public StatementCounter(List<Performance> performances, Map<String, Play> plays) {
        this.performances = performances;
        this.plays = plays;
        this.countStrategies = new CountStrategies();
    }

    public StatementResult count() {
        List<StateItemResult> statementItems = this.performances.stream()
                .map(this::getStatementItemResult).collect(Collectors.toList());
        return new StatementResult(statementItems);
    }

    private StateItemResult getStatementItemResult(Performance performance) {
        Play play = plays.get(performance.getPlayId());
        int thisAmount = countStrategies.countAmount(play.getType(), performance.getAudience());
        int thisCredit = countStrategies.countCredit(play.getType(), performance.getAudience());
        return new StateItemResult(play.getName(), performance.getAudience(), thisAmount, thisCredit);
    }
}
