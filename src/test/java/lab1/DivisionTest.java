package lab1;

import com.epam.tat.module4.Calculator;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DivisionTest {
    private Calculator calculator;

    @BeforeMethod
    public void setUps() {
        calculator = new Calculator();
    }

    @Test(groups = {"Long"}, expectedExceptions = NumberFormatException.class)
    public void longDivTest() {
        Assert.assertEquals(calculator.div(0, 1243), 0);
        Assert.assertEquals(calculator.div(Long.MAX_VALUE, Long.MAX_VALUE), 1);
        Assert.assertEquals(calculator.div(Long.MAX_VALUE, Long.MIN_VALUE + 1), -1);
        calculator.div(123124, 0);
    }

    @Test(groups = {"Double"})
    public void doubleDivTest() {
        Assert.assertEquals(calculator.div(88.2, 58.8), 1.5, 0.0001);
        Assert.assertEquals(calculator.div(-1751.61, 61.46), -28.5, 0.0001);
        calculator.div(-6541685.65468, 0.);
    }
}
