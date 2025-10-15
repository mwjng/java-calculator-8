package calculator.domain;

public class Number {
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
            throw new IllegalArgumentException("입력한 값이 숫자가 아닙니다.", e);
        }
    }

    public int value() {
        return number;
    }

    private void validatePositive(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("입력한 숫자는 양수여야 합니다.");
        }
    }
}
