/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gob.pe.icl.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonView;
import gob.pe.icl.views.BikeView;
import gob.pe.icl.views.CarView;
import gob.pe.icl.views.PublicView;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Usuario
 */
@Component
@Scope("prototype")
@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(schema="develtrex",name = "user")
public class User extends GlobalEntityPkNumeric implements Serializable{

    @Column(name = "name")
    @JsonView(PublicView.class)
    private String name;
    @Column(name = "email")
    @JsonView(PublicView.class)
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonView(CarView.class)
    @JsonIgnoreProperties({"user"})
    private List<Car> cars = new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonView(BikeView.class)
    @JsonIgnoreProperties({"user"})
    private List<Bike> bikes = new ArrayList<>();
}
