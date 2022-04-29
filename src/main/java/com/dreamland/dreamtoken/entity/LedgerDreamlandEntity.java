package com.dreamland.dreamtoken.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "ledger_dreamland", schema = "dreamland", catalog = "")
@Data
public class LedgerDreamlandEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "insert_date")
    private Date insertDate;
    
    @Column(name = "fee")
    private BigDecimal fee;

}
