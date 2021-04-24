package org.coderead.trategy;

public class ComedyCalculator implements Calculator{
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
    public int getVolumeCredit(int audience) {
        int volumeCredit = Math.max(audience - 30, 0);
        volumeCredit += Math.floor((double) audience / 5);
        return volumeCredit;
    }

}
