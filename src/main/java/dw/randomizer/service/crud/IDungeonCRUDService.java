package dw.randomizer.service.crud;

import dw.randomizer.model.AreaDanger;
import dw.randomizer.model.Dungeon;

import java.util.List;

public interface IDungeonCRUDService extends IGenericCRUDService<Dungeon>{
    List<Dungeon> listCRUD();
    //metodo para read
    Dungeon searchByIdCRUD(Integer id);
    //el siguiente caso save se usa tanto como para INSERT como para UPDATE
    //si id es null, se hace insert, caso contrario se hace update
    void saveCRUD(Dungeon area);
    //metodo para delete
    void deleteCRUD(Dungeon area);

}
