package org.fizz;

public enum MatchValueEnum {

    FIZZ(3, "3"),
    BUZZ(5, "5");

    private final int matchValue;
    private final String matchValueCharacter;

    MatchValueEnum(int matchValue, String matchValueCharacter) {
        this.matchValue = matchValue;
        this.matchValueCharacter = matchValueCharacter;
    }

    public int getMatchValue() {
        return matchValue;
    }

    public String getMatchValueCharacter() {
        return matchValueCharacter;
    }
}
