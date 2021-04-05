package org.coderead;

public interface Calculator {
    static Calculator getCalculator(String playType) {
        try {
            return (Calculator) Class.forName(
                    getPackageName() +
                            "." +
                            toClassName(playType)).getDeclaredConstructor().newInstance();
        } catch (Exception exception) {
            throw new RuntimeException("unknown type:" + playType);
        }
    }

    static String getPackageName() {
        return Calculator.class.getPackage().getName();
    }

    static String toClassName(String tragedy) {
        return tragedy.substring(0, 1).toUpperCase() +
                tragedy.substring(1) +
                "Calculator";
    }

    int getAmount(int audience);

    int getVolumeCredit(Integer audience);
}
