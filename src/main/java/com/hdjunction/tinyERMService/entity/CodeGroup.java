package com.hdjunction.tinyERMService.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "code_group")
public class CodeGroup {
    @Id
    @Column(nullable = false, length = 10)
    private String CodeGroup;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(nullable = false, length = 10)
    private String description;
}
