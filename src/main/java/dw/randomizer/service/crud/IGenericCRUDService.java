package dw.randomizer.service.crud;

import dw.randomizer.model.IPWClass;

import java.util.List;

public interface IGenericCRUDService<T extends IPWClass> {
    List<T> listCRUD();
    //metodo para read
    T searchByIdCRUD(Integer id);
    //el siguiente caso save se usa tanto como para INSERT como para UPDATE
    //si id es null, se hace insert, caso contrario se hace update
    void saveCRUD(T object);
    //metodo para delete
    void deleteCRUD(T object);
}
