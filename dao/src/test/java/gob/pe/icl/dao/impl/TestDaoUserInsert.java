/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gob.pe.icl.dao.impl;

import com.jofrantoba.model.jpa.shared.UnknownException;
import gob.pe.icl.dao.inter.InterDaoUser;
import gob.pe.icl.entity.User;
import org.hibernate.Transaction;
import org.junit.Test;
/**
 *
 * @author Usuario
 */
public class TestDaoUserInsert extends TestBaseDao{ 
    
    @Test
    public void createEntity1() throws UnknownException{                
        User entity = contextEntity.getBean(User.class);        
        InterDaoUser dao = contextDao.getBean(DaoUser.class);
        entity.setName("Jin"); 
        entity.setEmail("prueba@mail.com");
        entity.setUsername("jin23");
        entity.setPassword("54321");
        Transaction tx=dao.getSession().beginTransaction();
        dao.save(entity);
        tx.commit();
    }
    
}
