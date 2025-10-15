package calculator.domain;

import java.util.Arrays;
import java.util.List;

public class Numbers {
    private final List<Number> numbers;

    private Numbers(List<Number> numbers) {
        this.numbers = numbers;
    }

    public static Numbers from(String expression) {
        String[] splitNumbers = expression.split("[,:]");
        List<Number> parsedNumbers = Arrays.stream(splitNumbers)
                .map(Number::from)
                .toList();

        return new Numbers(parsedNumbers);
    }
}
