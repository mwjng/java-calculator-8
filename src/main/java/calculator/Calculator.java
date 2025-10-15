package calculator;

import calculator.domain.CustomDelimiter;
import calculator.view.InputView;
import calculator.view.OutputView;
import java.util.Optional;

public class Calculator {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        outputView.askInputString();
        String input = inputView.inputString();

        if (input.isBlank()) {
            outputView.showResult(0);
            return;
        }

        Optional<CustomDelimiter> customDelimiter = CustomDelimiter.from(input);
        if (customDelimiter.isPresent()) {
            CustomDelimiter delimiter = customDelimiter.get();
            input = delimiter.replaceWithDefaultDelimiter(input);
        }
    }
}
