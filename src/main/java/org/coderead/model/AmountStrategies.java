package org.coderead.model;

import org.coderead.model.strategy.AmoutStrategy;
import org.coderead.model.strategy.ComedyAmoutStrategy;
import org.coderead.model.strategy.TragedyAmoutStrategy;

import java.util.HashMap;

public class AmountStrategies {
    public AmountStrategies() {
    }

    public int countAmount(String type, int audience) {
        HashMap<String, AmoutStrategy> map = new HashMap<String, AmoutStrategy>();
        map.put("tragedy", new TragedyAmoutStrategy());
        map.put("comedy", new ComedyAmoutStrategy());
        AmoutStrategy amoutStrategy = map.get(type);
        return amoutStrategy.count(audience);
    }
}
