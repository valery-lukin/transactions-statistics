package com.n26;

import com.n26.collector.BigDecimalStatistics;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BigDecimalStatisticsTest {

    private static final BigDecimal TWO = new BigDecimal("2");
    private static final BigDecimal THREE = new BigDecimal("3");
    private static final BigDecimal FIVE = new BigDecimal("5");
    private static final BigDecimal AVG = new BigDecimal("3.33");

    @Test
    public void whenTwoAndThree_thenMinEqualsTwo() {
        BigDecimalStatistics bigDecimalStatistics = new BigDecimalStatistics();
        bigDecimalStatistics.accept(TWO);
        bigDecimalStatistics.accept(THREE);
        Assert.assertEquals(0, TWO.compareTo(bigDecimalStatistics.getMinimum()));
    }

    @Test
    public void whenTwoAndThree_thenMaxEqualsThree() {
        BigDecimalStatistics bigDecimalStatistics = new BigDecimalStatistics();
        bigDecimalStatistics.accept(TWO);
        bigDecimalStatistics.accept(THREE);
        Assert.assertEquals(0, THREE.compareTo(bigDecimalStatistics.getMaximum()));
    }

    @Test
    public void whenTwoThreeAndFive_thenSumEqualsTen() {
        BigDecimalStatistics bigDecimalStatistics = new BigDecimalStatistics();
        bigDecimalStatistics.accept(TWO);
        bigDecimalStatistics.accept(THREE);
        bigDecimalStatistics.accept(FIVE);
        Assert.assertEquals(0, BigDecimal.TEN.compareTo(bigDecimalStatistics.getSum()));
    }

    @Test
    public void whenTwoThreeAndFive_thenAvgEqualsThreePointThree() {
        BigDecimalStatistics bigDecimalStatistics = new BigDecimalStatistics();
        bigDecimalStatistics.accept(TWO);
        bigDecimalStatistics.accept(THREE);
        bigDecimalStatistics.accept(FIVE);
        Assert.assertEquals(0, AVG.compareTo(bigDecimalStatistics.getAverage()));
    }

    @Test
    public void whenEmpty_thenMinEqualsZero() {
        BigDecimalStatistics bigDecimalStatistics = new BigDecimalStatistics();
        Assert.assertEquals(0, BigDecimal.ZERO.compareTo(bigDecimalStatistics.getMinimum()));
    }
}
