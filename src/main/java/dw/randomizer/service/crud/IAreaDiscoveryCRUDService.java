package dw.randomizer.service.crud;

import dw.randomizer.model.AreaDanger;
import dw.randomizer.model.AreaDiscovery;

import java.util.List;

public interface IAreaDiscoveryCRUDService extends IGenericCRUDService<AreaDiscovery>{
    List<AreaDiscovery> listCRUD();
    //metodo para read
    AreaDiscovery searchByIdCRUD(Integer id);
    //el siguiente caso save se usa tanto como para INSERT como para UPDATE
    //si id es null, se hace insert, caso contrario se hace update
    void saveCRUD(AreaDiscovery area);
    //metodo para delete
    void deleteCRUD(AreaDiscovery area);


}
