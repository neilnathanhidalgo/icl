/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gob.pe.icl.service.inter;

import com.jofrantoba.model.jpa.shared.UnknownException;
import gob.pe.icl.entity.User;

import java.util.List;


public interface InterServiceUser {

    User getUserById(Long id) throws UnknownException;
    User saveUser(User entidad)throws UnknownException;
    List<User> findAllUsers() throws UnknownException;
    User updateUser(User user) throws UnknownException;
    void deleteUser(Long userId) throws UnknownException;

}
