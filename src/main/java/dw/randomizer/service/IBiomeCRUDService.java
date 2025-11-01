package dw.randomizer.service;

import dw.randomizer.model.Biome;

import java.util.List;

public interface IBiomeCRUDService {
    List<Biome> listBiome();
    //metodo para read
    Biome searchByIdBiome(Integer id);
    //el siguiente caso save se usa tanto como para INSERT como para UPDATE
    //si id es null, se hace insert, caso contrario se hace update
    void saveBiome(Biome biome);
    //metodo para delete
    void deleteBiome(Biome biome);

}
