package calculator.domain;

import java.util.Objects;

public class Number {
    private static final String NOT_POSITIVE_ERROR_MESSAGE = "입력한 숫자는 양수여야 합니다.";
    private static final String NOT_INT_ERROR_MESSAGE = "입력한 값이 올바른 숫자가 아닙니다.";

    private final int number;

    private Number(int number) {
        validatePositive(number);
        this.number = number;
    }

    public static Number from(String rawValue) {
        try {
            int parsedNumber = Integer.parseInt(rawValue);
            return new Number(parsedNumber);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_INT_ERROR_MESSAGE, e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Number other = (Number) o;
        return number == other.number;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(number);
    }

    public int value() {
        return number;
    }

    private void validatePositive(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException(NOT_POSITIVE_ERROR_MESSAGE);
        }
    }
}
