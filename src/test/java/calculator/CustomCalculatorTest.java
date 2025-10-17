package calculator;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

public class CustomCalculatorTest extends NsTest {

    @Test
    void 기본_구분자() {
        assertSimpleTest(() -> {
            run("1,2,3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 단일_숫자_입력() {
        assertSimpleTest(() -> {
            run("6");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 기본_구분자_혼합() {
        assertSimpleTest(() -> {
            run("1,2:3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 커스텀_구분자() {
        assertSimpleTest(() -> {
            run("//;\\n1;2;3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 커스텀_구분자_여러_글자인_경우() {
        assertSimpleTest(() -> {
            run("//@!@!@\\n1@!@!@2@!@!@3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 구분자_혼합() {
        assertSimpleTest(() -> {
            run("//;\\n1;2,3:4");
            assertThat(output()).contains("결과 : 10");
        });
    }

    @Test
    void 공백_문자열() {
        assertSimpleTest(() -> {
            run(" ");
            assertThat(output()).contains("결과 : 0");
        });
    }

    @Test
    void 숫자_아닌_문자_포함() {
        assertSimpleTest(() -> {
            assertThatThrownBy(() -> runException("1,a,3"))
                    .isInstanceOf(IllegalArgumentException.class);
        });
    }

    @Test
    void 빈_값_포함() {
        assertSimpleTest(() -> {
            assertThatThrownBy(() -> runException("//;\\n1;;3"))
                    .isInstanceOf(IllegalArgumentException.class);
        });
    }

    @Test
    void 공백_포함() {
        assertSimpleTest(() -> {
            assertThatThrownBy(() -> runException("//;\\n1; ;3"))
                    .isInstanceOf(IllegalArgumentException.class);
        });
    }

    @Test
    void 공백_포함한_커스텀_구분자() {
        assertSimpleTest(() -> {
            run("//; \\n1; 2; 3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 숫자_0_포함() {
        assertSimpleTest(() -> {
            assertThatThrownBy(() -> runException("1,0,2"))
                    .isInstanceOf(IllegalArgumentException.class);
        });
    }

    @Test
    void 음수_포함() {
        assertSimpleTest(() -> {
            assertThatThrownBy(() -> runException("1,-2,3"))
                    .isInstanceOf(IllegalArgumentException.class);
        });
    }

    @Test
    void 숫자_뒤에_구분자만_존재() {
        assertSimpleTest(() -> {
            assertThatThrownBy(() -> runException("1,2,"))
                    .isInstanceOf(IllegalArgumentException.class);
        });
    }

    @Test
    void 기본_구분자_형식_오류() {
        assertSimpleTest(() -> {
            assertThatThrownBy(() -> runException("1,2;3"))
                    .isInstanceOf(IllegalArgumentException.class);
        });
    }

    @Test
    void 커스텀_구분자_형식_오류1() {
        assertSimpleTest(() -> {
            assertThatThrownBy(() -> runException("//;1;2;3"))
                    .isInstanceOf(IllegalArgumentException.class);
        });
    }

    @Test
    void 커스텀_구분자_형식_오류2() {
        assertSimpleTest(() -> {
            assertThatThrownBy(() -> runException("/;\n;1;2;3"))
                    .isInstanceOf(IllegalArgumentException.class);
        });
    }

    @Test
    void 커스텀_구분자_형식_오류3() {
        assertSimpleTest(() -> {
            assertThatThrownBy(() -> runException("//\\n1,2,3"))
                    .isInstanceOf(IllegalArgumentException.class);
        });
    }

    @Test
    void 커스텀_구분자_형식_오류4() {
        assertSimpleTest(() -> {
            assertThatThrownBy(() -> runException("// \\n1,2,3"))
                    .isInstanceOf(IllegalArgumentException.class);
        });
    }

    @Test
    void 커스텀_구분자_뒤에_빈줄_존재() {
        assertSimpleTest(() -> {
            assertThatThrownBy(() -> runException("//;\\n"))
                    .isInstanceOf(IllegalArgumentException.class);
        });
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
