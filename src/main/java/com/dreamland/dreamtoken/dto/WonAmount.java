package com.dreamland.dreamtoken.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class WonAmount {
    private Integer idUser;
    private BigDecimal tokenAmount;
}
