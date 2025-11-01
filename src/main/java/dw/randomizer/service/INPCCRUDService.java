package dw.randomizer.service;

import dw.randomizer.model.NPC;

import java.util.List;

public interface INPCCRUDService {
    List<NPC> listNPCs();
    //metodo para read
    NPC searchById(Integer id);
    //el siguiente caso save se usa tanto como para INSERT como para UPDATE
    //si id es null, se hace insert, caso contrario se hace update
    void saveNPC(NPC npc);
    //metodo para delete
    void deleteNPC(NPC npc);
}
