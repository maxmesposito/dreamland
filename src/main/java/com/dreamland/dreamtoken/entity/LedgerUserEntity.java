package com.dreamland.dreamtoken.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public String getEntryType() {
        return entryType;
    }

    public void setEntryType(String entryType) {
        this.entryType = entryType;
    }

    public BigDecimal getTokenAmount() {
        return tokenAmount;
    }

    public void setTokenAmount(BigDecimal tokenAmount) {
        this.tokenAmount = tokenAmount;
    }

    public BigDecimal getCurrencyAmount() {
        return currencyAmount;
    }

    public void setCurrencyAmount(BigDecimal currencyAmount) {
        this.currencyAmount = currencyAmount;
    }

    public DreamtokenRateEntity getDreamtokenRate() {
        return dreamtokenRate;
    }

    public void setDreamtokenRate(DreamtokenRateEntity dreamtokenRate) {
        this.dreamtokenRate = dreamtokenRate;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LedgerUserEntity that = (LedgerUserEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(insertDate, that.insertDate) && Objects.equals(entryType, that.entryType) && Objects.equals(tokenAmount, that.tokenAmount) && Objects.equals(currencyAmount, that.currencyAmount) && Objects.equals(dreamtokenRate, that.dreamtokenRate) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, insertDate, entryType, tokenAmount, currencyAmount, dreamtokenRate, user);
    }
}
