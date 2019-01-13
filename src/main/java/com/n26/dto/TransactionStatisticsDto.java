package com.n26.dto;

import com.n26.collector.BigDecimalStatistics;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public final class TransactionStatisticsDto {

    private String sum;

    private String avg;

    private String max;

    private String min;

    private Long count;

    public static TransactionStatisticsDto fromStatistics(BigDecimalStatistics bigDecimalStatistics) {
        String sum = bigDecimalStatistics.getSum().toString();
        String avg = bigDecimalStatistics.getAverage().toString();
        String max = bigDecimalStatistics.getMaximum().toString();
        String min = bigDecimalStatistics.getMinimum().toString();
        Long count = bigDecimalStatistics.getCount();
        return new TransactionStatisticsDto(sum, avg, max, min, count);
    }

}
