package calculator.domain;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum DefaultDelimiter {
    COMMA(","),
    COLON(":");

    private static final String SEPARATOR = "";
    private static final String REGEX_PREFIX = "[";
    private static final String REGEX_SUFFIX = "]";

    private final String value;

    DefaultDelimiter(String value) {
        this.value = value;
    }

    public static String defaultSymbol() {
        return COMMA.value;
    }

    public static String pattern() {
        return Arrays.stream(values())
                .map(DefaultDelimiter::value)
                .collect(Collectors.joining(SEPARATOR, REGEX_PREFIX, REGEX_SUFFIX));
    }

    public String value() {
        return value;
    }
}
