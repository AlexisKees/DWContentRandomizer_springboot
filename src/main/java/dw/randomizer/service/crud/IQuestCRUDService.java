package dw.randomizer.service.crud;

import dw.randomizer.model.AreaDanger;
import dw.randomizer.model.Quest;

import java.util.List;

public interface IQuestCRUDService extends IGenericCRUDService<Quest>{
    List<Quest> listCRUD();
    //metodo para read
    Quest searchByIdCRUD(Integer id);
    //el siguiente caso save se usa tanto como para INSERT como para UPDATE
    //si id es null, se hace insert, caso contrario se hace update
    void saveCRUD(Quest area);
    //metodo para delete
    void deleteCRUD(Quest area);

}
