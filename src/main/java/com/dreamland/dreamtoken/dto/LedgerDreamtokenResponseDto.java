package com.dreamland.dreamtoken.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class LedgerDreamtokenResponseDto {
    private Integer id;
    private Date issueDate;
    private BigDecimal quantity;
    private Date conversionToCurrencyDate;
}
