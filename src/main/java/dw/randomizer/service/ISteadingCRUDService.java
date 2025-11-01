package dw.randomizer.service;

import dw.randomizer.model.Steading;

import java.util.List;

public interface ISteadingCRUDService {
    List<Steading> listSteading();
    //metodo para read
    Steading searchById(Integer id);
    //el siguiente caso save se usa tanto como para INSERT como para UPDATE
    //si id es null, se hace insert, caso contrario se hace update
    void saveSteading(Steading steading);
    //metodo para delete
    void deleteSteading(Steading steading);
}
