package com.dreamland.dreamtoken.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class StatsResponseDto {
    private BigDecimal tokenSumCurrentDay;
    private BigDecimal currenciesSum;

    public StatsResponseDto(){
        this.tokenSumCurrentDay = BigDecimal.ZERO;
        this.currenciesSum = BigDecimal.ZERO;
    }
}
