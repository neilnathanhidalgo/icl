
package gob.pe.icl.dao.inter;

import com.jofrantoba.model.jpa.daoentity.InterCrud;
import gob.pe.icl.entity.User;

import java.util.List;

public interface InterDaoUser extends InterCrud<User>{
    List<User> findAllUsers();

}