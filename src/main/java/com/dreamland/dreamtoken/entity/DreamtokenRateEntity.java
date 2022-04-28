package com.dreamland.dreamtoken.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "dreamtoken_rate", schema = "dreamland", catalog = "")
public class DreamtokenRateEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "start_date")
    private Date startDate;
    
    @Column(name = "end_date")
    private int endDate;
    
    @Column(name = "exchange_rate_to_currency")
    private BigDecimal exchangeRateToCurrency;
    
    @Column(name = "dreamland_fee")
    private BigDecimal dreamlandFee;

    @ManyToOne
    @JoinColumn(name = "id_currency", referencedColumnName = "id", nullable = false)
    private CurrencyEntity currency;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getEndDate() {
        return endDate;
    }

    public void setEndDate(int endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getExchangeRateToCurrency() {
        return exchangeRateToCurrency;
    }

    public void setExchangeRateToCurrency(BigDecimal exchangeRateToCurrency) {
        this.exchangeRateToCurrency = exchangeRateToCurrency;
    }

    public BigDecimal getDreamlandFee() {
        return dreamlandFee;
    }

    public void setDreamlandFee(BigDecimal dreamlandFee) {
        this.dreamlandFee = dreamlandFee;
    }

    public CurrencyEntity getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyEntity currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DreamtokenRateEntity that = (DreamtokenRateEntity) o;
        return endDate == that.endDate && Objects.equals(id, that.id) && Objects.equals(startDate, that.startDate) && Objects.equals(exchangeRateToCurrency, that.exchangeRateToCurrency) && Objects.equals(dreamlandFee, that.dreamlandFee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDate, endDate, exchangeRateToCurrency, dreamlandFee);
    }


}
