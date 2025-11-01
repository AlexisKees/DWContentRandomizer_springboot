package dw.randomizer.service;

import dw.randomizer.model.Area;

import java.util.List;

public interface IAreaCRUDService {
    List<Area> listAreas();
    //metodo para read
    Area searchById(Integer id);
    //el siguiente caso save se usa tanto como para INSERT como para UPDATE
    //si id es null, se hace insert, caso contrario se hace update
    void saveArea(Area area);
    //metodo para delete
    void deleteArea(Area area);
}
