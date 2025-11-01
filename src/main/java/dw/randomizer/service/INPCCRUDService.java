package dw.randomizer.service;

import dw.randomizer.model.NPC;

import java.util.List;

public interface INPCCRUDService extends IGenericCRUDService<NPC>{
    List<NPC> listCRUD();
    //metodo para read
    NPC searchByIdCRUD(Integer id);
    //el siguiente caso save se usa tanto como para INSERT como para UPDATE
    //si id es null, se hace insert, caso contrario se hace update
    void saveCRUD(NPC area);
    //metodo para delete
    void deleteCRUD(NPC area);
}
