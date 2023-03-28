/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gob.pe.icl.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import gob.pe.icl.views.PublicView;
import lombok.Data;
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
@Table(schema="develtrex",name = "car")
public class Car extends GlobalEntityPkNumeric implements Serializable {
    @Column(name = "brand")
    @JsonView(PublicView.class)
    private String brand;
    @Column(name = "model")
    @JsonView(PublicView.class)
    private String model;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"cars"})
    private User user;
}
