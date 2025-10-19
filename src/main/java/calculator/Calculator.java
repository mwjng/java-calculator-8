package calculator;

import calculator.domain.CustomDelimiter;
import calculator.domain.Numbers;
import calculator.view.InputView;
import calculator.view.OutputView;

public class Calculator {
    private static final int DEFAULT_SUM = 0;

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        String expression = readInputExpression();
        int result = calculateSum(expression);
        outputView.showResult(result);
    }

    private String readInputExpression() {
        outputView.askInputExpression();
        return inputView.inputExpression();
    }

    private int calculateSum(String expression) {
        if (expression.isBlank()) {
            return DEFAULT_SUM;
        }
        expression = applyDefaultDelimiter(expression);
        Numbers numbers = Numbers.from(expression);
        return numbers.sum();
    }

    private String applyDefaultDelimiter(String expression) {
        return CustomDelimiter.from(expression)
                .map(delimiter -> delimiter.replaceWithDefaultDelimiter(expression))
                .orElse(expression);
    }
}
