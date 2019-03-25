package lab1;

import com.epam.tat.module4.Calculator;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

//Исправлено: один assert в методе, реализованы dataProvider'ы

public class MultiplicationTest {

    private Calculator calculator;
    private final static double epsilon = 0.0001;

    @DataProvider(name = "longDataProvider")
    public static Object[][] longData() {
        return new Object[][]{
                {5, 20, 100},
                {10, 10, 100},
                {0, Long.MAX_VALUE, 0},
                {Long.MAX_VALUE, 0, 0},
                {-5, 11, -55},
                {-5, -11, 55}
        };
    }

    @DataProvider(name = "doubleDataProvider")
    public static Object[][] doubleData() {
        return new Object[][]{
                {3.0, 2.25, 6.0},
                {25.5, -4.0, -102.0},
                {-17.64, -5.0, 88.0}
        };
    }

    @BeforeMethod
    public void setUps() {
        calculator = new Calculator();
    }

    @Test(groups = {"Long"}, dataProvider = "longDataProvider")
    public void longMultTest(long x, long y, long expected) {
        Assert.assertEquals(calculator.mult(x, y), expected);
    }

    @Test(groups = {"Double"}, dataProvider = "doubleDataProvider")
    public void doubleMultTest(double x, double y, double expected) {
        Assert.assertEquals(calculator.mult(x, y), expected, epsilon);
    }
}
