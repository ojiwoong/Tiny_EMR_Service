package com.hdjunction.tinyERMService.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "code_group")
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class CodeGroup {
    @Id
    @Column(nullable = false, length = 10)
    private String CodeGroup;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(nullable = false, length = 10)
    private String description;
}
