package dw.randomizer.service;

import dw.randomizer.model.AreaDanger;

import java.util.List;

public interface IAreaDangerCRUDService {
    List<AreaDanger> listAreaDangers();
    //metodo para read
    AreaDanger searchById(Integer id);
    //el siguiente caso save se usa tanto como para INSERT como para UPDATE
    //si id es null, se hace insert, caso contrario se hace update
    void saveAreaDanger(AreaDanger areaDanger);
    //metodo para delete
    void deleteAreaDanger(AreaDanger areaDanger);
}
