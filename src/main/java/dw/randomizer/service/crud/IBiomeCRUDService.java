package dw.randomizer.service.crud;

import dw.randomizer.model.AreaDanger;
import dw.randomizer.model.Biome;

import java.util.List;

public interface IBiomeCRUDService extends IGenericCRUDService<Biome>{
    List<Biome> listCRUD();
    //metodo para read
    Biome searchByIdCRUD(Integer id);
    //el siguiente caso save se usa tanto como para INSERT como para UPDATE
    //si id es null, se hace insert, caso contrario se hace update
    void saveCRUD(Biome area);
    //metodo para delete
    void deleteCRUD(Biome area);


}
