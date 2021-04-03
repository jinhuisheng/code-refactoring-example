package org.coderead.statement;

import org.coderead.model.Performance;
import org.coderead.model.Play;
import org.coderead.strategy.CountStrategy;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StatementCounter {
    private final List<Performance> performances;
    private final Map<String, Play> plays;

    public StatementCounter(List<Performance> performances, Map<String, Play> plays) {
        this.performances = performances;
        this.plays = plays;
    }

    public StatementResult count() {
        List<StateItemResult> statementItems = this.performances.stream()
                .map(this::getStatementItemResult).collect(Collectors.toList());
        return new StatementResult(statementItems);
    }

    private StateItemResult getStatementItemResult(Performance performance) {
        Play play = plays.get(performance.getPlayId());
        CountStrategy countStrategy = CountStrategy.getCountStrategy(play.getType());
        int thisAmount = countStrategy.countAmount(performance.getAudience());
        int thisCredit = countStrategy.countCredit(performance.getAudience());
        return new StateItemResult(play.getName(), performance.getAudience(), thisAmount, thisCredit);
    }
}
