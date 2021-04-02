package org.coderead.statement;

import java.util.List;

public class StatementResult {
    private List<StateItemResult> resultList;

    public StatementResult(List<StateItemResult> resultList) {
        this.resultList = resultList;
    }

    public List<StateItemResult> getResultList() {
        return resultList;
    }

    public double getTotalAmount() {
        return resultList.stream().map(StateItemResult::getAmount).reduce(0, Integer::sum);
    }

    public int getVolumeCredits() {
        return resultList.stream().map(StateItemResult::getCredit).reduce(0, Integer::sum);
    }

}

