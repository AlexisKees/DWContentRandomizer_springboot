package dw.randomizer.service;

import dw.randomizer.model.Discovery;

import java.util.List;

public interface IDiscoveryCRUDService extends IGenericCRUDService<Discovery>{
    List<Discovery> listCRUD();
    //metodo para read
    Discovery searchByIdCRUD(Integer id);
    //el siguiente caso save se usa tanto como para INSERT como para UPDATE
    //si id es null, se hace insert, caso contrario se hace update
    void saveCRUD(Discovery area);
    //metodo para delete
    void deleteCRUD(Discovery area);
}
