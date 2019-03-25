package lab1;

import com.epam.tat.module4.Calculator;
// TODO неиспользуемый импорт
//исправлено
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ParametrizedSignTest {

    private Calculator calculator;

    @BeforeMethod
    public void setUps() {
        calculator = new Calculator();
    }

    @Test(groups = {"Long"})
    @Parameters(value = "positiveLong")
    // TODO Для каких целей используется параметризация?
    //Исправлено: один assert в методе, метод параметризован
    public void longPositiveSignTest(long positiveLong) {
        assertTrue(calculator.isPositive(positiveLong));
    }

    @Test(groups = {"Long"})
    @Parameters(value= "negativeLong")
    // TODO Для каких целей используется параметризация?
    //Исправлено: один assert в методе, метод параметризован
    public void longNegativeSignTest(long negativeLong) {
        assertTrue(calculator.isNegative(negativeLong));
    }
}
