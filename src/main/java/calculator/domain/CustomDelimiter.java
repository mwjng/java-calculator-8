package calculator.domain;

import java.util.Objects;
import java.util.Optional;

public class CustomDelimiter {
    private static final String BLANK_ERROR_MESSAGE = "커스텀 구분자가 공백이거나 비어있으면 안됩니다.";

    private static final String CUSTOM_DELIMITER_PREFIX = "//";
    private static final String CUSTOM_DELIMITER_SUFFIX = "\\n";
    private static final int PREFIX_LENGTH = CUSTOM_DELIMITER_PREFIX.length();

    private final String delimiter;

    private CustomDelimiter(String delimiter) {
        validateNotBlank(delimiter);
        this.delimiter = delimiter;
    }

    public static Optional<CustomDelimiter> from(String expression) {
        if (hasCustomDelimiter(expression)) {
            int delimiterEndIndex = expression.indexOf(CUSTOM_DELIMITER_SUFFIX);
            String customDelimiter = expression.substring(PREFIX_LENGTH, delimiterEndIndex);

            return Optional.of(new CustomDelimiter(customDelimiter));
        }
        return Optional.empty();
    }

    private static boolean hasCustomDelimiter(String expression) {
        return expression.startsWith(CUSTOM_DELIMITER_PREFIX)
                && expression.contains(CUSTOM_DELIMITER_SUFFIX);
    }

    public String replaceWithDefaultDelimiter(String expression) {
        int delimiterEndIndex = expression.indexOf(CUSTOM_DELIMITER_SUFFIX);
        String numbersSection = expression.substring(delimiterEndIndex + PREFIX_LENGTH);
        return numbersSection.replace(delimiter, DefaultDelimiter.defaultSymbol());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CustomDelimiter other = (CustomDelimiter) o;
        return Objects.equals(delimiter, other.delimiter);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(delimiter);
    }

    private void validateNotBlank(String delimiter) {
        if (delimiter.isBlank()) {
            throw new IllegalArgumentException(BLANK_ERROR_MESSAGE);
        }
    }
}
