package com.dreamland.dreamtoken.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "user", schema = "dreamland", catalog = "")
@Data
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

}
