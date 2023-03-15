package gob.pe.icl.dao.inter;

import com.jofrantoba.model.jpa.daoentity.InterCrud;
import gob.pe.icl.entity.Car;

import java.util.List;

public interface InterDaoCar extends InterCrud<Car> {
    List<Car> findByUserId(Long userId);
    List<Car> findAll();
}
