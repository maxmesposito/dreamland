package com.dreamland.dreamtoken.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "dreamtoken_rate", schema = "dreamland", catalog = "")
@Data
public class DreamtokenRateEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "start_date")
    private Date startDate;
    
    @Column(name = "end_date")
    private Date endDate;
    
    @Column(name = "exchange_rate_to_currency")
    private BigDecimal exchangeRateToCurrency;
    
    @Column(name = "dreamland_fee")
    private BigDecimal dreamlandFee;

    @ManyToOne
    @JoinColumn(name = "id_currency", referencedColumnName = "id", nullable = false)
    private CurrencyEntity currency;

}
