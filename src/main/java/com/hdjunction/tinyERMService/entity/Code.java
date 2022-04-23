package com.hdjunction.tinyERMService.entity;

import javax.persistence.*;

@Entity
@Table(name = "code")
public class Code {
    @Id
    @Column(nullable = false, length = 10)
    private String code;

    @Column(nullable = false, length = 10)
    private String codeGroup;

    @Column(nullable = false, length = 10)
    private String name;
}
