package lab1;

import com.epam.tat.module4.Calculator;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

//Исправлено: один assert в методе, реализованы dataProvider'ы

public class SubstractionTest {
    private Calculator calculator;
    private final static double epsilon = 0.0001;

    @DataProvider(name = "longDataProvider")
    public static Object[][] longData() {
        return new Object[][]{
                {75, 25, 50},
                {Long.MAX_VALUE, Long.MAX_VALUE, 0},
                {Long.MIN_VALUE, 1, Long.MAX_VALUE}
        };
    }

    @DataProvider(name = "doubleDataProvider")
    public static Object[][] doubleData() {
        return new Object[][]{
                {2.375, 0.625, 1.75},
                {75.0, 25.0, 50.0},
        };
    }

    @BeforeMethod
    public void setUps() {
        calculator = new Calculator();
    }

    @Test(groups = {"Long"}, dataProvider = "longDataProvider")
    public void longSubTest(long x, long y, long expected) {
        Assert.assertEquals(calculator.sub(x, y), expected);
    }

    @Test(groups = {"Double"}, dataProvider = "doubleDataProvider")
    public void doubleSubTest(double x, double y, double expected) {
        Assert.assertEquals(calculator.sub(x, y), expected, epsilon);
    }
}
