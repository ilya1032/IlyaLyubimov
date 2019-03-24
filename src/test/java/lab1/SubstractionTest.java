package lab1;

import com.epam.tat.module4.Calculator;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class SubstractionTest {
    private Calculator calculator;

    @BeforeMethod
    public void setUps() {
        calculator = new Calculator();
    }

    @Test(groups = {"Long"})
    public void longSubTest() {
        // TODO обычно правило 1 тест - 1 assert.
        // TODO пердлагаю разделить тесты и прибегруть к использованию DataProvider
        Assert.assertEquals(calculator.sub(75, 25), 50);
        Assert.assertEquals(calculator.sub(Long.MAX_VALUE, Long.MAX_VALUE), 0);
        Assert.assertEquals(calculator.sub(Long.MIN_VALUE, 1), Long.MAX_VALUE);
        Assert.assertNotEquals(calculator.sum(1, 1), 100);
    }

    @Test(groups = {"Double"})
    public void doubleSubTest() {
        // TODO обычно правило 1 тест - 1 assert.
        // TODO пердлагаю разделить тесты и прибегруть к использованию DataProvider
        Assert.assertEquals(calculator.sub(2.375, 0.625), 1.75, 0.0001);
        Assert.assertEquals(calculator.sub(75.0, 25.0), 50, 0.0001);
    }
}
