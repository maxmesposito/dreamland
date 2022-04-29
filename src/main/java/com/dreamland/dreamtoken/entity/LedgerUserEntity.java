package com.dreamland.dreamtoken.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "ledger_user", schema = "dreamland", catalog = "")
public class LedgerUserEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "insert_date")
    private Date insertDate;
    
    @Column(name = "entry_type")
    private String entryType;
    
    @Column(name = "token_amount")
    private BigDecimal tokenAmount;
    
    @Column(name = "currency_amount")
    private BigDecimal currencyAmount;

    @ManyToOne
    @JoinColumn(name = "id_dreamtoken_rate", referencedColumnName = "id", nullable = false)
    private DreamtokenRateEntity dreamtokenRate;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id", nullable = false)
    private UserEntity user;
}
