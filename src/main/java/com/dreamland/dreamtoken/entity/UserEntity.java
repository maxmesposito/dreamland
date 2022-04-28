package com.dreamland.dreamtoken.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "dreamland", catalog = "")
public class UserEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "deleted")
    private Boolean deleted;

    @OneToMany(mappedBy = "user")
    private Collection<LedgerDreamtokenEntity> ledgerDreamtokens;

    @OneToMany(mappedBy = "user")
    private Collection<LedgerUserEntity> ledgerUsers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Collection<LedgerDreamtokenEntity> getLedgerDreamtokens() {
        return ledgerDreamtokens;
    }

    public void setLedgerDreamtokens(Collection<LedgerDreamtokenEntity> ledgerDreamtokens) {
        this.ledgerDreamtokens = ledgerDreamtokens;
    }

    public Collection<LedgerUserEntity> getLedgerUsers() {
        return ledgerUsers;
    }

    public void setLedgerUsers(Collection<LedgerUserEntity> ledgerUsers) {
        this.ledgerUsers = ledgerUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id.equals(that.id) && Objects.equals(name, that.name) && deleted.equals(that.deleted) && Objects.equals(ledgerDreamtokens, that.ledgerDreamtokens) && Objects.equals(ledgerUsers, that.ledgerUsers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, deleted, ledgerDreamtokens, ledgerUsers);
    }
}
