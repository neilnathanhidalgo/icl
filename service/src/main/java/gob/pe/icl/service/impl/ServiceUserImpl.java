/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gob.pe.icl.service.impl;

import com.jofrantoba.model.jpa.shared.UnknownException;
import gob.pe.icl.dao.inter.InterDaoUser;
import gob.pe.icl.entity.User;
import gob.pe.icl.service.inter.InterServiceUser;

import java.net.ServerSocket;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.hibernate.Transaction;


@Service
public class ServiceUserImpl implements InterServiceUser {

    @Autowired
    private InterDaoUser dao;

    @Override
    public User getUserById(Long id) throws  UnknownException {
        Transaction tx = dao.getSession().beginTransaction();
        User user;
        try {
            user = dao.findById(id);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new UnknownException(ServiceUserImpl.class, "No se pudo obtener el usuario con id " + id);
        }
        return user;
    }
    @Override
    public User saveUser(User entidad) throws UnknownException {
        Transaction tx = dao.getSession().beginTransaction();
        try {
            entidad.setIsPersistente(Boolean.TRUE);
            entidad.setVersion((new Date()).getTime());
            dao.save(entidad);
            tx.commit();
        } catch (Exception ex) {
            tx.rollback();
            throw new UnknownException(ServiceUserImpl.class, "No se a podido crear el usuario");
        }
        return entidad;
    }

}
