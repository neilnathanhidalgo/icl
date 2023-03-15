package gob.pe.icl.dao.inter;

import com.jofrantoba.model.jpa.daoentity.InterCrud;
import gob.pe.icl.entity.Bike;

import java.util.List;

public interface InterDaoBike extends InterCrud<Bike> {
    List<Bike> findByUserId(Long userId);
    List<Bike> findAll();

}
