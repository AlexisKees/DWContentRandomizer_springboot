package dw.randomizer.service;

import dw.randomizer.model.Dungeon;

import java.util.List;

public interface IDungeonCRUDService {

    List<Dungeon> listDungeons();
    //metodo para read
    Dungeon searchById(Integer id);
    //el siguiente caso save se usa tanto como para INSERT como para UPDATE
    //si id es null, se hace insert, caso contrario se hace update
    void saveDungeon(Dungeon dungeon);
    //metodo para delete
    void deleteDungeon(Dungeon dungeon);
}
