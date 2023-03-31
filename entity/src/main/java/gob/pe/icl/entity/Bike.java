/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gob.pe.icl.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
@Component
@Scope("prototype")
@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(schema="develtrex",name = "bike")
public class Bike extends GlobalEntityPkNumeric implements Serializable {
    @Column(name = "brand")
    private String brand;
    @Column(name = "model")
    private String model;
    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}