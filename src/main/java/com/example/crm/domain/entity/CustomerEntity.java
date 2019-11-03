package com.example.crm.domain.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/** 
 * CustomerEntity
 */

@Entity
@Table(name = "CUSTOMER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CustomerEntity extends BaseEntity {

    private static final long serialVersionUID = -2855941455504302128L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMER_ID", updatable= false, nullable = false)
    private Long customerId ;

    @ApiModelProperty(notes = "First name")
    @Column(name = "NAME_FIRST", nullable = false, length = 255)
    private String nameFirst;

    @ApiModelProperty(notes = "Middle name")
    @Column(name = "NAME_MIDDLE", nullable = true)
    private String nameMiddle;

    @ApiModelProperty(notes = "Last name")
    @Column(name = "NAME_LAST", nullable = false)
    private String nameLast ;

    @ApiModelProperty(notes = "Phone")
    @Column(name = "PHONE", nullable = true)
    private String phone ;

    @ApiModelProperty(notes = "Status: P = Prospective, C = Current, I = Idle")
    @Column(name = "STATUS", nullable = false)
    private char status;

    @ApiModelProperty(notes = "Note")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private List<CustomerNoteEntity> notes;

}