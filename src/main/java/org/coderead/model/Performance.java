package org.coderead.model;

import org.coderead.statement.StateItemResult;
import org.coderead.strategy.CountStrategy;

/**
 * 表演
 *
 * @author kendoziyu
 * @since 2020/10/11 0011
 */
public class Performance {

    private String playId;

    private int audience;

    public String getPlayId() {
        return playId;
    }

    public void setPlayId(String playId) {
        this.playId = playId;
    }

    public int getAudience() {
        return audience;
    }

    public void setAudience(int audience) {
        this.audience = audience;
    }

    public StateItemResult getStatementItemResult(Play play) {
        CountStrategy countStrategy = CountStrategy.getCountStrategy(play.getType());
        int thisAmount = countStrategy.countAmount(getAudience());
        int thisCredit = countStrategy.countCredit(getAudience());
        return new StateItemResult(play.getName(), getAudience(), thisAmount, thisCredit);
    }
}
