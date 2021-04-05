package org.coderead;

public class ComedyCalculator implements Calculator {
    public ComedyCalculator() {
    }

    @Override
    public int getAmount(int audience) {
        int thisAmount = 30000;
        if (audience > 20) {
            thisAmount += 10000 + 500 * (audience - 20);
        }
        thisAmount += 300 * audience;
        return thisAmount;
    }

    @Override
    public int getVolumeCredit(Integer audience) {
        int credit = Math.max(audience - 30, 0);
        credit += Math.floor(audience / 5);
        return credit;
    }
}
