package dw.randomizer.service;

import dw.randomizer.model.Quest;

import java.util.List;

public interface IQuestCRUDService {

    List<Quest> listQuest();
    //metodo para read
    Quest searchById(Integer id);
    //el siguiente caso save se usa tanto como para INSERT como para UPDATE
    //si id es null, se hace insert, caso contrario se hace update
    void saveQuest(Quest quest);
    //metodo para delete
    void deleteQuest(Quest quest);
}
