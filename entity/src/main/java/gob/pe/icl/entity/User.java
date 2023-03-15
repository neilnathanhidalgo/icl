/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gob.pe.icl.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Usuario
 */
@Component
@Scope("prototype")
@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(catalog="develtrex",schema="develtrex",name = "user")
public class User extends GlobalEntityPkNumeric implements Serializable{

    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
}
