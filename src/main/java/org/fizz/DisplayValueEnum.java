package org.fizz;

public enum DisplayValueEnum {

    FIZZ("Fizz"),
    BUZZ("Buzz"),
    FIZZ_BUZZ("FizzBuzz");

    private final String displayValue;

    DisplayValueEnum(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
