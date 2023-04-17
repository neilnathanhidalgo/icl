/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gob.pe.icl.config;

import com.jofrantoba.model.jpa.psf.PSF;
import com.jofrantoba.model.jpa.psf.connection.ConnectionPropertiesMysql;
import java.util.ArrayList;
import java.util.List;

import com.jofrantoba.model.jpa.psf.connection.ConnectionPropertiesPostgre;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan(basePackages = {"gob.pe.icl.dao"})
public class ConfigDao {
    @Autowired
    @Primary
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(){
        List<String> packages=new ArrayList();
        packages.add("gob.pe.icl.entity");
        PSF.getInstance().buildPSF("postgres", getCnx(), packages);
        SessionFactory sesionFactory=PSF.getInstance().getPSF("postgres");
        return  sesionFactory;
    }

    private ConnectionPropertiesPostgre getCnx(){
        ConnectionPropertiesPostgre cnx=new ConnectionPropertiesPostgre("localhost",5432,"develtrex","postgres","72087008");
        return cnx;
    }
}
