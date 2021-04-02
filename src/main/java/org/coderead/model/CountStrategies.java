package org.coderead.model;

import org.coderead.strategy.CountStrategy;
import org.coderead.strategy.ComedyCountStrategy;
import org.coderead.strategy.TragedyCountStrategy;

import java.util.HashMap;

public class CountStrategies {

    private HashMap<String, CountStrategy> strategies;

    public CountStrategies() {
        strategies = new HashMap<>();
        strategies.put("tragedy", new TragedyCountStrategy());
        strategies.put("comedy", new ComedyCountStrategy());
    }

    public int countAmount(String type, int audience) {
        CountStrategy countStrategy = strategies.get(type);
        return countStrategy.countAmount(audience);
    }

    public int countCredit(String type, int audience) {
        CountStrategy countStrategy = strategies.get(type);
        return countStrategy.countCredit(audience);
    }

}
