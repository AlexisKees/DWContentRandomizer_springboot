package dw.randomizer.service;

import dw.randomizer.model.Creature;

import java.util.List;

public interface ICreatureCRUDService {
    List<Creature> listCreatures();
    //metodo para read
    Creature searchById(Integer id);
    //el siguiente caso save se usa tanto como para INSERT como para UPDATE
    //si id es null, se hace insert, caso contrario se hace update
    void saveCreature(Creature creature);
    //metodo para delete
    void deleteCreature(Creature creature);
}
