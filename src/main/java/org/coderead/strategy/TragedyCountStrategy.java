package org.coderead.strategy;

public class TragedyCountStrategy extends CountStrategy {
    @Override
    public int countAmount(int audience) {
        int thisAmount = 40000;
        if (audience > 30) {
            thisAmount += 1000 * (audience - 30);
        }
        return thisAmount;
    }
    @Override
    public int countCredit(int audience) {
        return Math.max(audience - 30, 0);
    }

}
