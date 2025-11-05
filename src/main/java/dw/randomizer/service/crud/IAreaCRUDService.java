package dw.randomizer.service.crud;

import dw.randomizer.model.Area;

import java.util.List;

public interface IAreaCRUDService extends IGenericCRUDService<Area>{
    List<Area> listCRUD();
    //metodo para read
    Area searchByIdCRUD(Integer id);
    //el siguiente caso save se usa tanto como para INSERT como para UPDATE
    //si id es null, se hace insert, caso contrario se hace update
    void saveCRUD(Area area);
    //metodo para delete
    void deleteCRUD(Area area);
}
