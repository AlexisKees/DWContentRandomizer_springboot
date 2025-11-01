package dw.randomizer.service;

import dw.randomizer.model.Creature;

import java.util.List;

public interface ICreatureCRUDService extends IGenericCRUDService<Creature>{
    List<Creature> listCRUD();
    //metodo para read
    Creature searchByIdCRUD(Integer id);
    //el siguiente caso save se usa tanto como para INSERT como para UPDATE
    //si id es null, se hace insert, caso contrario se hace update
    void saveCRUD(Creature area);
    //metodo para delete
    void deleteCRUD(Creature area);
}
