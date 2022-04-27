package com.hdjunction.tinyERMService.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "code")
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Code {
    @Id
    @Column(nullable = false, length = 20)
    private String code;

    @Column(nullable = false, length = 20)
    private String codeGroup;

    @Column(nullable = false, length = 20)
    private String name;
}
