package org.coderead.strategy;

public class ComedyCountStrategy extends CountStrategy {
    @Override
    public int countAmount(int audience) {
        int thisAmount = 30000;
        if (audience > 20) {
            thisAmount += 10000 + 500 * (audience - 20);
        }
        thisAmount += 300 * audience;
        return thisAmount;
    }

    @Override
    public int countCredit(int audience) {
        int thisCredit = Math.max(audience - 30, 0);
        thisCredit += Math.floor(audience / 5);
        return thisCredit;
    }

}
