package calculator.domain;

import java.util.Optional;

public class CustomDelimiter {
    private final String delimiter;

    private CustomDelimiter(String delimiter) {
        validateNotBlank(delimiter);
        this.delimiter = delimiter;
    }

    public static Optional<CustomDelimiter> from(String expression) {
        if (hasCustomDelimiter(expression)) {
            int delimiterEndIndex = expression.indexOf("\\n");
            String customDelimiter = expression.substring(2, delimiterEndIndex);

            return Optional.of(new CustomDelimiter(customDelimiter));
        }
        return Optional.empty();
    }

    private static boolean hasCustomDelimiter(String expression) {
        return expression.startsWith("//")
                && expression.contains("\\n");
    }

    private void validateNotBlank(String delimiter) {
        if (delimiter.isBlank()) {
            throw new IllegalArgumentException("커스텀 구분자가 공백이거나 비어있으면 안됩니다.");
        }
    }
}
