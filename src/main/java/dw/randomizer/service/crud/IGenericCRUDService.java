package dw.randomizer.service.crud;

import dw.randomizer.model.IPWClass;

import java.util.List;

public interface IGenericCRUDService<T extends IPWClass> {
    List<T> list();
    //metodo para read
    T searchById(Integer id);
    //el siguiente caso save se usa tanto como para INSERT como para UPDATE
    //si id es null, se hace insert, caso contrario se hace update
    void save(T object);
    //metodo para delete
    void delete(T object);
}
