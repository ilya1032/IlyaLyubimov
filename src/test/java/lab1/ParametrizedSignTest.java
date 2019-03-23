package lab1;

import com.epam.tat.module4.Calculator;
// TODO неиспользуемый импорт
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static org.testng.Assert.assertNotEquals;

public class ParametrizedSignTest {

    private Calculator calculator;

    @BeforeMethod
    public void setUps() {
        calculator = new Calculator();
    }

    @Test(groups = {"Long"})
    @Parameters(value = "positiveLong")
    // TODO Для каких целей используется параметризация?
    public void longPositiveSignTest(long positiveLong) {
        assertTrue(calculator.isPositive(positiveLong));
        assertTrue(calculator.isPositive(25));
        assertFalse(calculator.isPositive(-35));
        assertNotEquals(calculator.isPositive(1), false);
    }

    @Test(groups = {"Long"})
    @Parameters(value= "negativeLong")
    // TODO Для каких целей используется параметризация?
    public void longNegativeSignTest(long negativeLong) {
        assertTrue(calculator.isNegative(negativeLong));
        assertTrue(calculator.isNegative(-25));
        assertFalse(calculator.isNegative(35));
        assertNotEquals(calculator.isNegative(-1), false);
    }
}
