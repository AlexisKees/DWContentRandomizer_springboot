package dw.randomizer.service.crud;

import dw.randomizer.model.Follower;

import java.util.List;

public interface IFollowerCRUDService extends IGenericCRUDService<Follower>{
    List<Follower> listCRUD();
    //metodo para read
    Follower searchByIdCRUD(Integer id);
    //el siguiente caso save se usa tanto como para INSERT como para UPDATE
    //si id es null, se hace insert, caso contrario se hace update
    void saveCRUD(Follower area);
    //metodo para delete
    void deleteCRUD(Follower area);
}
