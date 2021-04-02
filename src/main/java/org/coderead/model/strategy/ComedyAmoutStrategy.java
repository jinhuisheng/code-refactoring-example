package org.coderead.model.strategy;

public class ComedyAmoutStrategy extends AmoutStrategy {
    @Override
    public int count(int audience) {
        int thisAmount = 30000;
        if (audience > 20) {
            thisAmount += 10000 + 500 * (audience - 20);
        }
        thisAmount += 300 * audience;
        return thisAmount;
    }
}
