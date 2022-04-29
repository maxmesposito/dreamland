package com.dreamland.dreamtoken.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "ledger_dreamtoken", schema = "dreamland", catalog = "")
@Data
public class LedgerDreamtokenEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "issue_date")
    private Date issueDate;
    
    @Column(name = "quantity")
    private BigDecimal quantity;
    
    @Column(name = "conversion_to_currency_date")
    private Date conversionToCurrencyDate;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id", nullable = false)
    private UserEntity user;

}
