package com.example.crm.domain.entity;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

import java.io.Serializable;
import java.util.Date;
/** 
 * CustomerEntity
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -6242965382789029630L;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_ON", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Date createOn;
    
    @Version
    @Column(name = "VERSION")
    private Long Version;







}