package dw.randomizer.service.crud;

import dw.randomizer.model.AreaDanger;
import dw.randomizer.model.Steading;

import java.util.List;

public interface ISteadingCRUDService extends IGenericCRUDService<Steading>{
    List<Steading> listCRUD();
    //metodo para read
    Steading searchByIdCRUD(Integer id);
    //el siguiente caso save se usa tanto como para INSERT como para UPDATE
    //si id es null, se hace insert, caso contrario se hace update
    void saveCRUD(Steading area);
    //metodo para delete
    void deleteCRUD(Steading area);

}
