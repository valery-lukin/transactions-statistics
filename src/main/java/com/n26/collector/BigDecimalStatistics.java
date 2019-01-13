package com.n26.collector;

import java.math.BigDecimal;
import java.util.function.Consumer;
import java.util.stream.Collector;

public final class BigDecimalStatistics implements Consumer<BigDecimal> {

    private static final int ROUND_HALF_UP = BigDecimal.ROUND_HALF_UP;

    private static final Integer TRANSACTIONS_SCALE = 2;

    private BigDecimal sum = BigDecimal.ZERO;
    private BigDecimal minimum = BigDecimal.ZERO;
    private BigDecimal maximum = BigDecimal.ZERO;
    private long count;

    public static Collector<BigDecimal, ?, BigDecimalStatistics> statistics() {

        return Collector.of(BigDecimalStatistics::new, BigDecimalStatistics::accept,
                BigDecimalStatistics::merge);
    }

    @Override
    public void accept(BigDecimal t) {

        if (count == 0) {
            firstElementSetup(t);
        } else {
            sum = sum.add(t);
            minimum = minimum.min(t);
            maximum = maximum.max(t);
            count++;
        }
    }

    public BigDecimalStatistics merge(BigDecimalStatistics s) {
        if (s.count > 0) {
            if (count == 0) {
                setupFirstElement(s);
            } else {
                sum = sum.add(s.sum);
                minimum = minimum.min(s.minimum);
                maximum = maximum.max(s.maximum);
                count += s.count;
            }
        }
        return this;
    }

    private void setupFirstElement(BigDecimalStatistics s) {
        count = s.count;
        sum = s.sum;
        minimum = s.minimum;
        maximum = s.maximum;
    }

    private void firstElementSetup(BigDecimal t) {
        count = 1;
        sum = t;
        minimum = t;
        maximum = t;
    }

    public BigDecimal getAverage() {
        if (count == 0) {
            return BigDecimalStatistics.getWithScale(BigDecimal.ZERO);
        }
        return sum.divide(BigDecimal.valueOf(count), TRANSACTIONS_SCALE,
                ROUND_HALF_UP);

    }

    public BigDecimal getSum() {
        return BigDecimalStatistics.getWithScale(sum);
    }

    public BigDecimal getMinimum() {
        return BigDecimalStatistics.getWithScale(minimum);
    }

    public BigDecimal getMaximum() {
        return BigDecimalStatistics.getWithScale(maximum);
    }

    public long getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "MyBigDecimalSummaryCollector [sum=" + sum + ", minimum=" + minimum + ", maximum=" + maximum + ", count="
                + count + "]";
    }

    private static BigDecimal getWithScale(BigDecimal bigDecimal) {
        return bigDecimal.setScale(TRANSACTIONS_SCALE, ROUND_HALF_UP);
    }
}
