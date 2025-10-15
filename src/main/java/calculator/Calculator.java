package calculator;

import calculator.domain.CustomDelimiter;
import calculator.domain.Numbers;
import calculator.view.InputView;
import calculator.view.OutputView;
import java.util.Optional;

public class Calculator {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        String input = readInputString();
        int result = calculateSum(input);
        outputView.showResult(result);
    }

    private String readInputString() {
        outputView.askInputString();
        return inputView.inputString();
    }

    private int calculateSum(String input) {
        if (input.isBlank()) {
            return 0;
        }
        input = replaceCustomDelimiter(input);
        Numbers numbers = Numbers.from(input);
        return numbers.sum();
    }

    private String replaceCustomDelimiter(String input) {
        Optional<CustomDelimiter> customDelimiter = CustomDelimiter.from(input);
        if (customDelimiter.isPresent()) {
            CustomDelimiter delimiter = customDelimiter.get();
            input = delimiter.replaceWithDefaultDelimiter(input);
        }
        return input;
    }
}
