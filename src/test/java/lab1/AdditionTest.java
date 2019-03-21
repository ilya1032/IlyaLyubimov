package lab1;

import com.epam.tat.module4.Calculator;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AdditionTest {

    private Calculator calculator;

    @BeforeMethod
    public void setUps() {
        calculator = new Calculator();
    }

    @Test(groups = {"Long"})
    public void longSumTest() {
        Assert.assertEquals(calculator.sum(75, 25), 100);
        Assert.assertEquals(calculator.sum(Long.MAX_VALUE, Long.MIN_VALUE), -1);
        Assert.assertNotEquals(calculator.sum(1, 1), 100);
    }

    @Test(groups = {"Double"})
    public void doubleSumTest() {
        Assert.assertEquals(calculator.sum(11.2, 22.1), 33.3, 0.0001);
        Assert.assertEquals(calculator.sum(0.0, 1.33), 1.33, 0.0001);
        Assert.assertNotEquals(calculator.sum(1.0, 1.0), 100.27, 0.0001);
    }
}
