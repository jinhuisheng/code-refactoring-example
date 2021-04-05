package org.coderead;

public class TragedyCalculator implements Calculator {
    public TragedyCalculator() {
    }

    @Override
    public int getAmount(int audience) {
        int thisAmount = 40000;
        if (audience > 30) {
            thisAmount += 1000 * (audience - 30);
        }
        return thisAmount;
    }

    @Override
    public int getVolumeCredit(Integer audience) {
        return Math.max(audience - 30, 0);
    }
}
