package org.coderead.model.strategy;

public class TragedyAmoutStrategy extends AmoutStrategy {
    @Override
    public int count(int audience) {
        int thisAmount = 40000;
        if (audience > 30) {
            thisAmount += 1000 * (audience - 30);
        }
        return thisAmount;
    }
}
