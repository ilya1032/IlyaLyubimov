package lab1;

import com.epam.tat.module4.Calculator;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AdditionTest {

    private Calculator calculator;
    private final static double epsilon = 0.0001;

    @DataProvider(name = "longDataProvider")
    public static Object[][] longData() {
        return new Object[][]{
                {75, 25, 100},
                {Long.MAX_VALUE, Long.MIN_VALUE, -1}
        };
    }

    @DataProvider(name = "doubleDataProvider")
    public static Object[][] doubleData() {
        return new Object[][]{
                {11.2, 22.1, 33.3},
                {0.0, 1.33, 1.33}
        };
    }

    @BeforeMethod
    public void setUps() {
        calculator = new Calculator();
    }

    @Test(groups = {"Long"}, dataProvider = "longDataProvider")
    public void longSumTest(long x, long y, long expected) {
        Assert.assertEquals(calculator.sum(x, y), expected);
    }

    @Test(groups = {"Double"}, dataProvider = "doubleDataProvider")
    public void doubleSumTest(double x, double y, double expected) {
        Assert.assertEquals(calculator.sum(x, y), expected, epsilon);
    }
}
