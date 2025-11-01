package dw.randomizer.service;

import dw.randomizer.model.Danger;

import java.util.List;

public interface IDangerCRUDService {
    List<Danger> listDangers();
    //metodo para read
    Danger searchById(Integer id);
    //el siguiente caso save se usa tanto como para INSERT como para UPDATE
    //si id es null, se hace insert, caso contrario se hace update
    void saveDanger(Danger danger);
    //metodo para delete
    void deleteDanger(Danger danger);
}
