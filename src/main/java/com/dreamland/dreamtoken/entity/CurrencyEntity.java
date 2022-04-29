package com.dreamland.dreamtoken.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "currency", schema = "dreamland", catalog = "")
@Data
public class CurrencyEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "code")
    private String code;
    
    @Column(name = "name")
    private String name;

}
