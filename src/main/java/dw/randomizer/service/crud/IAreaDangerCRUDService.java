package dw.randomizer.service.crud;

import dw.randomizer.model.AreaDanger;

import java.util.List;

public interface IAreaDangerCRUDService extends IGenericCRUDService<AreaDanger>{
        List<AreaDanger> listCRUD();
        //metodo para read
        AreaDanger searchByIdCRUD(Integer id);
        //el siguiente caso save se usa tanto como para INSERT como para UPDATE
        //si id es null, se hace insert, caso contrario se hace update
        void saveCRUD(AreaDanger area);
        //metodo para delete
        void deleteCRUD(AreaDanger area);
}
