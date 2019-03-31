package lab1;

import com.epam.tat.module4.Calculator;
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
    public void longPositiveSignTest(long positiveLong) {
        assertTrue(calculator.isPositive(positiveLong));
    }

    @Test(groups = {"Long"})
    @Parameters(value= "negativeLong")
    public void longNegativeSignTest(long negativeLong) {
        assertTrue(calculator.isNegative(negativeLong));
    }
}
