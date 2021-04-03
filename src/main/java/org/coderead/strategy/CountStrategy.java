package org.coderead.strategy;


public abstract class CountStrategy {

    public static CountStrategy getCountStrategy(String playType) {
        try {
            return (CountStrategy) Class
                    .forName(getPackage() + "." + toClassName(playType))
                    .getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("");
        }
    }

    private static String getPackage() {
        return CountStrategy.class.getPackage().getName();
    }

    private static String toClassName(String tragedy) {
        return tragedy.substring(0, 1).toUpperCase() + tragedy.substring(1) +
                "CountStrategy";
    }

    public abstract int countAmount(int audience);

    public abstract int countCredit(int audience);
}
