package org.coderead.model;

import org.coderead.statement.StateItemResult;
import org.coderead.statement.StatementResult;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public StatementResult count(Map<String, Play> plays) {
        List<StateItemResult> statementItems = getPerformances().stream()
                .map(performance -> performance.getStatementItemResult(plays.get(performance.getPlayId())))
                .collect(Collectors.toList());
        return new StatementResult(statementItems);
    }
}
