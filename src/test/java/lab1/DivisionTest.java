package lab1;

import com.epam.tat.module4.Calculator;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

//Исправлено: один assert в методе, реализованы dataProvider'ы

public class DivisionTest {
    private Calculator calculator;
    private final static double epsilon = 0.0001;

    @DataProvider(name = "longDataProvider")
    public static Object[][] longData() {
        return new Object[][]{
                {0, 1234, 0},
                {Long.MAX_VALUE, Long.MAX_VALUE, 1},
                {Long.MAX_VALUE, Long.MIN_VALUE + 1, -1}
        };
    }

    @DataProvider(name = "doubleDataProvider")
    public static Object[][] doubleData() {
        return new Object[][]{
                {88.2, 58.8, 1.5},
                {-1751.61, 61.46, -28.5}
        };
    }

    @BeforeMethod
    public void setUps() {
        calculator = new Calculator();
    }

    @Test(groups = {"Long"}, dataProvider = "longDataProvider")
    public void longDivTest(long x, long y, long expected) {
        Assert.assertEquals(calculator.div(x, y), expected);
    }

    @Test(groups = {"Long"}, expectedExceptions = NumberFormatException.class)
    public void longDivExceptionTest() {
        calculator.div(123124, 0);
    }

    @Test(groups = {"Double"}, dataProvider = "doubleDataProvider")
    public void doubleDivTest(double x, double y, double expected) {
        Assert.assertEquals(calculator.div(x, y), expected, epsilon);
    }
}
