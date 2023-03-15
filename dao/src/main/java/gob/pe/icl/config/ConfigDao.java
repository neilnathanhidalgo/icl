/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gob.pe.icl.config;

import com.jofrantoba.model.jpa.psf.PSF;
import com.jofrantoba.model.jpa.psf.connection.ConnectionPropertiesMysql;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 *
 * @author Usuario
 */
@Configuration
@ComponentScan(basePackages = {"gob.pe.icl.dao"})
public class ConfigDao {
    @Autowired
    @Primary
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(){
        List<String> packages=new ArrayList();
        packages.add("gob.pe.icl.entity");
        PSF.getInstance().buildPSF("mysql", getCnx(), packages);
        SessionFactory sesionFactory=PSF.getInstance().getPSF("mysql");
        return  sesionFactory;
    }
    
    private ConnectionPropertiesMysql getCnx(){
        ConnectionPropertiesMysql cnx=new ConnectionPropertiesMysql("127.0.0.1",3306,"develtrex","root","72087008");
        return cnx;
    }
}
