package dw.randomizer.service.crud;

import dw.randomizer.model.AreaDanger;
import dw.randomizer.model.Danger;

import java.util.List;

public interface IDangerCRUDService extends IGenericCRUDService<Danger> {
    List<Danger> listCRUD();

    //metodo para read
    Danger searchByIdCRUD(Integer id);

    //el siguiente caso save se usa tanto como para INSERT como para UPDATE
    //si id es null, se hace insert, caso contrario se hace update
    void saveCRUD(Danger area);

    //metodo para delete
    void deleteCRUD(Danger area);
}

