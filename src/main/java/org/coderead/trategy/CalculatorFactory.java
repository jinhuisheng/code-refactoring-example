package org.coderead.trategy;

import java.util.HashMap;
import java.util.Objects;

public class CalculatorFactory {

    private static final HashMap<String, Calculator> map;

    static {
        map = new HashMap<>();
        map.put("tragedy", new TragedyCalculator());
        map.put("comedy", new ComedyCalculator());
    }

     public static Calculator getCalculator(String type) {
        Calculator calculator = map.get(type);
        if (Objects.isNull(calculator)) {
            throw new IllegalArgumentException("unknown type:" + type);
        }
        return calculator;
    }
}
