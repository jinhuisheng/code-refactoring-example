package org.coderead.statement;

public class StateItemResult {
    private final String name;
    private final int audience;
    private final int amount;
    private final int credit;

    public StateItemResult(String name, int audience, int amount, int credit) {
        this.name = name;
        this.audience = audience;
        this.amount = amount;
        this.credit = credit;
    }

    public String getName() {
        return name;
    }

    public int getAudience() {
        return audience;
    }

    public int getAmount() {
        return amount;
    }

    public int getCredit() {
        return credit;
    }
}
