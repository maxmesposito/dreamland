package com.dreamland.dreamtoken.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "ledger_user", schema = "dreamland", catalog = "")
@Data
public class LedgerUserEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "insert_date")
    private Date insertDate;
    
    @Column(name = "entry_type")
    @Enumerated(EnumType.STRING)
    private EntryType entryType;
    
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

    public enum EntryType{
        CONVERT_TO_CURRENCY,
        FEE_AFTER_CONVERT
    }
}
