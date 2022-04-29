package com.dreamland.dreamtoken.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class WonAmountRequestDto {
    private Integer idUser;
    private BigDecimal tokenAmount;
}
