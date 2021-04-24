package org.coderead.trategy;

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
    public int getVolumeCredit(int audience) {
        return Math.max(audience - 30, 0);
    }
}
