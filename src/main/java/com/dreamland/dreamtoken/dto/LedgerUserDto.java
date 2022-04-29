package com.dreamland.dreamtoken.dto;

import com.dreamland.dreamtoken.entity.LedgerUserEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class LedgerUserDto {
    private Integer id;
    private Date insertDate;
    private LedgerUserEntity.EntryType entryType;
    private BigDecimal tokenAmount;
    private BigDecimal currencyAmount;
}
