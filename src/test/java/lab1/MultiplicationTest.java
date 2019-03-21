package lab1;

import com.epam.tat.module4.Calculator;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MultiplicationTest {

    private Calculator calculator;

    @BeforeMethod
    public void setUps() {
        calculator = new Calculator();
    }

    @Test(groups = {"Long"})
    public void longMultTest() {
        Assert.assertEquals(calculator.mult(5, 20), 100);
        Assert.assertEquals(calculator.mult(10, 10), 100);
        Assert.assertEquals(calculator.mult(0, Long.MAX_VALUE), 0);
        Assert.assertEquals(calculator.mult(Long.MAX_VALUE, 0), 0);
        Assert.assertEquals(calculator.mult(-5, 11), -55);
        Assert.assertEquals(calculator.mult(-5, -11), 55);
        Assert.assertNotEquals(calculator.mult(288, -375), 347);
    }

    @Test(groups = {"Double"})
    public void doubleMultTest() {
        Assert.assertEquals(calculator.mult(3.0,2.25), 6.0, 0.0001);
        Assert.assertEquals(calculator.mult(25.5, -4.0), -102.0, 0.0001);
        Assert.assertEquals(calculator.mult(-17.64f, -5.0f), 88.0, 0.0001);
    }
}
