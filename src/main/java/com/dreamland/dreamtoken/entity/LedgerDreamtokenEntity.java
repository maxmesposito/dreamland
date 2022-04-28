package com.dreamland.dreamtoken.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "ledger_dreamtoken", schema = "dreamland", catalog = "")
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Date getConversionToCurrencyDate() {
        return conversionToCurrencyDate;
    }

    public void setConversionToCurrencyDate(Date conversionToCurrencyDate) {
        this.conversionToCurrencyDate = conversionToCurrencyDate;
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
        LedgerDreamtokenEntity that = (LedgerDreamtokenEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(issueDate, that.issueDate) && Objects.equals(quantity, that.quantity) && Objects.equals(conversionToCurrencyDate, that.conversionToCurrencyDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, issueDate, quantity, conversionToCurrencyDate);
    }
}
