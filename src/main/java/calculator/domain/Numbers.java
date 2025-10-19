package calculator.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Numbers {
    private final List<Number> numbers;

    private Numbers(List<Number> numbers) {
        this.numbers = numbers;
    }

    public static Numbers from(String expression) {
        String[] splitNumbers = expression.split(DefaultDelimiter.pattern(), -1);
        List<Number> parsedNumbers = Arrays.stream(splitNumbers)
                .map(Number::from)
                .toList();

        return new Numbers(parsedNumbers);
    }

    public int sum() {
        return numbers.stream()
                .mapToInt(Number::value)
                .sum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Numbers other = (Numbers) o;
        return Objects.equals(numbers, other.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numbers);
    }
}
