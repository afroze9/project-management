package com.afroze.projectmanagement.company.api.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "company", indexes = {
        @Index(name = "idx_company_id", columnList = "id")
}, uniqueConstraints = {
        @UniqueConstraint(name = "uc_company_name", columnNames = {"name"})
})
public class Company extends Auditable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String tags;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}