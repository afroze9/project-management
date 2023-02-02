package com.afroze.projectmanagement.project.api.data;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

/**
 *
 * @param <U> The type for auditors (users)
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<U> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "created_on", nullable = false)
    @CreatedDate
    private Date createdOn;

    @Column(name = "created_by", nullable = false)
    @CreatedBy
    private U createdBy;

    @Column(name = "modified_on", nullable = false)
    @LastModifiedDate
    private Date modifiedOn;

    @Column(name = "modified_by", nullable = false)
    @LastModifiedBy
    private U modifiedBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public U getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(U createdBy) {
        this.createdBy = createdBy;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public U getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(U modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}