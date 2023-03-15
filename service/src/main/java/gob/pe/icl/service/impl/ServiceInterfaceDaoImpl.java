/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gob.pe.icl.service.impl;

import gob.pe.icl.config.ConfigDao;
import gob.pe.icl.dao.impl.DaoBike;
import gob.pe.icl.dao.impl.DaoCar;
import gob.pe.icl.dao.impl.DaoUser;
import gob.pe.icl.dao.inter.InterDaoBike;
import gob.pe.icl.dao.inter.InterDaoCar;
import gob.pe.icl.dao.inter.InterDaoUser;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 *
 * @author Usuario
 */
@Configuration
public class ServiceInterfaceDaoImpl {
    protected AnnotationConfigApplicationContext contextDao = new AnnotationConfigApplicationContext(ConfigDao.class);
    
    @Bean
    public InterDaoUser setUpDaoUser(){
        return contextDao.getBean(DaoUser.class);
    }
    @Bean
    public InterDaoCar setUpDaoCar(){
        return contextDao.getBean(DaoCar.class);
    }
    @Bean
    public InterDaoBike setUpDaoBike(){
        return contextDao.getBean(DaoBike.class);
    }
}
